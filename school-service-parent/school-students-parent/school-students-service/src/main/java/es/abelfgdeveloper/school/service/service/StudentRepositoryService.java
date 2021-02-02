package es.abelfgdeveloper.school.service.service;

import es.abelfgdeveloper.common.domain.PaginationIn;
import es.abelfgdeveloper.school.service.domain.Student;
import es.abelfgdeveloper.school.service.domain.StudentPaginated;

public interface StudentRepositoryService {

  Student save(Student student);

  void deleteById(String id);

  Student findById(String id);

  StudentPaginated findAll(PaginationIn pagination, String query);
}
