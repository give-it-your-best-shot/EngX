package com.engx.engxserver.controller;

import com.engx.engxserver.dto.AddChapterRequestDTO;
import com.engx.engxserver.dto.ChapterDTO;
import com.engx.engxserver.exception.InsertFailException;
import com.engx.engxserver.service.base.AuthenticationService;
import com.engx.engxserver.service.base.MaterialService;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/chapters")
public class MaterialController {
    private MaterialService chapterService;
    private AuthenticationService authenticationService;

    @GetMapping
    public ResponseEntity<List<ChapterDTO>> getAllChapter() {
        List<ChapterDTO> result = chapterService.getAllChapters();
        return ResponseEntity.ok(result);
    }

    // @GetMapping("/{id}")
    // public ResponseEntity<ResponseWithUser<ChapterDTO>> getChapterWithId(@PathVariable Long id)
    //         throws ResourceNotFoundException {
    //     ChapterDTO result = chapterService.getChapterById(id);
    //     ResponseWithUser<ChapterDTO> responseWithUser =
    //             new ResponseWithUser<>(authenticationService.getAuthenticatedUser(), result);
    //     return ResponseEntity.ok(responseWithUser);
    // }

    @PostMapping
    public ResponseEntity<ChapterDTO> addChapter(HttpServletRequest request, @RequestBody AddChapterRequestDTO chapter)
            throws InsertFailException {
        ChapterDTO result = chapterService.addChapter(chapter);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}
