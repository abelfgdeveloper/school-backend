package es.abelfgdeveloper.school.service.courses.api.v1.validator;

import es.abelfgdeveloper.common.exception.client.ValidationRequestException;
import es.abelfgdeveloper.school.service.courses.api.v1.resource.request.UpdateCourseRequestResource;
import es.abelfgdeveloper.school.service.courses.util.CourseErrorCodes;
import org.springframework.stereotype.Component;

@Component
public class UpdateCourseRequestResourceValidator extends CourseValidator {

  public void validate(UpdateCourseRequestResource course) {
    if (course == null) {
      throw new ValidationRequestException(CourseErrorCodes.UPDATE_BODY_NOT_NULL);
    }
    validateName(course.getName());
  }
}
