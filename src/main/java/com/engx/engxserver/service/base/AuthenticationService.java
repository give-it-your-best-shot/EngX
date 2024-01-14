package com.engx.engxserver.service.base;

import com.engx.engxserver.dto.AuthenticationRequest;
import com.engx.engxserver.dto.AuthenticationResponse;
import com.engx.engxserver.dto.RegisterRequest;
import com.engx.engxserver.dto.UserDTO;
import com.engx.engxserver.exception.InsertFailException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest request) throws InsertFailException;

    AuthenticationResponse authenticate(AuthenticationRequest request);

    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;

    UserDTO getAuthenticatedUser();

    void logout(HttpServletRequest request, HttpServletResponse response, String jwt);
}
