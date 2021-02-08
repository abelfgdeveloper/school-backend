package es.abelfgdeveloper.common.exception.client;

import es.abelfgdeveloper.common.exception.AbelfgDeveloperException;

public class ConflictException extends AbelfgDeveloperException {

  private static final long serialVersionUID = 1L;
  private static final int ERROR_STATUS_CODE = 409;

  public ConflictException(String message) {
    super(ERROR_STATUS_CODE, message);
  }

  public ConflictException(String message, Throwable cause) {
    super(ERROR_STATUS_CODE, message, cause);
  }
}
