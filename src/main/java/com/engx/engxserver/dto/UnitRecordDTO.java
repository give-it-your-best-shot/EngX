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
public class UnitRecordDTO {
    private Long id;

    private Integer numQuestion;

    private Float score;

    private LocalDateTime createdAt;
}
