package com.engx.engxserver.dto;

import java.util.Date;
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

    private String userId;

    private String bookId;

    private Integer numQuestion;

    private Float score;

    private Date createdAt;
}
