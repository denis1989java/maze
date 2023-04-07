package com.denis.monich.mazeapi.service.impl;

import com.denis.monich.mazeapi.dto.MazeDataDto;
import com.denis.monich.mazeapi.dto.MazeDto;
import com.denis.monich.mazeapi.exception.MazeException;
import com.denis.monich.mazeapi.model.Maze;
import com.denis.monich.mazeapi.repository.MazeRepository;
import com.denis.monich.mazeapi.service.MazeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import static com.denis.monich.mazeapi.exception.ErrorCode.MAZE_DOES_NOT_EXIST;

@Slf4j
@Service
@RequiredArgsConstructor
public class MazeServiceImpl implements MazeService {

  public final static String MAZE_EMPTY_CELL = StringUtils.SPACE;
  public final static String MAZE_BLOCKED_CELL = "X";
  public final static String MAZE_START_CELL = "S";
  public final static String MAZE_FINISH_CELL = "F";
  public final static String MAZE_LINE_SEPARATOR = ",";
  public final static String MAZE_SEPARATOR = ";";

  private final MazeRepository repository;

  @Override
  public String findViewById(String id) {
    Maze maze = repository.findById(id).orElseThrow(() -> new MazeException(MAZE_DOES_NOT_EXIST));
    return MazeDataDto.view(stringMazeDataToArray(maze.getData()));
  }

  @Override
  public MazeDto findById(String id) {
    Maze maze = repository.findById(id).orElseThrow(() -> new MazeException(MAZE_DOES_NOT_EXIST));
    return mazeToMazeDto(maze);
  }

  @Override
  public MazeDto create(MazeDto mazeDto) {
    Maze maze = mazeDtoToMaze(mazeDto);

    MazeDataDto mazeDataDto = new MazeDataDto(mazeDto.getWidth(), mazeDto.getHeight());
    maze.setData(mazeDataDto.mapToDbType());

    return mazeToMazeDto(repository.save(maze));
  }

  @Override
  public String[][] getMazeDataById(String mazeId) {
    Maze maze = repository.findById(mazeId).orElseThrow(() -> new MazeException(MAZE_DOES_NOT_EXIST));
    return stringMazeDataToArray(maze.getData());
  }

  private String[][] stringMazeDataToArray(String mazeData) {
    String[] rows = mazeData.split(MAZE_SEPARATOR);

    String[][] arrayMazeData = new String[rows.length][rows[0].split(MAZE_LINE_SEPARATOR).length];

    for (int i = 0; i < rows.length; i++) {
      String[] cells = rows[i].split(MAZE_LINE_SEPARATOR);
      for (int j = 0; j < cells.length; j++) {
        arrayMazeData[i][j] = cells[j];
      }
    }

    return arrayMazeData;
  }

  private Maze mazeDtoToMaze(MazeDto mazeDto) {
    Maze maze = new Maze();

    maze.setHeight(mazeDto.getHeight());
    maze.setWidth(mazeDto.getWidth());

    return maze;
  }


  private MazeDto mazeToMazeDto(Maze maze) {
    MazeDto mazeDto = new MazeDto();

    mazeDto.setMazeUid(maze.getId());
    mazeDto.setHeight(maze.getHeight());
    mazeDto.setWidth(maze.getWidth());

    return mazeDto;
  }

}
