package com.engx.engxserver.controller;

import org.modelmapper.ModelMapper;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.engx.engxserver.dto.LoginResponseDTO;
import com.engx.engxserver.dto.LoginRequestDTO;
import com.engx.engxserver.dto.UserDTO;
import com.engx.engxserver.entity.User;
import com.engx.engxserver.service.base.UserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
  private ModelMapper modelMapper;

  private UserService userService;

  private MessageSource messageSource;

  @PostMapping("/login")
  public ResponseEntity<?> loginWithEmailAddress(@RequestBody LoginRequestDTO loginRequestDTO) {
    User user = userService.getUserByEmail(loginRequestDTO.getEmail());
    if (user == null) {
      return new ResponseEntity<>(messageSource.getMessage("api.error.user.not.authenticated", null, null),
          HttpStatus.UNAUTHORIZED);
    }
    UserDTO userDTOResponse = modelMapper.map(user, UserDTO.class);

    LoginResponseDTO loginDTO = new LoginResponseDTO(userDTOResponse);
    return ResponseEntity.ok(loginDTO);
  }
}
