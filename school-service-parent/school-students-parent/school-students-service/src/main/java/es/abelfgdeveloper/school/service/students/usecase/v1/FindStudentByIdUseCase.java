package es.abelfgdeveloper.school.service.students.usecase.v1;

import es.abelfgdeveloper.school.service.students.domain.Student;

public interface FindStudentByIdUseCase {

  Student execute(String id);
}
