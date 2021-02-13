package es.abelfgdeveloper.school.service.courses.service.impl;

import es.abelfgdeveloper.school.service.courses.service.StudentRepositoryService;
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
    try {
      log.info("Try call to studentsClient.findById");
      studentsClient.findById(id);
      log.info("Success call to studentsClient.findById");
    } catch (HttpClientErrorException e) {
      log.warn("Error when call to studentsClient.findById");
      return false;
    }
    return true;
  }
}
