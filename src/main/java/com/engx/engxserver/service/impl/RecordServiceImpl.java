package com.engx.engxserver.service.impl;

import com.engx.engxserver.dto.AddBookRecordRequestDTO;
import com.engx.engxserver.dto.AddUnitRecordRequestDTO;
import com.engx.engxserver.dto.BookRecordDTO;
import com.engx.engxserver.dto.UnitRecordDTO;
import com.engx.engxserver.entity.Book;
import com.engx.engxserver.entity.BookRecord;
import com.engx.engxserver.entity.Unit;
import com.engx.engxserver.entity.UnitRecord;
import com.engx.engxserver.entity.User;
import com.engx.engxserver.repository.BookRecordRepository;
import com.engx.engxserver.repository.BookRepository;
import com.engx.engxserver.repository.UnitRecordRepository;
import com.engx.engxserver.repository.UnitRepository;
import com.engx.engxserver.repository.UserRepository;
import com.engx.engxserver.service.base.RecordService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecordServiceImpl implements RecordService {
    private final BookRecordRepository bookRecordRepository;
    private final UnitRecordRepository unitRecordRepository;
    private final BookRepository bookRepository;
    private final UnitRepository unitRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final MessageSource messageSource;

    @Override
    public BookRecordDTO addOneBookRecord(AddBookRecordRequestDTO addBookRecordRequestDTO) {
        Optional<User> user = userRepository.findById(addBookRecordRequestDTO.getUserId());
        Optional<Book> book = bookRepository.findById(addBookRecordRequestDTO.getBookId());
        BookRecord bookRecord = new BookRecord();
        bookRecord.setBook(book.get());
        bookRecord.setUser(user.get());
        bookRecord.setScore(addBookRecordRequestDTO.getScore());
        bookRecord.setNumQuestion(addBookRecordRequestDTO.getNumQuestion());
        bookRecord.setPassed(addBookRecordRequestDTO.getPassed());
        BookRecord saveBookRecord = bookRecordRepository.save(bookRecord);
        BookRecordDTO bookRecordDTO = modelMapper.map(saveBookRecord, BookRecordDTO.class);
        bookRecordDTO.setBookId(book.get().getId());
        return bookRecordDTO;
    }

    @Override
    public UnitRecordDTO addOneUnitRecord(AddUnitRecordRequestDTO addUnitRecordRequestDTO) {
        Optional<User> user = userRepository.findById(addUnitRecordRequestDTO.getUserId());
        Optional<Unit> unit = unitRepository.findById(addUnitRecordRequestDTO.getUnitId());
        UnitRecord unitRecord = new UnitRecord();
        unitRecord.setUnit(unit.get());
        unitRecord.setUser(user.get());
        unitRecord.setScore(addUnitRecordRequestDTO.getScore());
        unitRecord.setNumQuestion(addUnitRecordRequestDTO.getNumQuestion());
        unitRecord.setPassed(addUnitRecordRequestDTO.getPassed());
        UnitRecord saveUnitRecord = unitRecordRepository.save(unitRecord);
        UnitRecordDTO unitRecordDTO = modelMapper.map(saveUnitRecord, UnitRecordDTO.class);
        unitRecordDTO.setUnitId(unit.get().getId());
        return unitRecordDTO;
    }

    @Override
    public List<BookRecordDTO> getBookRecordsOfUser(Long userId) {
        return bookRecordRepository.findAllByUser(userId).stream()
                .map(bookRecord -> modelMapper.map(bookRecord, BookRecordDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<UnitRecordDTO> getUnitRecordsOfUser(Long userId) {
        return unitRecordRepository.findAllByUser(userId).stream()
                .map(bookRecord -> modelMapper.map(bookRecord, UnitRecordDTO.class))
                .collect(Collectors.toList());
    }
}
