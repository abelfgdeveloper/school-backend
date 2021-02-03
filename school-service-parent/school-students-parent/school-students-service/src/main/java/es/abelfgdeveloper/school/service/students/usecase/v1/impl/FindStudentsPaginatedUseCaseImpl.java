package es.abelfgdeveloper.school.service.students.usecase.v1.impl;

import es.abelfgdeveloper.common.domain.PaginationIn;
import es.abelfgdeveloper.school.service.students.domain.StudentPaginated;
import es.abelfgdeveloper.school.service.students.service.StudentRepositoryService;
import es.abelfgdeveloper.school.service.students.usecase.v1.FindStudentsPaginatedUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class FindStudentsPaginatedUseCaseImpl implements FindStudentsPaginatedUseCase {

  private final StudentRepositoryService studentRepositoryService;

  @Transactional(readOnly = true)
  @Override
  public StudentPaginated execute(PaginationIn pagination, String query) {
    return studentRepositoryService.findAll(pagination, query);
  }
}
