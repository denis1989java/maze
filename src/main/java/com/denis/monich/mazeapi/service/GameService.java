package com.denis.monich.mazeapi.service;

import com.denis.monich.mazeapi.dto.GameDto;
import com.denis.monich.mazeapi.dto.MazeBlockDto;
import com.denis.monich.mazeapi.dto.MazeGameDto;
import com.denis.monich.mazeapi.dto.OperationType;

public interface GameService {

  GameDto create(String mazeId);

  MazeGameDto findById(String gameId);

  MazeGameDto reset(String gameId);

  MazeGameDto moveNextCell(OperationType operationType, String gameId, String[][] mazeData);

  MazeBlockDto mapMazeBlockDto(GameDto game, String[][] mazeData);

}
