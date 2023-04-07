package com.denis.monich.mazeapi.service;

import com.denis.monich.mazeapi.dto.MazeDto;

public interface MazeService {

  String findViewById(String id);

  MazeDto findById(String id);

  MazeDto create(MazeDto mazeDto);

  String[][] getMazeDataById(String mazeId);

}
