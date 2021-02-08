package es.abelfgdeveloper.common.config.exception;

import es.abelfgdeveloper.common.api.v1.resource.response.ErrorResponseResource;
import es.abelfgdeveloper.common.api.v1.resource.response.ErrorResponseResource.ErrorResponseResourceBuilder;
import es.abelfgdeveloper.common.config.exception.mapper.StackTraceMapper;
import es.abelfgdeveloper.common.exception.AbelfgDeveloperException;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RequiredArgsConstructor
@ControllerAdvice
public class CommonExceptionHandler extends ResponseEntityExceptionHandler {

  private final StackTraceMapper stackTraceMapper;

  @ExceptionHandler(AbelfgDeveloperException.class)
  public ResponseEntity<ErrorResponseResource> abelfgdeveloperExceptionHandler(
      AbelfgDeveloperException e) {
    HttpStatus httpStatus = HttpStatus.valueOf(e.getErrorStatusCode());
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
            .detail(e.getClass().getSimpleName())
            .stackTrace(stackTraceMapper.map(e.getStackTrace()));
    if (httpStatus != null) {
      builder.respondeCode(httpStatus.value());
      builder.status(httpStatus.getReasonPhrase());
      if (httpStatus.is5xxServerError()) {
        builder.code("000000000");
        log.error("Exception: {}", e);
      } else {
        log.warn("Exception: {}", e);
      }
    }

    if (e.getCause() != null) {
      builder.cause(generateErrorResponseResource(e.getCause(), null));
    }
    return builder.build();
  }
}
