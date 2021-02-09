package es.abelfgdeveloper.school.service.students.client.v1.impl;

import es.abelfgdeveloper.school.service.students.api.v1.resource.response.StudentResponseResource;
import es.abelfgdeveloper.school.service.students.client.v1.StudentsClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Component
public class DefaultStudentsClient implements StudentsClient {

  private static final String BASE_URL = "http://school-students-service/students/v1";
  private static final String SLASH = "/";

  private final RestTemplate restTemplate;

  @Override
  public StudentResponseResource findById(String id) {
    String url = new StringBuilder().append(BASE_URL).append(SLASH).append(id).toString();
    return restTemplate.getForObject(url, StudentResponseResource.class);
  }
}
