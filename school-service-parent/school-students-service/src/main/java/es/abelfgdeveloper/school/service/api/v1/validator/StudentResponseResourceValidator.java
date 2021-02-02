package es.abelfgdeveloper.school.service.api.v1.validator;

import es.abelfgdeveloper.common.exception.client.ValidationRequestException;
import es.abelfgdeveloper.common.exception.server.ValidationResponseException;
import es.abelfgdeveloper.school.service.api.v1.resource.response.StudentResponseResource;
import org.springframework.stereotype.Component;

@Component
public class StudentResponseResourceValidator extends StudentValidator {

  public void validate(StudentResponseResource student) {
    if (student == null) {
      throw new ValidationResponseException("El cuerpo de la peticion es obligatorio");
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
