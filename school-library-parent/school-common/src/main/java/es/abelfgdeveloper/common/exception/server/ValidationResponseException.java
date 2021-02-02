package es.abelfgdeveloper.common.exception.server;

public class ValidationResponseException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public ValidationResponseException(String message) {
    super(message);
  }

  public ValidationResponseException(String message, Throwable cause) {
    super(message, cause);
  }
}
