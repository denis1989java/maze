package com.denis.monich.mazeapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MazeGameDto {

  private GameDto game;
  private MazeBlockDto mazeBlockView;

  public MazeGameDto(GameDto game) {
    this.game = game;
  }
}
