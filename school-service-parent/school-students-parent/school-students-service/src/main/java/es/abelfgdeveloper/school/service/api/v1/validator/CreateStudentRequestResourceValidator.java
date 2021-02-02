package es.abelfgdeveloper.school.service.api.v1.validator;

import es.abelfgdeveloper.common.exception.client.ValidationRequestException;
import es.abelfgdeveloper.school.service.api.v1.resource.request.CreateStudentRequestResource;
import org.springframework.stereotype.Component;

@Component
public class CreateStudentRequestResourceValidator extends StudentValidator {

  public void validate(CreateStudentRequestResource student) {
    if (student == null) {
      throw new ValidationRequestException("El cuerpo de la peticion es obligatorio");
    }
    validateFirstName(student.getFirstName());
    validateLastName(student.getLastName());
    validateEmail(student.getEmail());
  }
}
