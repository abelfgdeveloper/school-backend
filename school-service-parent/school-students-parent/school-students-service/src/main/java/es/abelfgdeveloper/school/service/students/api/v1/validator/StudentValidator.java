package es.abelfgdeveloper.school.service.students.api.v1.validator;

import es.abelfgdeveloper.common.exception.client.ValidationRequestException;
import java.time.LocalDateTime;
import java.util.regex.Pattern;
import org.springframework.util.StringUtils;

public abstract class StudentValidator {

  public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
      Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

  protected void validateId(String id) {
    if (StringUtils.isEmpty(id) || id.trim().isEmpty()) {
      throw new ValidationRequestException("El ID es obligatorio");
    }
  }

  protected void validateFirstName(String firstName) {
    if (StringUtils.isEmpty(firstName) || firstName.trim().isEmpty()) {
      throw new ValidationRequestException("El nombre es obligatorio");
    }
    if (firstName.length() < 3 || firstName.length() > 25) {
      throw new ValidationRequestException("El nombre debe tener entre 3 y 25 caracteres");
    }
  }

  protected void validateLastName(String lastName) {
    if (StringUtils.isEmpty(lastName) || lastName.trim().isEmpty()) {
      throw new ValidationRequestException("El apellido es obligatorio");
    }
    if (lastName.length() < 3 || lastName.length() > 25) {
      throw new ValidationRequestException("El apellido debe tener entre 3 y 25 caracteres");
    }
  }

  protected void validateEmail(String email) {
    if (StringUtils.isEmpty(email) || email.trim().isEmpty()) {
      throw new ValidationRequestException("El email es obligatorio");
    }
    if (email.length() < 5 || email.length() > 50) {
      throw new ValidationRequestException("El apellido debe tener entre 5 y 50 caracteres");
    }
    if (!VALID_EMAIL_ADDRESS_REGEX.matcher(email).find()) {
      throw new ValidationRequestException("El formato del email no es correcto");
    }
  }

  protected void validateCreateAt(LocalDateTime createAt) {
    if (createAt == null) {
      throw new ValidationRequestException("La fecha de creacion es obligatoria");
    }
  }
}
