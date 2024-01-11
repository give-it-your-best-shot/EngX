package com.engx.engxserver.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChapterWordDTO {
  private Long id;

  private String word;

  private String meaning;

  private String example_sentence;
}
