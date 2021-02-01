package es.abelfgdeveloper.school.server.config;

import brave.Tracer;
import es.abelfgdeveloper.school.server.domain.RouteData;
import es.abelfgdeveloper.school.server.service.RouteRepositoryService;
import java.util.List;
import java.util.StringJoiner;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.GatewayFilterSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder.Builder;
import org.springframework.cloud.gateway.route.builder.UriSpec;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Configuration
public class RoutesConfiguration {

  private static final String API_PREFIX = "api";
  private static final String LOAD_BALANCER = "lb://";
  private static final int NUMBER_PARTS_PATH_REMOVE = 2;

  private final RouteRepositoryService routeRepositoryService;
  private final Tracer tracer;

  @Bean
  public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
    Builder routeBuilder = builder.routes();
    List<RouteData> routes = routeRepositoryService.getRoutes();
    for (RouteData routeData : routes) {
      addPublicRoute(routeBuilder, routeData);
    }
    return routeBuilder.build();
  }

  private void addPublicRoute(Builder routeBuilder, RouteData routeData) {
    routeBuilder.route(
        generateRouteId(routeData),
        r ->
            r.path(generatePattern(routeData))
                .filters(getFilters())
                .uri(LOAD_BALANCER + routeData.getMicroservice()));
  }

  private String generateRouteId(RouteData routeData) {
    StringJoiner stringJoiner = new StringJoiner("-");
    stringJoiner.add(routeData.getApplication());
    stringJoiner.add(routeData.getMicroservice());
    stringJoiner.add(routeData.getPath());
    return stringJoiner.toString();
  }

  private String generatePattern(RouteData routeData) {
    String delimiter = "/";
    StringJoiner stringJoiner = new StringJoiner(delimiter);
    stringJoiner.add(API_PREFIX);
    stringJoiner.add(routeData.getApplication());
    stringJoiner.add(routeData.getPath());
    stringJoiner.add("**");
    return delimiter + stringJoiner.toString();
  }

  private Function<GatewayFilterSpec, UriSpec> getFilters() {
    return f ->
        f.preserveHostHeader().filter(this::addHeaders).stripPrefix(NUMBER_PARTS_PATH_REMOVE);
  }

  private Mono<Void> addHeaders(ServerWebExchange exchange, GatewayFilterChain chain) {
    org.springframework.util.MultiValueMap<String, String> map = new HttpHeaders();
    map.add("trace-id", getTraceId());
    exchange.getResponse().getHeaders().addAll(map);
    return chain.filter(exchange);
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
