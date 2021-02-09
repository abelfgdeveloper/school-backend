package es.abelfgdeveloper.school.service.courses.usecase.v1.impl;

import es.abelfgdeveloper.school.service.courses.service.CourseRepositoryService;
import es.abelfgdeveloper.school.service.courses.usecase.v1.DeleteCourseByIdUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class DeleteCourseByIdUseCaseImpl implements DeleteCourseByIdUseCase {

  private final CourseRepositoryService courseRepositoryService;

  @Transactional
  @Override
  public void execute(String id) {
    courseRepositoryService.deleteById(id);
  }
}
