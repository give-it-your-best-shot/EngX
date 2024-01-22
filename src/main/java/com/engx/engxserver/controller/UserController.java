package com.engx.engxserver.controller;

import com.engx.engxserver.dto.ResponseFailure;
import com.engx.engxserver.dto.ResponseSuccess;
import com.engx.engxserver.dto.UserDTO;
import com.engx.engxserver.exception.InsertFailException;
import com.engx.engxserver.service.base.AuthenticationService;
import com.engx.engxserver.service.base.AvatarStorageService;

import lombok.AllArgsConstructor;

import java.io.IOException;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private AuthenticationService authenticationService;
    private AvatarStorageService avatarStorageService;

    @PostMapping("/get-user")
    public ResponseEntity<ResponseSuccess<UserDTO>> getUser() {
        UserDTO user = authenticationService.getAuthenticatedUser();
        return ResponseEntity.ok(new ResponseSuccess<>(user));
    }

    @PostMapping("/{userId}/avatar")
    public ResponseEntity<ResponseSuccess<String>> uploadAvatar(@PathVariable long userId, @RequestParam("image") MultipartFile image) throws IOException {
        avatarStorageService.save(userId, image);
        return ResponseEntity.ok(new ResponseSuccess<>("OK"));
    }

    @GetMapping(value = "/{userId}/avatar", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getAvatar(@PathVariable long userId) throws IOException {
        return avatarStorageService.get(userId);
    }
}
