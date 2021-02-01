package es.abelfgdeveloper.school.server.service.impl;

import es.abelfgdeveloper.school.server.domain.RouteData;
import es.abelfgdeveloper.school.server.service.RouteRepositoryService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class DefaultRouteRepositoryService implements RouteRepositoryService {

  @Override
  public List<RouteData> getRoutes() {
    List<RouteData> routes = new ArrayList<>();
    return routes;
  }
}
