package com.denis.monich.mazeapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Game {

  @Id
  @GeneratedValue(generator = "GAME_SEQ")
  @GenericGenerator(name = "GAME_SEQ", strategy = "uuid")
  private String id;

  private String mazeId;
  private boolean completed;
  private int currentPositionX;
  private int currentPositionY;

}
