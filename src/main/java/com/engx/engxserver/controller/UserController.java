package com.engx.engxserver.controller;

import com.engx.engxserver.dto.UserDTO;
import com.engx.engxserver.service.base.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private AuthenticationService authenticationService;

    @PostMapping("/get-user")
    public ResponseEntity<UserDTO> getUser() {
        UserDTO user = authenticationService.getAuthenticatedUser();
        return ResponseEntity.ok(user);
    }
}
