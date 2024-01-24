package com.engx.engxserver.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddBookRecordRequestDTO {

    private Long userId;

    private Long bookId;

    private Integer numQuestion;

    private Float score;

    private Boolean passed;
}
