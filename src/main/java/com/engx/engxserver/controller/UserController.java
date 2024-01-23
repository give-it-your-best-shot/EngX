package com.engx.engxserver.controller;

import com.engx.engxserver.dto.ResponseSuccess;
import com.engx.engxserver.dto.UserDTO;
import com.engx.engxserver.service.base.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private AuthenticationService authenticationService;

    @PostMapping("/get-user")
    public ResponseEntity<ResponseSuccess<UserDTO>> getUser() throws UsernameNotFoundException {
        UserDTO user = authenticationService.getAuthenticatedUser();
        return ResponseEntity.ok(new ResponseSuccess<>(user));
    }
}
