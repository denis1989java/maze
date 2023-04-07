package com.denis.monich.mazeapi.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MazeException extends RuntimeException {

  private final ErrorCode errorCode;
  private final Object[] args;

  public MazeException(ErrorCode errorCode) {
    super(errorCode.name());
    this.errorCode = errorCode;
    args = null;
  }

  public MazeException(ErrorCode errorCode, List<Object> args) {
    super(errorCode.name());
    this.errorCode = errorCode;
    this.args = args.toArray();
  }

}