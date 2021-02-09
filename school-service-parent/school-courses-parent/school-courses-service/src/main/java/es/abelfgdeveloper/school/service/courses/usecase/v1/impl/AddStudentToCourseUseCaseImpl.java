package es.abelfgdeveloper.school.service.courses.usecase.v1.impl;

import es.abelfgdeveloper.common.exception.client.BadRequestException;
import es.abelfgdeveloper.common.exception.client.ConflictException;
import es.abelfgdeveloper.school.service.courses.domain.Course;
import es.abelfgdeveloper.school.service.courses.service.CourseRepositoryService;
import es.abelfgdeveloper.school.service.courses.service.StudentRepositoryService;
import es.abelfgdeveloper.school.service.courses.usecase.v1.AddStudentToCourseUseCase;
import es.abelfgdeveloper.school.service.courses.util.CourseErrorCodes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AddStudentToCourseUseCaseImpl implements AddStudentToCourseUseCase {

  private final CourseRepositoryService courseRepositoryService;
  private final StudentRepositoryService studentRepositoryService;

  @Transactional
  @Override
  public Course execute(String courseId, String studentId) {
    Course course = courseRepositoryService.findById(courseId);
    checkIfStudentIsPresent(course, studentId);
    checkIfStudentExist(studentId);
    course.getStudents().add(studentId);
    return courseRepositoryService.save(course);
  }

  private void checkIfStudentIsPresent(Course course, String studentId) {
    if (course.getStudents().contains(studentId)) {
      throw new ConflictException(CourseErrorCodes.STUDENT_ID_EXIST);
    }
  }

  private void checkIfStudentExist(String studentId) {
    if (!studentRepositoryService.checkIfStudentExist(studentId)) {
      throw new BadRequestException(CourseErrorCodes.STUDENT_ID_NOT_EXIST);
    }
  }
}
