package io.github.marcinczeczko.model;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class ErrorResponse {

  private String message;
  private int errorCode;

  public ErrorResponse(String message, int errorCode) {
    this.message = message;
    this.errorCode = errorCode;
  }

  public String getMessage() {
    return message;
  }

  public int getErrorCode() {
    return errorCode;
  }
}
