package es.abelfgdeveloper.common.config.communication;

import java.time.Duration;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.zalando.logbook.spring.LogbookClientHttpRequestInterceptor;

@RequiredArgsConstructor
@Configuration
public class CommunicationBeans {

  @Bean
  @LoadBalanced
  public RestTemplate defaultRestTemplate(
      RestTemplateBuilder builder, LogbookClientHttpRequestInterceptor interceptor) {
    return builder
        .setConnectTimeout(Duration.ofSeconds(3))
        .setReadTimeout(Duration.ofSeconds(3))
        //        .additionalInterceptors(interceptor)
        .build();
  }
}
