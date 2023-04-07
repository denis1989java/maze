package com.denis.monich.mazeapi.exception;

public enum ErrorCode {

  // General
  UNKNOWN_ERROR,
  WRONG_REQUEST,
  VALIDATION_ERROR,

  //Game
  GAME_DOES_NOT_EXIST,

  //Maze
  MAZE_DOES_NOT_EXIST,

  //Operation
  OPERATION_IS_BLOCKED,
  OPERATION_DOES_NOT_EXIST

}
