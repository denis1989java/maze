package com.denis.monich.mazeapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MazeBlockDto {

  private int coordX;
  private int coordY;
  private boolean northBlocked;
  private boolean southBlocked;
  private boolean westBlocked;
  private boolean eastBlocked;

}
