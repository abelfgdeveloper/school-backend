package es.abelfgdeveloper.school.service.courses.api.v1.validator;

import es.abelfgdeveloper.common.exception.client.ValidationRequestException;
import es.abelfgdeveloper.common.exception.server.ValidationResponseException;
import es.abelfgdeveloper.school.service.courses.api.v1.resource.response.CourseResponseResource;
import es.abelfgdeveloper.school.service.courses.util.CourseErrorCodes;
import org.springframework.stereotype.Component;

@Component
public class CourseResponseResourceValidator extends CourseValidator {

  public void validate(CourseResponseResource course) {
    if (course == null) {
      throw new ValidationResponseException(CourseErrorCodes.RESPONSE_BODY_NOT_NULL);
    }
    try {
      validateId(course.getId());
      validateName(course.getName());
      validateCreateAt(course.getCreateAt());
    } catch (ValidationRequestException e) {
      throw new ValidationResponseException(e.getMessage(), e);
    }
  }
}
