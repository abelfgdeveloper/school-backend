package es.abelfgdeveloper.school.service.courses.usecase.v1;

import es.abelfgdeveloper.common.domain.PaginationIn;
import es.abelfgdeveloper.school.service.courses.domain.CoursePaginated;

public interface FindCoursesPaginatedUseCase {

  CoursePaginated execute(PaginationIn pagination, String query);
}
