package es.abelfgdeveloper.school.service.students.usecase.v1.impl;

import es.abelfgdeveloper.school.service.students.domain.Student;
import es.abelfgdeveloper.school.service.students.service.StudentRepositoryService;
import es.abelfgdeveloper.school.service.students.usecase.v1.CreateStudentUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CreateStudentUseCaseImpl implements CreateStudentUseCase {

  private final StudentRepositoryService studentRepositoryService;

  @Transactional
  @Override
  public Student execute(Student student) {
    return studentRepositoryService.save(student);
  }
}
