package com.engx.engxserver.service.impl;

import com.engx.engxserver.dto.AddChapterRequestDTO;
import com.engx.engxserver.dto.ChapterDTO;
import com.engx.engxserver.dto.ChapterWordDTO;
import com.engx.engxserver.entity.Chapter;
import com.engx.engxserver.repository.ChapterRepository;
import com.engx.engxserver.service.base.ChapterService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChapterServiceImpl implements ChapterService {
    private final ChapterRepository chapterRepositor;
    private final ModelMapper modelMapper;

    @Override
    public ChapterDTO addChapter(AddChapterRequestDTO chapterRequestDTO) {
        Chapter chapter = modelMapper.map(chapterRequestDTO, Chapter.class);

        chapter.getWords().forEach(word -> word.setChapter(chapter));
        Chapter savedChapter = chapterRepositor.save(chapter);
        List<ChapterWordDTO> words_dto = savedChapter.getWords().stream()
                .map(word -> modelMapper.map(word, ChapterWordDTO.class))
                .collect(Collectors.toList());

        ChapterDTO returnChapterDTO = modelMapper.map(savedChapter, ChapterDTO.class);
        returnChapterDTO.setWords(words_dto);
        return returnChapterDTO;
    }

    @Override
    public List<ChapterDTO> getAllChapters() {
        return chapterRepositor.findAll().stream()
                .map(chapter -> modelMapper.map(chapter, ChapterDTO.class))
                .collect(Collectors.toList());
    }
}
