package com.engx.engxserver.service.impl;

import com.engx.engxserver.dto.BookRecordDTO;
import com.engx.engxserver.repository.BookRecordRepository;
import com.engx.engxserver.repository.UnitRecordRepository;
import com.engx.engxserver.service.base.RecordService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecordServiceImpl implements RecordService {
    private final BookRecordRepository bookRecordRepository;
    private final UnitRecordRepository unitRecordRepository;
    private final ModelMapper modelMapper;
    private final MessageSource messageSource;

    @Override
    public BookRecordDTO addOneBookRecord(BookRecordDTO bookRecordDTO) {
        return modelMapper.map(
                bookRecordRepository.addOne(
                        bookRecordDTO.getUserId(),
                        bookRecordDTO.getBookId(),
                        bookRecordDTO.getNumQuestion(),
                        bookRecordDTO.getScore()),
                BookRecordDTO.class);
    }
}
