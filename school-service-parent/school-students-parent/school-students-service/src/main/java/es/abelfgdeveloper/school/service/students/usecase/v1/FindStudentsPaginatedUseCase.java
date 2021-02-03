package es.abelfgdeveloper.school.service.students.usecase.v1;

import es.abelfgdeveloper.common.domain.PaginationIn;
import es.abelfgdeveloper.school.service.students.domain.StudentPaginated;

public interface FindStudentsPaginatedUseCase {

  StudentPaginated execute(PaginationIn pagination, String query);
}
