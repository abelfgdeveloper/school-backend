package es.abelfgdeveloper.school.service.courses.usecase.v1.impl;

import es.abelfgdeveloper.common.exception.client.BadRequestException;
import es.abelfgdeveloper.school.service.courses.domain.Course;
import es.abelfgdeveloper.school.service.courses.service.CourseRepositoryService;
import es.abelfgdeveloper.school.service.courses.usecase.v1.UpdateCourseUseCase;
import es.abelfgdeveloper.school.service.courses.util.CourseErrorCodes;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UpdateCourseUseCaseImpl implements UpdateCourseUseCase {

  private final CourseRepositoryService courseRepositoryService;

  @Transactional
  @Override
  public Course execute(Course course) {
    Optional<Course> courseInDataBase = courseRepositoryService.findByName(course.getName());
    if (courseInDataBase.isPresent()) {
      throw new BadRequestException(CourseErrorCodes.NAME_EXIST);
    }
    return courseRepositoryService.save(course);
  }
}
