package es.abelfgdeveloper.common.config.exception;

import es.abelfgdeveloper.common.api.v1.resource.response.ErrorResponseResource;
import es.abelfgdeveloper.common.api.v1.resource.response.ErrorResponseResource.ErrorResponseResourceBuilder;
import es.abelfgdeveloper.common.config.exception.mapper.StackTraceMapper;
import es.abelfgdeveloper.common.exception.client.BadRequestException;
import es.abelfgdeveloper.common.exception.client.ConflictException;
import es.abelfgdeveloper.common.exception.client.NotFoundException;
import es.abelfgdeveloper.common.exception.client.ValidationRequestException;
import es.abelfgdeveloper.common.exception.server.ValidationResponseException;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RequiredArgsConstructor
@ControllerAdvice
public class CommonExceptionHandler extends ResponseEntityExceptionHandler {

  private final StackTraceMapper stackTraceMapper;

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<ErrorResponseResource> notFoundExceptionHandler(NotFoundException e) {
    HttpStatus httpStatus = HttpStatus.NOT_FOUND;
    ErrorResponseResource body = generateErrorResponseResource(e, httpStatus);
    return new ResponseEntity<>(body, httpStatus);
  }

  @ExceptionHandler(ConflictException.class)
  public ResponseEntity<ErrorResponseResource> conflictExceptionHandler(ConflictException e) {
    HttpStatus httpStatus = HttpStatus.CONFLICT;
    ErrorResponseResource body = generateErrorResponseResource(e, httpStatus);
    return new ResponseEntity<>(body, httpStatus);
  }

  @ExceptionHandler(BadRequestException.class)
  public ResponseEntity<ErrorResponseResource> badRequestExceptionHandler(BadRequestException e) {
    HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
    ErrorResponseResource body = generateErrorResponseResource(e, httpStatus);
    return new ResponseEntity<>(body, httpStatus);
  }

  @ExceptionHandler(ValidationRequestException.class)
  public ResponseEntity<ErrorResponseResource> validationRequestExceptionHandler(
      ValidationRequestException e) {
    HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
    ErrorResponseResource body = generateErrorResponseResource(e, httpStatus);
    return new ResponseEntity<>(body, httpStatus);
  }

  @ExceptionHandler(ValidationResponseException.class)
  public ResponseEntity<ErrorResponseResource> validationResponseExceptionHandler(
      ValidationResponseException e) {
    HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    ErrorResponseResource body = generateErrorResponseResource(e, httpStatus);
    return new ResponseEntity<>(body, httpStatus);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponseResource> exceptionHandler(Exception e) {
    HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    ErrorResponseResource body = generateErrorResponseResource(e, httpStatus);
    return new ResponseEntity<>(body, httpStatus);
  }

  private ErrorResponseResource generateErrorResponseResource(Throwable e, HttpStatus httpStatus) {
    ErrorResponseResourceBuilder builder =
        ErrorResponseResource.builder()
            .timestamp(LocalDateTime.now())
            .code(e.getMessage())
            .respondeCode(httpStatus.value())
            .status(httpStatus.getReasonPhrase())
            .detail(e.getClass().getSimpleName())
            .stackTrace(stackTraceMapper.map(e.getStackTrace()));
    if (e.getCause() != null) {
      builder.cause(generateErrorResponseResource(e.getCause(), null));
    }
    return builder.build();
  }
}
