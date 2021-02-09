package es.abelfgdeveloper.school.service.courses.api.v1.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import es.abelfgdeveloper.common.exception.AbelfgDeveloperException;
import es.abelfgdeveloper.common.exception.server.ValidationResponseException;
import es.abelfgdeveloper.school.service.courses.api.v1.resource.response.CourseResponseResource;
import es.abelfgdeveloper.school.service.courses.objectmother.CourseObjectMother;
import es.abelfgdeveloper.school.service.courses.util.CourseErrorCodes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CourseResponseResourceValidatorTest {

  private CourseResponseResourceValidator courseResponseResourceValidator;

  @BeforeEach
  void setUp() {
    courseResponseResourceValidator = new CourseResponseResourceValidator();
  }

  @Test
  void test_validate_ok() {
    // given
    CourseResponseResource courseResponseResource =
        CourseObjectMother.getCourseResponseResourceV1();

    // when
    courseResponseResourceValidator.validate(courseResponseResource);

    // then
  }

  @Test
  void test_validate_objectNull() {
    // given
    CourseResponseResource courseResponseResource = null;

    // when
    AbelfgDeveloperException exception =
        assertThrows(
            ValidationResponseException.class,
            () -> {
              courseResponseResourceValidator.validate(courseResponseResource);
            });

    // then
    assertEquals(500, exception.getErrorStatusCode());
    assertEquals(CourseErrorCodes.RESPONSE_BODY_NOT_NULL, exception.getMessage());
  }

  @Test
  void test_validate_idNull() {
    // given
    CourseResponseResource courseResponseResource =
        CourseObjectMother.getCourseResponseResourceV1();
    courseResponseResource.setId(null);

    // when
    AbelfgDeveloperException exception =
        assertThrows(
            ValidationResponseException.class,
            () -> {
              courseResponseResourceValidator.validate(courseResponseResource);
            });

    // then
    assertEquals(500, exception.getErrorStatusCode());
    assertEquals(CourseErrorCodes.ID_NOT_NULL, exception.getMessage());
  }

  @Test
  void test_validate_idEmpty() {
    // given
    CourseResponseResource courseResponseResource =
        CourseObjectMother.getCourseResponseResourceV1();
    courseResponseResource.setId(" ");

    // when
    AbelfgDeveloperException exception =
        assertThrows(
            ValidationResponseException.class,
            () -> {
              courseResponseResourceValidator.validate(courseResponseResource);
            });

    // then
    assertEquals(500, exception.getErrorStatusCode());
    assertEquals(CourseErrorCodes.ID_NOT_NULL, exception.getMessage());
  }

  @Test
  void test_validate_firstNameNull() {
    // given
    CourseResponseResource courseResponseResource =
        CourseObjectMother.getCourseResponseResourceV1();
    courseResponseResource.setName(null);

    // when
    AbelfgDeveloperException exception =
        assertThrows(
            ValidationResponseException.class,
            () -> {
              courseResponseResourceValidator.validate(courseResponseResource);
            });

    // then
    assertEquals(500, exception.getErrorStatusCode());
    assertEquals(CourseErrorCodes.NAME_NOT_NULL, exception.getMessage());
  }

  @Test
  void test_validate_firstNameEmpty() {
    // given
    CourseResponseResource courseResponseResource =
        CourseObjectMother.getCourseResponseResourceV1();
    courseResponseResource.setName(" ");

    // when
    AbelfgDeveloperException exception =
        assertThrows(
            ValidationResponseException.class,
            () -> {
              courseResponseResourceValidator.validate(courseResponseResource);
            });

    // then
    assertEquals(500, exception.getErrorStatusCode());
    assertEquals(CourseErrorCodes.NAME_NOT_NULL, exception.getMessage());
  }

  @Test
  void test_validate_firstNameMinLength() {
    // given
    CourseResponseResource courseResponseResource =
        CourseObjectMother.getCourseResponseResourceV1();
    courseResponseResource.setName("a");

    // when
    AbelfgDeveloperException exception =
        assertThrows(
            ValidationResponseException.class,
            () -> {
              courseResponseResourceValidator.validate(courseResponseResource);
            });

    // then
    assertEquals(500, exception.getErrorStatusCode());
    assertEquals(CourseErrorCodes.NAME_LENGTH, exception.getMessage());
  }

  @Test
  void test_validate_firstNameMaxLength() {
    // given
    CourseResponseResource courseResponseResource =
        CourseObjectMother.getCourseResponseResourceV1();
    courseResponseResource.setName("abcdefghijabcdefghijabcdef");

    // when
    AbelfgDeveloperException exception =
        assertThrows(
            ValidationResponseException.class,
            () -> {
              courseResponseResourceValidator.validate(courseResponseResource);
            });

    // then
    assertEquals(500, exception.getErrorStatusCode());
    assertEquals(CourseErrorCodes.NAME_LENGTH, exception.getMessage());
  }

  @Test
  void test_validate_createAtNull() {
    // given
    CourseResponseResource courseResponseResource =
        CourseObjectMother.getCourseResponseResourceV1();
    courseResponseResource.setCreateAt(null);

    // when
    AbelfgDeveloperException exception =
        assertThrows(
            ValidationResponseException.class,
            () -> {
              courseResponseResourceValidator.validate(courseResponseResource);
            });

    // then
    assertEquals(500, exception.getErrorStatusCode());
    assertEquals(CourseErrorCodes.CREATE_AT_NOT_NULL, exception.getMessage());
  }
}
