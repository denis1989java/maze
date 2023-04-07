package com.denis.monich.mazeapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Maze {

  @Id
  @GeneratedValue(generator = "MAZE_SEQ")
  @GenericGenerator(name = "MAZE_SEQ", strategy = "uuid")
  private String id;

  private int width;
  private int height;
  @Column(columnDefinition = "TEXT")
  private String data;

}
