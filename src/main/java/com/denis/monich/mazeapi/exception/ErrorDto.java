package com.denis.monich.mazeapi.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ErrorDto {

  private ErrorCode code;
  private String message;
  private Object[] args;

  public ErrorDto(ErrorCode code, String message) {
    this.code = code;
    this.message = message;
  }

}
