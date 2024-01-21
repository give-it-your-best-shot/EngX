package com.engx.engxserver.service.impl;

import com.engx.engxserver.dto.AuthenticationRequest;
import com.engx.engxserver.dto.AuthenticationResponse;
import com.engx.engxserver.dto.RegisterRequest;
import com.engx.engxserver.dto.UserDTO;
import com.engx.engxserver.entity.Token;
import com.engx.engxserver.entity.TokenType;
import com.engx.engxserver.entity.User;
import com.engx.engxserver.entity.UserRole;
import com.engx.engxserver.exception.InsertFailException;
import com.engx.engxserver.exception.MissingArgumentException;
import com.engx.engxserver.repository.TokenRepository;
import com.engx.engxserver.repository.UserRepository;
import com.engx.engxserver.security.CustomUserDetails;
import com.engx.engxserver.security.JwtTokenProvider;
import com.engx.engxserver.service.base.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import javax.security.auth.login.LoginException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final TokenRepository tokenRepository;
    private final ModelMapper modelMapper;
    private final MessageSource messageSource;

    public AuthenticationResponse register(RegisterRequest request) throws InsertFailException {
        User user = User.builder()
                .firstName(request.getFirstname())
                .lastName(request.getLastname())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .userRole(UserRole.USER)
                .build();
        try {
            User savedUser = userRepository.save(user);
            CustomUserDetails userDetails = new CustomUserDetails(user);
            String jwtToken = jwtTokenProvider.generateToken(userDetails);
            String refreshToken = jwtTokenProvider.generateRefreshToken(userDetails);
            saveUserToken(savedUser, jwtToken);
            return AuthenticationResponse.builder()
                    .accessToken(jwtToken)
                    .refreshToken(refreshToken)
                    .user(modelMapper.map(user, UserDTO.class))
                    .build();
        } catch (Exception exception) {
            throw new InsertFailException(messageSource.getMessage("api.error.user.existed", null, null));
        }
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        User user = userRepository.findByUsername(request.getUsername());
        if (user == null) {
            throw new UsernameNotFoundException(
                    messageSource.getMessage("api.error.user.not.authenticated", null, null));
        }
        CustomUserDetails userDetails = new CustomUserDetails(user);
        String jwtToken = jwtTokenProvider.generateToken(userDetails);
        String refreshToken = jwtTokenProvider.generateRefreshToken(userDetails);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .user(modelMapper.map(user, UserDTO.class))
                .build();
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty()) return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    private void saveUserToken(User user, String jwtToken) {
        Token token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    public AuthenticationResponse refreshToken(HttpServletRequest request, HttpServletResponse response)
            throws MissingArgumentException, LoginException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String username;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new MissingArgumentException("Refresh token missing");
        }
        refreshToken = authHeader.substring(7);
        username = jwtTokenProvider.extractUsername(refreshToken);
        if (username == null) {
            throw new LoginException(messageSource.getMessage("api.error.user.not.authenticated", null, null));
        }
        User user = this.userRepository.findByUsername(username);
        if (user == null) {
            throw new LoginException(messageSource.getMessage("api.error.user.not.authenticated", null, null));
        }
        UserDetails userDetails = new CustomUserDetails(user);
        if (jwtTokenProvider.isTokenValid(refreshToken, userDetails)) {
            var accessToken = jwtTokenProvider.generateToken(userDetails);
            revokeAllUserTokens(user);
            saveUserToken(user, accessToken);
            var authResponse = AuthenticationResponse.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .user(modelMapper.map(user, UserDTO.class))
                    .build();
            return authResponse;
        }
        throw new LoginException(messageSource.getMessage("api.error.user.not.authenticated", null, null));
    }

    @Override
    public UserDTO getAuthenticatedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getPrincipal() instanceof String) {
            return null;
        } else {
            CustomUserDetails customUserDetails = modelMapper.map(auth.getPrincipal(), CustomUserDetails.class);
            UserDTO userDTO = modelMapper.map(customUserDetails.getUser(), UserDTO.class);
            return userDTO;
        }
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, String jwt) {
        var storedToken = tokenRepository.findByToken(jwt).orElse(null);
        if (storedToken != null) {
            storedToken.setExpired(true);
            storedToken.setRevoked(true);
            tokenRepository.save(storedToken);
            SecurityContextHolder.clearContext();
        }
    }
}
