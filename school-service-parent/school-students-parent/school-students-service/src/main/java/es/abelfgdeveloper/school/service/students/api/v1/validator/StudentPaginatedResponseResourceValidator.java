package es.abelfgdeveloper.school.service.students.api.v1.validator;

import es.abelfgdeveloper.common.api.v1.resource.validator.PaginationResponseResourceValidator;
import es.abelfgdeveloper.common.exception.server.ValidationResponseException;
import es.abelfgdeveloper.school.service.students.api.v1.resource.response.StudentPaginatedResponseResource;
import es.abelfgdeveloper.school.service.students.api.v1.resource.response.StudentResponseResource;
import es.abelfgdeveloper.school.service.students.util.ErrorCodes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class StudentPaginatedResponseResourceValidator extends StudentValidator {

  private final StudentResponseResourceValidator studentResponseResourceValidator;
  private final PaginationResponseResourceValidator paginationResponseResourceValidator;

  public void validate(StudentPaginatedResponseResource studentPaginated) {
    if (studentPaginated == null) {
      throw new ValidationResponseException(ErrorCodes.STUDENT_PAGINATED_RESPONSE_BODY_NOT_NULL);
    }
    paginationResponseResourceValidator.validate(studentPaginated.getPagination());
    for (StudentResponseResource student : studentPaginated.getStudents()) {
      studentResponseResourceValidator.validate(student);
    }
  }
}
