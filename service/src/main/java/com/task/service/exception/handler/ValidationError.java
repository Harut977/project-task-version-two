package com.task.service.exception.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ValidationError implements ErrorCode {
  VALIDATION_ERROR(HttpStatus.BAD_REQUEST);

  private HttpStatus status;

  @Override
  public String getName() {
    return name();
  }

  @Override
  public HttpStatus getStatus() {
    return status;
  }
}
