package com.engx.engxserver.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddBookRequestDTO {
    private String name;
    private String description;
    private String photoUrl;
    private Long ownerId;
}
