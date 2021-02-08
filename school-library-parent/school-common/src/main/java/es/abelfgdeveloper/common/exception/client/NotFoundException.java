package es.abelfgdeveloper.common.exception.client;

import es.abelfgdeveloper.common.exception.AbelfgDeveloperException;

public class NotFoundException extends AbelfgDeveloperException {

  private static final long serialVersionUID = 1L;
  private static final int ERROR_STATUS_CODE = 404;

  public NotFoundException(String message) {
    super(ERROR_STATUS_CODE, message);
  }

  public NotFoundException(String message, Throwable cause) {
    super(ERROR_STATUS_CODE, message, cause);
  }
}
