package es.abelfgdeveloper.school.service.courses.api.v1.validator;

import es.abelfgdeveloper.common.exception.client.ValidationRequestException;
import es.abelfgdeveloper.school.service.courses.util.CourseErrorCodes;
import java.time.LocalDateTime;
import java.util.regex.Pattern;
import org.springframework.util.StringUtils;

public abstract class CourseValidator {

  public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
      Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

  protected void validateId(String id) {
    if (StringUtils.isEmpty(id) || id.trim().isEmpty()) {
      throw new ValidationRequestException(CourseErrorCodes.ID_NOT_NULL);
    }
  }

  protected void validateName(String name) {
    if (StringUtils.isEmpty(name) || name.trim().isEmpty()) {
      throw new ValidationRequestException(CourseErrorCodes.NAME_NOT_NULL);
    }
    if (name.length() < 3 || name.length() > 25) {
      throw new ValidationRequestException(CourseErrorCodes.NAME_LENGTH);
    }
  }

  protected void validateCreateAt(LocalDateTime createAt) {
    if (createAt == null) {
      throw new ValidationRequestException(CourseErrorCodes.CREATE_AT_NOT_NULL);
    }
  }
}
