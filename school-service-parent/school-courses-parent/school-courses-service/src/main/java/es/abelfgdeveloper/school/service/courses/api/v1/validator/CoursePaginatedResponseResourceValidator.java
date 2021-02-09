package es.abelfgdeveloper.school.service.courses.api.v1.validator;

import es.abelfgdeveloper.common.api.v1.resource.validator.PaginationResponseResourceValidator;
import es.abelfgdeveloper.common.exception.server.ValidationResponseException;
import es.abelfgdeveloper.school.service.courses.api.v1.resource.response.CoursePaginatedResponseResource;
import es.abelfgdeveloper.school.service.courses.api.v1.resource.response.CourseResponseResource;
import es.abelfgdeveloper.school.service.courses.util.CourseErrorCodes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CoursePaginatedResponseResourceValidator extends CourseValidator {

  private final CourseResponseResourceValidator courseResponseResourceValidator;
  private final PaginationResponseResourceValidator paginationResponseResourceValidator;

  public void validate(CoursePaginatedResponseResource coursePaginated) {
    if (coursePaginated == null) {
      throw new ValidationResponseException(CourseErrorCodes.PAGINATED_RESPONSE_BODY_NOT_NULL);
    }
    paginationResponseResourceValidator.validate(coursePaginated.getPagination());
    for (CourseResponseResource course : coursePaginated.getCourses()) {
      courseResponseResourceValidator.validate(course);
    }
  }
}
