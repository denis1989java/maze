package com.denis.monich.mazeapi.controller;

import com.denis.monich.mazeapi.dto.MazeDto;
import com.denis.monich.mazeapi.service.MazeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/maze")
@RestController
@RequiredArgsConstructor
public class MazeController {

  private final MazeService service;

  @GetMapping("/view/{id}")
  public ResponseEntity<String> viewById(@PathVariable String id) {
    return ResponseEntity.ok(service.findViewById(id));
  }

  @GetMapping("/{id}")
  public ResponseEntity<MazeDto> findById(@PathVariable String id) {
    return ResponseEntity.ok(service.findById(id));
  }

  @PostMapping
  public ResponseEntity<MazeDto> create(@RequestBody MazeDto dto) {
    return ResponseEntity.ok(service.create(dto));
  }
}
