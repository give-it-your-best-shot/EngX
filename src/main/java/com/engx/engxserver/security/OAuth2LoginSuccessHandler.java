package com.engx.engxserver.security;

import com.engx.engxserver.entity.User;
import com.engx.engxserver.entity.UserRole;
import com.engx.engxserver.repository.TokenRepository;
import com.engx.engxserver.repository.UserRepository;
import com.engx.engxserver.service.base.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    private final UserRepository userRepository;
    private final UserService userService;
    private final TokenRepository tokenRepository;
    private final ModelMapper modelMapper;
    private final JwtTokenProvider jwtTokenProvider;

    @Value("${application.client}")
    private String clientUrl;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws ServletException, IOException {

        OAuth2AuthenticationToken oAuth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
        if ("google".equals(oAuth2AuthenticationToken.getAuthorizedClientRegistrationId())) {
            DefaultOAuth2User principal = (DefaultOAuth2User) authentication.getPrincipal();
            Map<String, Object> attributes = principal.getAttributes();
            String email = attributes.getOrDefault("email", "").toString();
            String firstName = attributes.getOrDefault("given_name", "").toString();
            String lastName = attributes.getOrDefault("family_name", "").toString();
            String photoUrl = attributes.getOrDefault("picture", "").toString();
            User user = userRepository.findByEmail(email);
            com.engx.engxserver.entity.User.UserBuilder userBuilder = User.builder()
                    .userRole(UserRole.USER)
                    .email(email)
                    .firstName(firstName)
                    .lastName(lastName)
                    .photoURL(photoUrl);
            if (user != null) {
                userBuilder = userBuilder.id(user.getId());
            }
            user = userRepository.save(userBuilder.build());
            CustomUserDetails userDetails = new CustomUserDetails(user);
            String refreshToken = jwtTokenProvider.generateRefreshToken(userDetails);

            String targetUrl = UriComponentsBuilder.fromUriString(clientUrl + "/oauth2/callback")
                    .queryParam("refresh-token", refreshToken)
                    .build()
                    .toUriString();
            getRedirectStrategy().sendRedirect(request, response, targetUrl);
        }
    }
}
