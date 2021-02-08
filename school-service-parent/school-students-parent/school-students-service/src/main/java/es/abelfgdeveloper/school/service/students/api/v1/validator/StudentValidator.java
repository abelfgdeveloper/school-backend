package es.abelfgdeveloper.school.service.students.api.v1.validator;

import es.abelfgdeveloper.common.exception.client.ValidationRequestException;
import es.abelfgdeveloper.school.service.students.util.StudentErrorCodes;
import java.time.LocalDateTime;
import java.util.regex.Pattern;
import org.springframework.util.StringUtils;

public abstract class StudentValidator {

  public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
      Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

  protected void validateId(String id) {
    if (StringUtils.isEmpty(id) || id.trim().isEmpty()) {
      throw new ValidationRequestException(StudentErrorCodes.ID_NOT_NULL);
    }
  }

  protected void validateFirstName(String firstName) {
    if (StringUtils.isEmpty(firstName) || firstName.trim().isEmpty()) {
      throw new ValidationRequestException(StudentErrorCodes.FIRST_NAME_NOT_NULL);
    }
    if (firstName.length() < 3 || firstName.length() > 25) {
      throw new ValidationRequestException(StudentErrorCodes.FIRST_NAME_LENGTH);
    }
  }

  protected void validateLastName(String lastName) {
    if (StringUtils.isEmpty(lastName) || lastName.trim().isEmpty()) {
      throw new ValidationRequestException(StudentErrorCodes.LAST_NAME_NOT_NULL);
    }
    if (lastName.length() < 3 || lastName.length() > 25) {
      throw new ValidationRequestException(StudentErrorCodes.LAST_NAME_LENGTH);
    }
  }

  protected void validateEmail(String email) {
    if (StringUtils.isEmpty(email) || email.trim().isEmpty()) {
      throw new ValidationRequestException(StudentErrorCodes.EMAIL_NOT_NULL);
    }
    if (email.length() < 5 || email.length() > 50) {
      throw new ValidationRequestException(StudentErrorCodes.EMAIL_LENGTH);
    }
    if (!VALID_EMAIL_ADDRESS_REGEX.matcher(email).find()) {
      throw new ValidationRequestException(StudentErrorCodes.EMAIL_PATTERN);
    }
  }

  protected void validateCreateAt(LocalDateTime createAt) {
    if (createAt == null) {
      throw new ValidationRequestException(StudentErrorCodes.CREATE_AT_NOT_NULL);
    }
  }
}
