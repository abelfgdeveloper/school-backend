package es.abelfgdeveloper.school.service.courses.usecase.v1.impl;

import es.abelfgdeveloper.school.service.courses.domain.Course;
import es.abelfgdeveloper.school.service.courses.service.CourseRepositoryService;
import es.abelfgdeveloper.school.service.courses.usecase.v1.FindCourseByIdUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class FindCourseByIdUseCaseImpl implements FindCourseByIdUseCase {

  private final CourseRepositoryService courseRepositoryService;

  @Transactional(readOnly = true)
  @Override
  public Course execute(String id) {
    return courseRepositoryService.findById(id);
  }
}
