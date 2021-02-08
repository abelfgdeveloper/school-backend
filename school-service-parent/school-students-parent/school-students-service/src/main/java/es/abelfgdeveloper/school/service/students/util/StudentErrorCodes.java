package es.abelfgdeveloper.school.service.students.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StudentErrorCodes {

  private static final String SCHOOL_APP_ERROR_CODE = "001";
  private static final String SCHOOL_STUDENTS_SERVICE_ERROR_CODE = SCHOOL_APP_ERROR_CODE + "001";

  public static final String ID_NOT_FOUND = SCHOOL_STUDENTS_SERVICE_ERROR_CODE + "001";
  public static final String ID_NOT_NULL = SCHOOL_STUDENTS_SERVICE_ERROR_CODE + "002";

  public static final String FIRST_NAME_NOT_NULL = SCHOOL_STUDENTS_SERVICE_ERROR_CODE + "003";
  public static final String FIRST_NAME_LENGTH = SCHOOL_STUDENTS_SERVICE_ERROR_CODE + "007";

  public static final String LAST_NAME_NOT_NULL = SCHOOL_STUDENTS_SERVICE_ERROR_CODE + "004";
  public static final String LAST_NAME_LENGTH = SCHOOL_STUDENTS_SERVICE_ERROR_CODE + "008";

  public static final String EMAIL_NOT_NULL = SCHOOL_STUDENTS_SERVICE_ERROR_CODE + "005";
  public static final String EMAIL_LENGTH = SCHOOL_STUDENTS_SERVICE_ERROR_CODE + "009";
  public static final String EMAIL_PATTERN = SCHOOL_STUDENTS_SERVICE_ERROR_CODE + "010";
  public static final String EMAIL_NOT_FOUND = SCHOOL_STUDENTS_SERVICE_ERROR_CODE + "015";
  public static final String EMAIL_EXIST = SCHOOL_STUDENTS_SERVICE_ERROR_CODE + "016";

  public static final String CREATE_AT_NOT_NULL = SCHOOL_STUDENTS_SERVICE_ERROR_CODE + "006";

  public static final String CREATE_BODY_NOT_NULL = SCHOOL_STUDENTS_SERVICE_ERROR_CODE + "011";
  public static final String UPDATE_BODY_NOT_NULL = SCHOOL_STUDENTS_SERVICE_ERROR_CODE + "012";
  public static final String RESPONSE_BODY_NOT_NULL = SCHOOL_STUDENTS_SERVICE_ERROR_CODE + "013";
  public static final String PAGINATED_RESPONSE_BODY_NOT_NULL =
      SCHOOL_STUDENTS_SERVICE_ERROR_CODE + "014";
}
