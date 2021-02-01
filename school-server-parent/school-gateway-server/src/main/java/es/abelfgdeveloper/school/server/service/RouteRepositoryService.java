package es.abelfgdeveloper.school.server.service;

import es.abelfgdeveloper.school.server.domain.RouteData;
import java.util.List;

public interface RouteRepositoryService {

  List<RouteData> getRoutes();
}
