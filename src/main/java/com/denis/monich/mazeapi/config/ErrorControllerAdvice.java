package com.denis.monich.mazeapi.config;


import com.denis.monich.mazeapi.exception.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestControllerAdvice
@Slf4j
public class ErrorControllerAdvice {

  private final ErrorMessageSource errorMessageSource;

  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ValidationErrorDto methodArgumentNotValidHandler(MethodArgumentNotValidException exception) {
    return new ValidationErrorDto(errorMessageSource.getMessage(ErrorCode.VALIDATION_ERROR),
            exception.getBindingResult().getFieldErrors().stream()
                    .map(e -> new FieldErrorDto(e.getField(), e.getCode()))
                    .collect(Collectors.toList()));
  }

  @ExceptionHandler(MazeException.class)
  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorDto handleScoreBoxException(MazeException ex) {
    return new ErrorDto(ex.getErrorCode(), errorMessageSource.getMessage(ex), ex.getArgs());
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorDto handleUnexpectedError(Exception ex) {
    log.error("Unhandled exception", ex);
    return new ErrorDto(ErrorCode.UNKNOWN_ERROR, ex.getMessage());
  }
}
