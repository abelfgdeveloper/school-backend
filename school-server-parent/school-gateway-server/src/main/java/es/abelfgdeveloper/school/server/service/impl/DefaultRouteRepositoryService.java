package es.abelfgdeveloper.school.server.service.impl;

import es.abelfgdeveloper.school.server.domain.RouteData;
import es.abelfgdeveloper.school.server.service.RouteRepositoryService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class DefaultRouteRepositoryService implements RouteRepositoryService {

  private static final String SCHOOL_APPLICATION = "school";

  @Override
  public List<RouteData> getRoutes() {
    List<RouteData> routes = new ArrayList<>();
    routes.add(
        RouteData.builder()
            .application(SCHOOL_APPLICATION)
            .microservice("school-students-service")
            .path("students")
            .build());
    routes.add(
        RouteData.builder()
            .application(SCHOOL_APPLICATION)
            .microservice("school-courses-service")
            .path("courses")
            .build());
    return routes;
  }
}
