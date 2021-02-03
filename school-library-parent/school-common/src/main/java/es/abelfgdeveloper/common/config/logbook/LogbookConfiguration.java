package es.abelfgdeveloper.common.config.logbook;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zalando.logbook.CorrelationId;

@RequiredArgsConstructor
@Configuration
public class LogbookConfiguration {

  private final CustomCorrelationId customCorrelationId;

  @Bean
  public CorrelationId correlationId() {
    return customCorrelationId;
  }
}
