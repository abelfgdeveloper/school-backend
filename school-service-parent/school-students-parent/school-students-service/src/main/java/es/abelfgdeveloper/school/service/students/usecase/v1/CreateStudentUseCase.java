package es.abelfgdeveloper.school.service.students.usecase.v1;

import es.abelfgdeveloper.school.service.students.domain.Student;

public interface CreateStudentUseCase {

  Student execute(Student student);
}
