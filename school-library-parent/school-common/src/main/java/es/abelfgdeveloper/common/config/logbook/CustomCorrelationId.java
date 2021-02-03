package es.abelfgdeveloper.common.config.logbook;

import brave.Tracer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.zalando.logbook.CorrelationId;
import org.zalando.logbook.HttpRequest;

@RequiredArgsConstructor
@Component
public final class CustomCorrelationId implements CorrelationId {

  private final Tracer tracer;

  @Override
  public String generate(final HttpRequest request) {
    return getTraceId();
  }

  protected String getTraceId() {
    String traceId = "";
    if (tracer != null
        && tracer.currentSpan() != null
        && tracer.currentSpan().context() != null
        && tracer.currentSpan().context().traceIdString() != null) {
      traceId = tracer.currentSpan().context().traceIdString();
    }
    return traceId;
  }
}
