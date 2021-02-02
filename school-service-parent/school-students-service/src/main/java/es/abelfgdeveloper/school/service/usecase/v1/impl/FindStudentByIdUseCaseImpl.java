package es.abelfgdeveloper.school.service.usecase.v1.impl;

import es.abelfgdeveloper.school.service.domain.Student;
import es.abelfgdeveloper.school.service.service.StudentRepositoryService;
import es.abelfgdeveloper.school.service.usecase.v1.FindStudentByIdUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class FindStudentByIdUseCaseImpl implements FindStudentByIdUseCase {

  private final StudentRepositoryService studentRepositoryService;

  @Transactional(readOnly = true)
  @Override
  public Student execute(String id) {
    return studentRepositoryService.findById(id);
  }
}
