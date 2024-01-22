package com.engx.engxserver.service.impl;

import com.engx.engxserver.dto.BookDTO;
import com.engx.engxserver.dto.UnitDTO;
import com.engx.engxserver.dto.WordDTO;
import com.engx.engxserver.repository.BookRepository;
import com.engx.engxserver.repository.UnitRepository;
import com.engx.engxserver.repository.WordRepository;
import com.engx.engxserver.service.base.MaterialService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MaterialServiceImpl implements MaterialService {
    private final UnitRepository unitRepository;
    private final BookRepository bookRepository;
    private final WordRepository wordRepository;
    private final ModelMapper modelMapper;
    private final MessageSource messageSource;

    // @Override
    // public UnitDTO addUnit(AddUnitRequestDTO chapterRequestDTO) throws
    // InsertFailException {
    // try {
    // Unit chapter = modelMapper.map(chapterRequestDTO, Unit.class);
    // Unit savedChapter = chapterRepositor.save(chapter);
    // UnitDTO returnUnitDTO = modelMapper.map(savedChapter, UnitDTO.class);
    // return returnUnitDTO;
    // } catch (Exception exception) {
    // throw new
    // InsertFailException(messageSource.getMessage("api.error.chapter.not.inserted",
    // null, null));
    // }
    // }

    @Override
    public List<UnitDTO> getAllUnitsOfBook(Long bookId) {
        return unitRepository.findAllByBook(bookId).stream()
                .map(unit -> modelMapper.map(unit, UnitDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<WordDTO> getAllWordsOfUnit(Long unitId) {
        return wordRepository.findAllByUnit(unitId).stream()
                .map(word -> modelMapper.map(word, WordDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<BookDTO> getAllBooksOfOwner(Long ownerId) {
        return bookRepository.findAllByOwner(ownerId).stream()
                .map(book -> modelMapper.map(book, BookDTO.class))
                .collect(Collectors.toList());
    }
}
