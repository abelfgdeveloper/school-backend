package es.abelfgdeveloper.school.service.courses.usecase.v1;

import es.abelfgdeveloper.school.service.courses.domain.Course;

public interface RemoveStudentFromCourseUseCase {

  Course execute(String courseId, String studentId);
}
