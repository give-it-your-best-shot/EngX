package com.engx.engxserver.service.impl;

import com.engx.engxserver.dto.AddChapterRequestDTO;
import com.engx.engxserver.dto.ChapterDTO;
import com.engx.engxserver.entity.Chapter;
import com.engx.engxserver.exception.InsertFailException;
import com.engx.engxserver.exception.ResourceNotFoundException;
import com.engx.engxserver.repository.ChapterRepository;
import com.engx.engxserver.service.base.ChapterService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChapterServiceImpl implements ChapterService {
    private final ChapterRepository chapterRepositor;
    private final ModelMapper modelMapper;
    private final MessageSource messageSource;

    @Override
    public ChapterDTO addChapter(AddChapterRequestDTO chapterRequestDTO) throws InsertFailException {
        try {
            Chapter chapter = modelMapper.map(chapterRequestDTO, Chapter.class);
            Chapter savedChapter = chapterRepositor.save(chapter);
            ChapterDTO returnChapterDTO = modelMapper.map(savedChapter, ChapterDTO.class);
            return returnChapterDTO;
        } catch (Exception exception) {
            throw new InsertFailException(messageSource.getMessage("api.error.chapter.not.inserted", null, null));
        }
    }

    @Override
    public List<ChapterDTO> getAllChapters() {
        return chapterRepositor.findAll().stream()
                .map(chapter -> modelMapper.map(chapter, ChapterDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ChapterDTO getChapterById(Long id) throws ResourceNotFoundException {
        Optional<Chapter> chapter = chapterRepositor.findById(id);
        if (chapter.get() == null) {
            throw new ResourceNotFoundException(messageSource.getMessage("api.error.chapter.not.found", null, null));
        }
        ChapterDTO chapterDTO = modelMapper.map(chapter.get(), ChapterDTO.class);
        return chapterDTO;
    }
}
