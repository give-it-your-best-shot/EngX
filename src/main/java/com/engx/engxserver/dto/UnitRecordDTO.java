package com.engx.engxserver.dto;

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

    private String userId;

    private String unitId;

    private Integer numQuestion;

    private Float score;
}
