package es.abelfgdeveloper.common.exception.server;

import es.abelfgdeveloper.common.exception.AbelfgdeveloperException;

public class InternalServerErrorException extends AbelfgdeveloperException {

  private static final long serialVersionUID = 1L;
  private static final int ERROR_STATUS_CODE = 500;

  public InternalServerErrorException(String message) {
    super(ERROR_STATUS_CODE, message);
  }

  public InternalServerErrorException(String message, Throwable cause) {
    super(ERROR_STATUS_CODE, message, cause);
  }
}
