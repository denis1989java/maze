package com.denis.monich.mazeapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GameDto {

  private String gameUid;
  private String mazeId;
  private Boolean completed;
  private Integer currentPositionX;
  private Integer currentPositionY;

}
