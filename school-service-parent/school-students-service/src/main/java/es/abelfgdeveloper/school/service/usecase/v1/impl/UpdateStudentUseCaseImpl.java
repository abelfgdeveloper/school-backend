package es.abelfgdeveloper.school.service.usecase.v1.impl;

import es.abelfgdeveloper.school.service.domain.Student;
import es.abelfgdeveloper.school.service.service.StudentRepositoryService;
import es.abelfgdeveloper.school.service.usecase.v1.UpdateStudentUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UpdateStudentUseCaseImpl implements UpdateStudentUseCase {

  private final StudentRepositoryService studentRepositoryService;

  @Transactional
  @Override
  public Student execute(Student student) {
    return studentRepositoryService.save(student);
  }
}
