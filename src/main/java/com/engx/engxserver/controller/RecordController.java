package com.engx.engxserver.controller;

import com.engx.engxserver.dto.AddBookRecordRequestDTO;
import com.engx.engxserver.dto.AddUnitRecordRequestDTO;
import com.engx.engxserver.dto.BookRecordDTO;
import com.engx.engxserver.dto.ResponseSuccess;
import com.engx.engxserver.dto.UnitRecordDTO;
import com.engx.engxserver.service.base.RecordService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/records")
@RequiredArgsConstructor
public class RecordController {

    private final RecordService recordService;

    @PostMapping("/books")
    public ResponseEntity<ResponseSuccess<BookRecordDTO>> addBookRecord(
            @RequestBody AddBookRecordRequestDTO addBookRecordRequestDTO) {
        return ResponseEntity.ok(new ResponseSuccess<>(recordService.addOneBookRecord(addBookRecordRequestDTO)));
    }

    @PostMapping("/units")
    public ResponseEntity<ResponseSuccess<UnitRecordDTO>> addUnitRecord(
            @RequestBody AddUnitRecordRequestDTO addUnitRecordRequestDTO) {
        return ResponseEntity.ok(new ResponseSuccess<>(recordService.addOneUnitRecord(addUnitRecordRequestDTO)));
    }

    @GetMapping("/users/{userId}/books")
    public ResponseEntity<ResponseSuccess<List<BookRecordDTO>>> getBookRecordsOfUser(@PathVariable Long userId) {
        return ResponseEntity.ok(new ResponseSuccess<>(recordService.getBookRecordsOfUser(userId)));
    }

    @GetMapping("/users/{userId}/units")
    public ResponseEntity<ResponseSuccess<List<UnitRecordDTO>>> getUnitRecordsOfUser(@PathVariable Long userId) {
        return ResponseEntity.ok(new ResponseSuccess<>(recordService.getUnitRecordsOfUser(userId)));
    }
}
