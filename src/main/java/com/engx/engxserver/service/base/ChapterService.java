package com.engx.engxserver.service.base;

import java.util.List;

import com.engx.engxserver.dto.AddChapterRequestDTO;
import com.engx.engxserver.dto.ChapterDTO;

public interface ChapterService {
  ChapterDTO addChapter(AddChapterRequestDTO chapter);

  List<ChapterDTO> getAllChapters();
}
