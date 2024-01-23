package com.engx.engxserver.controller;

import com.engx.engxserver.dto.AddBookRequestDTO;
import com.engx.engxserver.dto.AddUnitRequestDTO;
import com.engx.engxserver.dto.AddWordRequestDTO;
import com.engx.engxserver.dto.BookDTO;
import com.engx.engxserver.dto.ResponseSuccess;
import com.engx.engxserver.dto.UnitDTO;
import com.engx.engxserver.dto.WordDTO;
import com.engx.engxserver.exception.InsertFailException;
import com.engx.engxserver.exception.ResourceNotFoundException;
import com.engx.engxserver.service.base.MaterialService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/materials")
public class MaterialController {
    private MaterialService materialService;

    @GetMapping("/books/{bookId}/units")
    public ResponseEntity<ResponseSuccess<List<UnitDTO>>> getAllUnitsOfBook(@PathVariable Long bookId)
            throws ResourceNotFoundException {
        List<UnitDTO> result = materialService.getAllUnitsOfBook(bookId);
        return ResponseEntity.ok(new ResponseSuccess<>(result));
    }

    @GetMapping("/units/{unitId}/words")
    public ResponseEntity<ResponseSuccess<List<WordDTO>>> getAllWordsOfUnit(@PathVariable Long unitId)
            throws ResourceNotFoundException {
        List<WordDTO> result = materialService.getAllWordsOfUnit(unitId);
        return ResponseEntity.ok(new ResponseSuccess<>(result));
    }

    @GetMapping("/owner/{ownerId}/books")
    public ResponseEntity<ResponseSuccess<List<BookDTO>>> getAllBooksOfOwner(@PathVariable Long ownerId)
            throws ResourceNotFoundException {
        List<BookDTO> result = materialService.getAllBooksOfOwner(ownerId);
        return ResponseEntity.ok(new ResponseSuccess<>(result));
    }

    @PostMapping("/books")
    public ResponseEntity<ResponseSuccess<BookDTO>> addBook(@RequestBody AddBookRequestDTO addBookRequestDTO)
            throws InsertFailException {
        return ResponseEntity.ok(new ResponseSuccess<>(materialService.addBook(addBookRequestDTO)));
    }

    @PostMapping("/units")
    public ResponseEntity<ResponseSuccess<UnitDTO>> addUnit(@RequestBody AddUnitRequestDTO addUnitRequestDTO)
            throws InsertFailException {
        return ResponseEntity.ok(new ResponseSuccess<>(materialService.addUnit(addUnitRequestDTO)));
    }

    @PostMapping("/words")
    public ResponseEntity<ResponseSuccess<WordDTO>> addWord(@RequestBody AddWordRequestDTO addWordRequestDTO)
            throws InsertFailException {
        return ResponseEntity.ok(new ResponseSuccess<>(materialService.addWord(addWordRequestDTO)));
    }
}
