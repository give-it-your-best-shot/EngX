package com.engx.engxserver.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddUnitRecordRequestDTO {

    private Long userId;

    private Long unitId;

    private Integer numQuestion;

    private Float score;
}
