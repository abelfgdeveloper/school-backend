package es.abelfgdeveloper.school.service.courses.usecase.v1;

import es.abelfgdeveloper.school.service.courses.domain.Course;
import java.util.List;

public interface FindCoursesByStudentIdUseCase {

  List<Course> execute(String studentId);
}
