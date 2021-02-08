package es.abelfgdeveloper.school.service.students.api.v1.validator;

import es.abelfgdeveloper.common.exception.client.ValidationRequestException;
import es.abelfgdeveloper.school.service.students.api.v1.resource.request.CreateStudentRequestResource;
import es.abelfgdeveloper.school.service.students.util.StudentErrorCodes;
import org.springframework.stereotype.Component;

@Component
public class CreateStudentRequestResourceValidator extends StudentValidator {

  public void validate(CreateStudentRequestResource student) {
    if (student == null) {
      throw new ValidationRequestException(StudentErrorCodes.CREATE_BODY_NOT_NULL);
    }
    validateFirstName(student.getFirstName());
    validateLastName(student.getLastName());
    validateEmail(student.getEmail());
  }
}
