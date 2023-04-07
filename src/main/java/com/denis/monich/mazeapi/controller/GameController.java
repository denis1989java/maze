package com.denis.monich.mazeapi.controller;

import com.denis.monich.mazeapi.dto.*;
import com.denis.monich.mazeapi.service.GameService;
import com.denis.monich.mazeapi.service.MazeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/game")
@RestController
@RequiredArgsConstructor
public class GameController {

  private final GameService service;
  private final MazeService mazeService;

  // we don't need operation Start payload for this request
  @PostMapping("/{mazeId}")
  public ResponseEntity<GameDto> create(@RequestBody OperationDto operationDto, @PathVariable String mazeId) {
    MazeDto mazeDto = mazeService.findById(mazeId);

    return ResponseEntity.ok(service.create(mazeDto.getMazeUid()));
  }

  @GetMapping("/{mazeId}/{gameId}")
  public ResponseEntity<MazeGameDto> findById(@PathVariable String mazeId, @PathVariable String gameId) {
    MazeGameDto mazeGameDto = service.findById(gameId);
    String[][] mazeData = mazeService.getMazeDataById(mazeId);

    MazeBlockDto mazeBlockDto = service.mapMazeBlockDto(mazeGameDto.getGame(), mazeData);
    mazeGameDto.setMazeBlockView(mazeBlockDto);

    return ResponseEntity.ok(mazeGameDto);
  }

  @PostMapping("/{mazeId}/{gameId}")
  public ResponseEntity<MazeGameDto> doOperation(@RequestBody OperationDto operationDto,
                                                 @PathVariable String mazeId,
                                                 @PathVariable String gameId) {
    String[][] mazeData = mazeService.getMazeDataById(mazeId);
    MazeGameDto mazeGameDto;

    if (operationDto.getOperation() == OperationType.START) {
      mazeGameDto = service.reset(gameId);
    } else {
      mazeGameDto = service.moveNextCell(operationDto.getOperation(), gameId, mazeData);
    }

    MazeBlockDto mazeBlockDto = service.mapMazeBlockDto(mazeGameDto.getGame(), mazeData);
    mazeGameDto.setMazeBlockView(mazeBlockDto);

    return ResponseEntity.ok(mazeGameDto);
  }

}
