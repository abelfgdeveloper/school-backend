package es.abelfgdeveloper.school.service.courses.usecase.v1;

import es.abelfgdeveloper.school.service.courses.domain.Course;

public interface FindCourseByIdUseCase {

  Course execute(String id);
}
