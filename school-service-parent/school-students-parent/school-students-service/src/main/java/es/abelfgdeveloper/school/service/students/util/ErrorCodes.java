package es.abelfgdeveloper.school.service.students.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorCodes {

  private static final String SCHOOL_APP_ERROR_CODE = "001";
  private static final String SCHOOL_STUDENTS_SERVICE_ERROR_CODE = SCHOOL_APP_ERROR_CODE + "001";

  public static final String STUDENT_ID_NOT_FOUND = SCHOOL_STUDENTS_SERVICE_ERROR_CODE + "001";
  public static final String STUDENT_ID_NOT_NULL = SCHOOL_STUDENTS_SERVICE_ERROR_CODE + "002";
  public static final String STUDENT_FIRST_NAME_NOT_NULL =
      SCHOOL_STUDENTS_SERVICE_ERROR_CODE + "003";
  public static final String STUDENT_LAST_NAME_NOT_NULL =
      SCHOOL_STUDENTS_SERVICE_ERROR_CODE + "004";
  public static final String STUDENT_EMAIL_NOT_NULL = SCHOOL_STUDENTS_SERVICE_ERROR_CODE + "005";
  public static final String STUDENT_CREATE_AT_NOT_NULL =
      SCHOOL_STUDENTS_SERVICE_ERROR_CODE + "006";
  public static final String STUDENT_FIRST_NAME_LENGTH = SCHOOL_STUDENTS_SERVICE_ERROR_CODE + "007";
  public static final String STUDENT_LAST_NAME_LENGTH = SCHOOL_STUDENTS_SERVICE_ERROR_CODE + "008";
  public static final String STUDENT_EMAIL_LENGTH = SCHOOL_STUDENTS_SERVICE_ERROR_CODE + "009";
  public static final String STUDENT_EMAIL_PATTERN = SCHOOL_STUDENTS_SERVICE_ERROR_CODE + "010";
  public static final String STUDENT_CREATE_BODY_NOT_NULL =
      SCHOOL_STUDENTS_SERVICE_ERROR_CODE + "011";
  public static final String STUDENT_UPDATE_BODY_NOT_NULL =
      SCHOOL_STUDENTS_SERVICE_ERROR_CODE + "012";
  public static final String STUDENT_RESPONSE_BODY_NOT_NULL =
      SCHOOL_STUDENTS_SERVICE_ERROR_CODE + "013";
  public static final String STUDENT_PAGINATED_RESPONSE_BODY_NOT_NULL =
      SCHOOL_STUDENTS_SERVICE_ERROR_CODE + "014";
  public static final String STUDENT_EMAIL_NOT_FOUND = SCHOOL_STUDENTS_SERVICE_ERROR_CODE + "015";
  public static final String STUDENT_EMAIL_EXIST = SCHOOL_STUDENTS_SERVICE_ERROR_CODE + "016";
}
