package com.engx.engxserver.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.engx.engxserver.dto.ChapterDTO;
import com.engx.engxserver.entity.Chapter;
import com.engx.engxserver.entity.ChapterWord;
import com.engx.engxserver.repository.ChapterRepository;
import com.engx.engxserver.repository.ChapterWordRepository;
import com.engx.engxserver.service.base.ChapterService;

import jakarta.transaction.Transactional;

import org.springframework.web.bind.annotation.RequestBody;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/chapters")
public class ChapterController {
  private ChapterService chapterService;

  @GetMapping
  public ResponseEntity<List<ChapterDTO>> getAllChapter() {
    List<ChapterDTO> result = chapterService.getAllChapters();
    return ResponseEntity.ok(result);
  }

  @PostMapping
  public ResponseEntity<ChapterDTO> addChapter(@RequestBody ChapterDTO chapter) {
    ChapterDTO result = chapterService.addChapter(chapter);

    return ResponseEntity.ok(result);
  }
}
