package com.denis.monich.mazeapi.service.impl;

import com.denis.monich.mazeapi.dto.GameDto;
import com.denis.monich.mazeapi.dto.MazeBlockDto;
import com.denis.monich.mazeapi.dto.MazeGameDto;
import com.denis.monich.mazeapi.dto.OperationType;
import com.denis.monich.mazeapi.exception.MazeException;
import com.denis.monich.mazeapi.model.Game;
import com.denis.monich.mazeapi.repository.GameRepository;
import com.denis.monich.mazeapi.service.GameService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;

import static com.denis.monich.mazeapi.exception.ErrorCode.GAME_DOES_NOT_EXIST;
import static com.denis.monich.mazeapi.exception.ErrorCode.OPERATION_IS_BLOCKED;
import static com.denis.monich.mazeapi.service.impl.MazeServiceImpl.MAZE_BLOCKED_CELL;
import static com.denis.monich.mazeapi.service.impl.MazeServiceImpl.MAZE_FINISH_CELL;

@Slf4j
@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

  private final GameRepository repository;

  @Override
  public GameDto create(String mazeId) {

    Game game = new Game();
    game.setMazeId(mazeId);

    return gameToGameDto(repository.save(game));
  }

  @Override
  public MazeGameDto findById(String gameId) {
    Game game = repository.findById(gameId).orElseThrow(() -> new MazeException(GAME_DOES_NOT_EXIST));
    return new MazeGameDto(gameToGameDto(game));
  }

  @Override
  public MazeGameDto reset(String gameId) {
    Game game = repository.findById(gameId).orElseThrow(() -> new MazeException(GAME_DOES_NOT_EXIST));

    game.setCompleted(false);
    game.setCurrentPositionX(0);
    game.setCurrentPositionY(0);

    game = repository.save(game);

    return new MazeGameDto(gameToGameDto(game));
  }

  @Override
  public MazeGameDto moveNextCell(OperationType operationType, String gameId, String[][] mazeData) {
    Game game = repository.findById(gameId).orElseThrow(() -> new MazeException(GAME_DOES_NOT_EXIST));
    Pair<Integer, Integer> nextCell = getNewCellByDirection(game.getCurrentPositionX(), game.getCurrentPositionY(), operationType);

    if (isCellBlocked(mazeData, nextCell.getLeft(), nextCell.getRight())) {
      throw new MazeException(OPERATION_IS_BLOCKED);
    }

    game.setCurrentPositionX(nextCell.getLeft());
    game.setCurrentPositionY(nextCell.getRight());

    if (isGameComplete(mazeData, game.getCurrentPositionX(), game.getCurrentPositionX())) {
      game.setCompleted(true);
    }

    game = repository.save(game);

    return new MazeGameDto(gameToGameDto(game));
  }

  @Override
  public MazeBlockDto mapMazeBlockDto(GameDto game, String[][] mazeData) {
    MazeBlockDto mazeBlockDto = new MazeBlockDto();

    mazeBlockDto.setCoordX(game.getCurrentPositionX());
    mazeBlockDto.setCoordY(game.getCurrentPositionY());
    mazeBlockDto.setNorthBlocked(isCellBlocked(mazeData, game.getCurrentPositionX(), game.getCurrentPositionY() - 1));
    mazeBlockDto.setSouthBlocked(isCellBlocked(mazeData, game.getCurrentPositionX(), game.getCurrentPositionY() + 1));
    mazeBlockDto.setEastBlocked(isCellBlocked(mazeData, game.getCurrentPositionX() + 1, game.getCurrentPositionY()));
    mazeBlockDto.setWestBlocked(isCellBlocked(mazeData, game.getCurrentPositionX() - 1, game.getCurrentPositionY()));

    return mazeBlockDto;
  }

  private Pair<Integer, Integer> getNewCellByDirection(int x, int y, OperationType operationType) {
    if (operationType == OperationType.GO_NORTH) {
      return Pair.of(x, y - 1);
    } else if (operationType == OperationType.GO_SOUTH) {
      return Pair.of(x, y + 1);
    } else if (operationType == OperationType.GO_EAST) {
      return Pair.of(x + 1, y);
    } else if (operationType == OperationType.GO_WEST) {
      return Pair.of(x - 1, y);
    }
    throw new MazeException(OPERATION_IS_BLOCKED);
  }

  private boolean isGameComplete(String[][] mazeData, int x, int y) {
    return mazeData[x][y].equals(MAZE_FINISH_CELL);
  }

  private boolean isCellBlocked(String[][] mazeData, int x, int y) {
    if (mazeData.length > x && x >= 0) {
      if (mazeData[x].length > y && y >= 0) {
        if (!mazeData[y][x].equals(MAZE_BLOCKED_CELL)) {
          return false;
        }
      }
    }
    return true;
  }

  private GameDto gameToGameDto(Game game) {
    GameDto gameDto = new GameDto();

    gameDto.setGameUid(game.getId());
    gameDto.setCompleted(game.isCompleted());
    gameDto.setMazeId(game.getMazeId());
    gameDto.setCurrentPositionX(game.getCurrentPositionX());
    gameDto.setCurrentPositionY(game.getCurrentPositionY());

    return gameDto;
  }
}
