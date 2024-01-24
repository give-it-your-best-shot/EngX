package com.engx.engxserver.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookRecordDTO {
    private Long id;

    private Long bookId;

    private Integer numQuestion;

    private Float score;

    private Boolean passed;

    private LocalDateTime createdAt;
}
