package com.denis.monich.mazeapi.dto;

import com.denis.monich.mazeapi.util.RandomUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;

import static com.denis.monich.mazeapi.service.impl.MazeServiceImpl.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MazeDataDto {

  private int rows;
  private int columns;
  private String[][] grid;

  public MazeDataDto(int rows, int columns) {
    this.rows = rows;
    this.columns = columns;

    grid = new String[rows][columns];

    fillByEmptyCells();
    randomlyFill();
    setStartAndGoalCells();
  }

  private void setStartAndGoalCells() {
    grid[0][0] = MAZE_START_CELL;
    grid[rows - 1][columns - 1] = MAZE_FINISH_CELL;
  }

  private void fillByEmptyCells() {
    for (String[] row : grid) {
      Arrays.fill(row, MAZE_EMPTY_CELL);
    }
  }

  private void randomlyFill() {
    int startFillingRow = RandomUtil.getRandomNumber(0, 1);
    for (int row = startFillingRow; row < rows; row = row + 2) {
      int emptyRowCell = RandomUtil.getRandomNumber(0, columns);
      for (int column = 0; column < columns; column++) {
        if (column != emptyRowCell) {
          grid[row][column] = MAZE_BLOCKED_CELL;
        }
      }
    }
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (String[] row : grid) {
      for (String cell : row) {
        sb.append(cell);
      }
      sb.append(System.lineSeparator());
    }
    return sb.toString();
  }

  public static String view(String[][] grid) {
    StringBuilder sb = new StringBuilder();
    for (String[] row : grid) {
      for (String cell : row) {
        sb.append(cell);
      }
      sb.append(System.lineSeparator());
    }
    return sb.toString();
  }

  public String mapToDbType() {
    String[] stringData = new String[grid.length];
    for (int i = 0; i < grid.length; i++) {
      stringData[i] = String.join(MAZE_LINE_SEPARATOR, grid[i]);
    }
    return String.join(MAZE_SEPARATOR, stringData);
  }

}
