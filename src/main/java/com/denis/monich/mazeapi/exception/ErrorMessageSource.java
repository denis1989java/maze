package com.denis.monich.mazeapi.exception;

import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class ErrorMessageSource {
  private final ResourceBundleMessageSource errors;

  public ErrorMessageSource() {
    errors = new ResourceBundleMessageSource();
    errors.setBasename("errors");
  }

  public String getMessage(MazeException exception) {
    return errors.getMessage(exception.getErrorCode().name(), exception.getArgs(), Locale.getDefault());
  }

  public String getMessage(ErrorCode errorCode) {
    return errors.getMessage(errorCode.name(), null, Locale.getDefault());
  }

}
