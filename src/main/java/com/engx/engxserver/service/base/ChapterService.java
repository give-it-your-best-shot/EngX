package com.engx.engxserver.service.base;

import com.engx.engxserver.dto.AddChapterRequestDTO;
import com.engx.engxserver.dto.ChapterDTO;
import java.util.List;

public interface ChapterService {
    ChapterDTO addChapter(AddChapterRequestDTO chapter);

    List<ChapterDTO> getAllChapters();
}
