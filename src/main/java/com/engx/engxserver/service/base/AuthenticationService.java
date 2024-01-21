package com.engx.engxserver.service.base;

import com.engx.engxserver.dto.AuthenticationRequest;
import com.engx.engxserver.dto.AuthenticationResponse;
import com.engx.engxserver.dto.RegisterRequest;
import com.engx.engxserver.dto.UserDTO;
import com.engx.engxserver.exception.InsertFailException;
import com.engx.engxserver.exception.MissingArgumentException;
import com.engx.engxserver.exception.UnAuthenticationException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import javax.security.auth.login.LoginException;

public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest request) throws InsertFailException;

    AuthenticationResponse authenticate(AuthenticationRequest request) throws UnAuthenticationException;

    AuthenticationResponse refreshToken(HttpServletRequest request, HttpServletResponse response)
            throws MissingArgumentException, LoginException;

    UserDTO getAuthenticatedUser();

    void logout(HttpServletRequest request, HttpServletResponse response, String jwt);
}
