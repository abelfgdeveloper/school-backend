package es.abelfgdeveloper.school.service.courses.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CourseErrorCodes {

  private static final String SCHOOL_APP_ERROR_CODE = "001";
  private static final String SCHOOL_COURSES_SERVICE_ERROR_CODE = SCHOOL_APP_ERROR_CODE + "001";

  public static final String ID_NOT_FOUND = SCHOOL_COURSES_SERVICE_ERROR_CODE + "001";
  public static final String ID_NOT_NULL = SCHOOL_COURSES_SERVICE_ERROR_CODE + "002";

  public static final String NAME_NOT_NULL = SCHOOL_COURSES_SERVICE_ERROR_CODE + "003";
  public static final String NAME_LENGTH = SCHOOL_COURSES_SERVICE_ERROR_CODE + "004";
  public static final String NAME_EXIST = SCHOOL_COURSES_SERVICE_ERROR_CODE + "005";

  public static final String CREATE_AT_NOT_NULL = SCHOOL_COURSES_SERVICE_ERROR_CODE + "006";

  public static final String CREATE_BODY_NOT_NULL = SCHOOL_COURSES_SERVICE_ERROR_CODE + "007";
  public static final String UPDATE_BODY_NOT_NULL = SCHOOL_COURSES_SERVICE_ERROR_CODE + "008";
  public static final String RESPONSE_BODY_NOT_NULL = SCHOOL_COURSES_SERVICE_ERROR_CODE + "009";
  public static final String PAGINATED_RESPONSE_BODY_NOT_NULL =
      SCHOOL_COURSES_SERVICE_ERROR_CODE + "010";
}
