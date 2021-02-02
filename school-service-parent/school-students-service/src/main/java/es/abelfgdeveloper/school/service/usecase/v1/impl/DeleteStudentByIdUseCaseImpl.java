package es.abelfgdeveloper.school.service.usecase.v1.impl;

import es.abelfgdeveloper.school.service.service.StudentRepositoryService;
import es.abelfgdeveloper.school.service.usecase.v1.DeleteStudentByIdUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class DeleteStudentByIdUseCaseImpl implements DeleteStudentByIdUseCase {

  private final StudentRepositoryService studentRepositoryService;

  @Transactional
  @Override
  public void execute(String id) {
    studentRepositoryService.deleteById(id);
  }
}
