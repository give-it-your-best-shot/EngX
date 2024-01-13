package com.engx.engxserver.dto;

import com.engx.engxserver.entity.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;

    private String username;

    private String firstName;

    private String lastName;

    private UserRole userRole;

    private String photoURL;
}
