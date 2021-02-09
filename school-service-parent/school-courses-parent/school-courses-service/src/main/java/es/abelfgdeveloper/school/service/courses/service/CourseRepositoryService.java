package es.abelfgdeveloper.school.service.courses.service;

import es.abelfgdeveloper.common.domain.PaginationIn;
import es.abelfgdeveloper.school.service.courses.domain.Course;
import es.abelfgdeveloper.school.service.courses.domain.CoursePaginated;
import java.util.List;
import java.util.Optional;

public interface CourseRepositoryService {

  Course save(Course course);

  void deleteById(String id);

  Course findById(String id);

  CoursePaginated findAll(PaginationIn pagination, String query);

  Optional<Course> findByName(String name);

  List<Course> findByStudentId(String studentId);
}
