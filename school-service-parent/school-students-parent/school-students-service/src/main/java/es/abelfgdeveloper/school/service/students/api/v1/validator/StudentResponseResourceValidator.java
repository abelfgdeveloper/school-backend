package es.abelfgdeveloper.school.service.students.api.v1.validator;

import es.abelfgdeveloper.common.exception.client.ValidationRequestException;
import es.abelfgdeveloper.common.exception.server.ValidationResponseException;
import es.abelfgdeveloper.school.service.students.api.v1.resource.response.StudentResponseResource;
import es.abelfgdeveloper.school.service.students.util.StudentErrorCodes;
import org.springframework.stereotype.Component;

@Component
public class StudentResponseResourceValidator extends StudentValidator {

  public void validate(StudentResponseResource student) {
    if (student == null) {
      throw new ValidationResponseException(StudentErrorCodes.RESPONSE_BODY_NOT_NULL);
    }
    try {
      validateId(student.getId());
      validateFirstName(student.getFirstName());
      validateLastName(student.getLastName());
      validateEmail(student.getEmail());
      validateCreateAt(student.getCreateAt());
    } catch (ValidationRequestException e) {
      throw new ValidationResponseException(e.getMessage(), e);
    }
  }
}
