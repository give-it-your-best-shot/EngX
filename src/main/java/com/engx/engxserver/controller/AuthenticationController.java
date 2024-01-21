package com.engx.engxserver.controller;

import com.engx.engxserver.dto.AuthenticationRequest;
import com.engx.engxserver.dto.AuthenticationResponse;
import com.engx.engxserver.dto.RegisterRequest;
import com.engx.engxserver.dto.ResponseSuccess;
import com.engx.engxserver.exception.InsertFailException;
import com.engx.engxserver.exception.MissingArgumentException;
import com.engx.engxserver.exception.UnAuthenticationException;
import com.engx.engxserver.service.base.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import javax.security.auth.login.LoginException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService service;
    private final MessageSource messageSource;

    @PostMapping("/register")
    public ResponseEntity<ResponseSuccess<AuthenticationResponse>> register(@RequestBody RegisterRequest request)
            throws InsertFailException {
        return ResponseEntity.ok(new ResponseSuccess<>(service.register(request)));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<ResponseSuccess<AuthenticationResponse>> authenticate(
            @RequestBody AuthenticationRequest request) throws UnAuthenticationException {
        return ResponseEntity.ok(new ResponseSuccess<>(service.authenticate(request)));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<ResponseSuccess<AuthenticationResponse>> refreshToken(
            HttpServletRequest request, HttpServletResponse response) throws LoginException, MissingArgumentException {
        return ResponseEntity.ok(new ResponseSuccess<>(service.refreshToken(request, response)));
    }

    @PostMapping("/logout")
    public ResponseEntity<ResponseSuccess<String>> logout(
            HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.ok(
                    new ResponseSuccess<>(messageSource.getMessage("api.response.logout", null, null)));
        }
        jwt = authHeader.substring(7);
        service.logout(request, response, jwt);
        return ResponseEntity.ok(new ResponseSuccess<>(messageSource.getMessage("api.response.logout", null, null)));
    }
}
