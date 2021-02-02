package es.abelfgdeveloper.common.exception.client;

public class ValidationRequestException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public ValidationRequestException(String message) {
    super(message);
  }

  public ValidationRequestException(String message, Throwable cause) {
    super(message, cause);
  }
}
