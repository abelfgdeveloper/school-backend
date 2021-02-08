package es.abelfgdeveloper.common.exception.server;

import es.abelfgdeveloper.common.exception.AbelfgdeveloperException;

public class ValidationResponseException extends AbelfgdeveloperException {

  private static final long serialVersionUID = 1L;
  private static final int ERROR_STATUS_CODE = 400;

  public ValidationResponseException(String message) {
    super(ERROR_STATUS_CODE, message);
  }

  public ValidationResponseException(String message, Throwable cause) {
    super(ERROR_STATUS_CODE, message, cause);
  }
}
