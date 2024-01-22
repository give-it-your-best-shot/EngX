package com.engx.engxserver.controller;

import com.engx.engxserver.dto.BookRecordDTO;
import com.engx.engxserver.dto.ResponseSuccess;
import com.engx.engxserver.service.base.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/records")
@RequiredArgsConstructor
public class RecordController {

    private final RecordService recordService;

    @PostMapping("/book")
    public ResponseEntity<ResponseSuccess<BookRecordDTO>> getUser(@RequestBody BookRecordDTO bookRecordDTO) {
        return ResponseEntity.ok(new ResponseSuccess<>(recordService.addOneBookRecord(bookRecordDTO)));
    }
}
