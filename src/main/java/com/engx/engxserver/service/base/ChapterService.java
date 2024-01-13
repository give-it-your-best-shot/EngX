package com.engx.engxserver.service.base;

import com.engx.engxserver.dto.AddChapterRequestDTO;
import com.engx.engxserver.dto.ChapterDTO;
import com.engx.engxserver.exception.InsertFailException;
import com.engx.engxserver.exception.ResourceNotFoundException;
import java.util.List;

public interface ChapterService {
    ChapterDTO addChapter(AddChapterRequestDTO chapter) throws InsertFailException;

    List<ChapterDTO> getAllChapters();

    ChapterDTO getChapterById(Long id) throws ResourceNotFoundException;
}
