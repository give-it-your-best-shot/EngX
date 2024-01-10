package com.engx.engxserver.controller;

import java.util.List;
import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chapters")
public class ChapterController {
  @GetMapping
  public ResponseEntity<List<String>> getAllChapter() {
    List<String> result = new ArrayList<String>();
    result.add("Hello");
    result.add("World");
    return ResponseEntity.ok(result);
  }
}
