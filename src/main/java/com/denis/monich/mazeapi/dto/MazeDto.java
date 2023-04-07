package com.denis.monich.mazeapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MazeDto {

  private String mazeUid;
  private Integer width;
  private Integer height;

}
