package com.engx.engxserver.controller;

import com.engx.engxserver.dto.UserDTO;
import com.engx.engxserver.security.CustomUserDetails;
import com.engx.engxserver.service.base.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private ModelMapper modelMapper;

    private UserService userService;

    private MessageSource messageSource;

    @PostMapping("/get-user")
    public ResponseEntity<?> getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails customUserDetails = modelMapper.map(auth.getPrincipal(), CustomUserDetails.class);
        UserDTO userDTO = modelMapper.map(customUserDetails.getUser(), UserDTO.class);
        return ResponseEntity.ok(userDTO);
    }
}
