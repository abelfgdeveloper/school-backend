package es.abelfgdeveloper.common.exception.client;

import es.abelfgdeveloper.common.exception.AbelfgdeveloperException;

public class ConflictException extends AbelfgdeveloperException {

  private static final long serialVersionUID = 1L;
  private static final int ERROR_STATUS_CODE = 400;

  public ConflictException(String message) {
    super(ERROR_STATUS_CODE, message);
  }

  public ConflictException(String message, Throwable cause) {
    super(ERROR_STATUS_CODE, message, cause);
  }
}
