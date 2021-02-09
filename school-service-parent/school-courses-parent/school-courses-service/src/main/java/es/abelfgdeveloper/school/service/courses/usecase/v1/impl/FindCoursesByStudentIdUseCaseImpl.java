package es.abelfgdeveloper.school.service.courses.usecase.v1.impl;

import es.abelfgdeveloper.school.service.courses.domain.Course;
import es.abelfgdeveloper.school.service.courses.service.CourseRepositoryService;
import es.abelfgdeveloper.school.service.courses.usecase.v1.FindCoursesByStudentIdUseCase;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class FindCoursesByStudentIdUseCaseImpl implements FindCoursesByStudentIdUseCase {

  private final CourseRepositoryService courseRepositoryService;

  @Transactional(readOnly = true)
  @Override
  public List<Course> execute(String studentId) {
    return courseRepositoryService.findByStudentId(studentId);
  }
}
