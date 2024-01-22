package com.engx.engxserver.dto;

import com.engx.engxserver.entity.Word;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddUnitRequestDTO {
    private String name;
    private Long bookId;
    private List<Word> words;
}
