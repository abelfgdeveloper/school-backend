package es.abelfgdeveloper.school.service.courses.usecase.v1.impl;

import es.abelfgdeveloper.common.domain.PaginationIn;
import es.abelfgdeveloper.school.service.courses.domain.CoursePaginated;
import es.abelfgdeveloper.school.service.courses.service.CourseRepositoryService;
import es.abelfgdeveloper.school.service.courses.usecase.v1.FindCoursesPaginatedUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class FindCoursesPaginatedUseCaseImpl implements FindCoursesPaginatedUseCase {

  private final CourseRepositoryService courseRepositoryService;

  @Transactional(readOnly = true)
  @Override
  public CoursePaginated execute(PaginationIn pagination, String query) {
    return courseRepositoryService.findAll(pagination, query);
  }
}
