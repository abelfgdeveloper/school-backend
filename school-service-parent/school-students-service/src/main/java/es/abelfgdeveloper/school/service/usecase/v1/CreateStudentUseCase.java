package es.abelfgdeveloper.school.service.usecase.v1;

import es.abelfgdeveloper.school.service.domain.Student;

public interface CreateStudentUseCase {

  Student execute(Student student);
}
