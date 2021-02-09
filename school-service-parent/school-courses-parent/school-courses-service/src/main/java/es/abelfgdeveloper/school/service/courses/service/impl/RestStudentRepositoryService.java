package es.abelfgdeveloper.school.service.courses.service.impl;

import es.abelfgdeveloper.school.service.courses.service.StudentRepositoryService;
import es.abelfgdeveloper.school.service.students.api.v1.resource.response.StudentResponseResource;
import es.abelfgdeveloper.school.service.students.client.v1.StudentsClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Slf4j
@RequiredArgsConstructor
@Service
public class RestStudentRepositoryService implements StudentRepositoryService {

  private final StudentsClient studentsClient;

  @Override
  public boolean checkIfStudentExist(String id) {
    StudentResponseResource response = null;
    try {
      response = studentsClient.findById(id);
    } catch (HttpClientErrorException e) {
      log.warn("Error when call to studentsClient.findById");
      return false;
    }
    return (response != null && response.getId() != null && response.getId().equals(id));
  }
}
