package com.engx.engxserver.dto;

import com.engx.engxserver.security.CustomUserDetails;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@AllArgsConstructor
public class AuthenticatedUserResponse {
    private UserDTO user;
    private ModelMapper modelMapper;

    protected AuthenticatedUserResponse() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getPrincipal() instanceof String) {
            setUser(null);
        } else {
            CustomUserDetails customUserDetails = modelMapper.map(auth.getPrincipal(), CustomUserDetails.class);
            UserDTO userDTO = modelMapper.map(customUserDetails.getUser(), UserDTO.class);
            setUser(userDTO);
        }
    }

    public UserDTO getUser() {
        return this.user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
