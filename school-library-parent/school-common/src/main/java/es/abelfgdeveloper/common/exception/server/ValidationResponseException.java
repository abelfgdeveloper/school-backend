package es.abelfgdeveloper.common.exception.server;

import es.abelfgdeveloper.common.exception.AbelfgDeveloperException;

public class ValidationResponseException extends AbelfgDeveloperException {

  private static final long serialVersionUID = 1L;
  private static final int ERROR_STATUS_CODE = 500;

  public ValidationResponseException(String message) {
    super(ERROR_STATUS_CODE, message);
  }

  public ValidationResponseException(String message, Throwable cause) {
    super(ERROR_STATUS_CODE, message, cause);
  }
}
