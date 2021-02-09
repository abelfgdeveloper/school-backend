package es.abelfgdeveloper.school.service.courses.usecase.v1.impl;

import es.abelfgdeveloper.common.exception.client.BadRequestException;
import es.abelfgdeveloper.school.service.courses.domain.Course;
import es.abelfgdeveloper.school.service.courses.service.CourseRepositoryService;
import es.abelfgdeveloper.school.service.courses.usecase.v1.RemoveStudentFromCourseUseCase;
import es.abelfgdeveloper.school.service.courses.util.CourseErrorCodes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class RemoveStudentFromCourseUseCaseImpl implements RemoveStudentFromCourseUseCase {

  private final CourseRepositoryService courseRepositoryService;

  @Transactional
  @Override
  public Course execute(String courseId, String studentId) {
    Course course = courseRepositoryService.findById(courseId);
    checkIfStudentIsPresent(course, studentId);
    course.getStudents().remove(studentId);
    return courseRepositoryService.save(course);
  }

  private void checkIfStudentIsPresent(Course course, String studentId) {
    if (!course.getStudents().contains(studentId)) {
      throw new BadRequestException(CourseErrorCodes.STUDENT_ID_NOT_EXIST);
    }
  }
}
