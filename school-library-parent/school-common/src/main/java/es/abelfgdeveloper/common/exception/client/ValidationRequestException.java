package es.abelfgdeveloper.common.exception.client;

import es.abelfgdeveloper.common.exception.AbelfgDeveloperException;

public class ValidationRequestException extends AbelfgDeveloperException {

  private static final long serialVersionUID = 1L;
  private static final int ERROR_STATUS_CODE = 400;

  public ValidationRequestException(String message) {
    super(ERROR_STATUS_CODE, message);
  }

  public ValidationRequestException(String message, Throwable cause) {
    super(ERROR_STATUS_CODE, message, cause);
  }
}
