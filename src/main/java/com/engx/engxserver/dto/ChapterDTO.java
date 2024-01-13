package com.engx.engxserver.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChapterDTO {
    private Long id;

    private String name;

    private String photoURL;

    private String description;

    private List<ChapterWordDTO> words;
}
