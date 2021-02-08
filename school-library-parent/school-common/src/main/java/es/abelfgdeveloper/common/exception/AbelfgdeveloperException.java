package es.abelfgdeveloper.common.exception;

import lombok.Getter;

public class AbelfgdeveloperException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  @Getter private final int errorStatusCode;

  protected AbelfgdeveloperException(int errorStatusCode, String message) {
    super(message);
    this.errorStatusCode = errorStatusCode;
  }

  protected AbelfgdeveloperException(int errorStatusCode, String message, Throwable cause) {
    super(message, cause);
    this.errorStatusCode = errorStatusCode;
  }
}
