package es.abelfgdeveloper.common.exception;

import lombok.Getter;

public class AbelfgDeveloperException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  @Getter private final int errorStatusCode;

  protected AbelfgDeveloperException(int errorStatusCode, String message) {
    super(message);
    this.errorStatusCode = errorStatusCode;
  }

  protected AbelfgDeveloperException(int errorStatusCode, String message, Throwable cause) {
    super(message, cause);
    this.errorStatusCode = errorStatusCode;
  }
}
