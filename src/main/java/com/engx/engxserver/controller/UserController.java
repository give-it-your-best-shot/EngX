package com.engx.engxserver.controller;

import com.engx.engxserver.service.base.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private ModelMapper modelMapper;

    private UserService userService;

    private MessageSource messageSource;

    // @PostMapping("/login")
    // public ResponseEntity<LoginResponseDTO> loginWithEmailAddress(@RequestBody
    // LoginRequestDTO loginRequestDTO)
    // throws LoginException {
    // User user = userService.getUserByEmail(loginRequestDTO.getEmail());
    // if (user == null) {
    // throw new
    // LoginException(messageSource.getMessage("api.error.user.not.authenticated",
    // null, null));
    // }
    // UserDTO userDTOResponse = modelMapper.map(user, UserDTO.class);

    // LoginResponseDTO loginDTO = new LoginResponseDTO(userDTOResponse);
    // return new ResponseEntity<>(loginDTO, HttpStatus.OK);
    // }
}
