package com.denis.monich.mazeapi.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ValidationErrorDto {

  private String message;
  private List<FieldErrorDto> fields;

}
