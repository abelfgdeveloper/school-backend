package es.abelfgdeveloper.school.service.courses.api.v1.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import es.abelfgdeveloper.common.exception.AbelfgDeveloperException;
import es.abelfgdeveloper.common.exception.client.ValidationRequestException;
import es.abelfgdeveloper.school.service.courses.api.v1.resource.request.UpdateCourseRequestResource;
import es.abelfgdeveloper.school.service.courses.objectmother.CourseObjectMother;
import es.abelfgdeveloper.school.service.courses.util.CourseErrorCodes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UpdateCourseRequestResourceValidatorTest {

  private UpdateCourseRequestResourceValidator updateCourseRequestResourceValidator;

  @BeforeEach
  void setUp() {
    updateCourseRequestResourceValidator = new UpdateCourseRequestResourceValidator();
  }

  @Test
  void test_validate_ok() {
    // given
    UpdateCourseRequestResource updateCourseRequestResource =
        CourseObjectMother.getUpdateCourseRequestResourceV1();

    // when
    updateCourseRequestResourceValidator.validate(updateCourseRequestResource);

    // then
  }

  @Test
  void test_validate_objectNull() {
    // given
    UpdateCourseRequestResource updateCourseRequestResource = null;

    // when
    AbelfgDeveloperException exception =
        assertThrows(
            ValidationRequestException.class,
            () -> {
              updateCourseRequestResourceValidator.validate(updateCourseRequestResource);
            });

    // then
    assertEquals(400, exception.getErrorStatusCode());
    assertEquals(CourseErrorCodes.UPDATE_BODY_NOT_NULL, exception.getMessage());
  }

  @Test
  void test_validate_firstNameNull() {
    // given
    UpdateCourseRequestResource updateCourseRequestResource =
        CourseObjectMother.getUpdateCourseRequestResourceV1();
    updateCourseRequestResource.setName(null);

    // when
    AbelfgDeveloperException exception =
        assertThrows(
            ValidationRequestException.class,
            () -> {
              updateCourseRequestResourceValidator.validate(updateCourseRequestResource);
            });

    // then
    assertEquals(400, exception.getErrorStatusCode());
    assertEquals(CourseErrorCodes.NAME_NOT_NULL, exception.getMessage());
  }

  @Test
  void test_validate_firstNameEmpty() {
    // given
    UpdateCourseRequestResource updateCourseRequestResource =
        CourseObjectMother.getUpdateCourseRequestResourceV1();
    updateCourseRequestResource.setName(" ");

    // when
    AbelfgDeveloperException exception =
        assertThrows(
            ValidationRequestException.class,
            () -> {
              updateCourseRequestResourceValidator.validate(updateCourseRequestResource);
            });

    // then
    assertEquals(400, exception.getErrorStatusCode());
    assertEquals(CourseErrorCodes.NAME_NOT_NULL, exception.getMessage());
  }

  @Test
  void test_validate_firstNameMinLength() {
    // given
    UpdateCourseRequestResource updateCourseRequestResource =
        CourseObjectMother.getUpdateCourseRequestResourceV1();
    updateCourseRequestResource.setName("a");

    // when
    AbelfgDeveloperException exception =
        assertThrows(
            ValidationRequestException.class,
            () -> {
              updateCourseRequestResourceValidator.validate(updateCourseRequestResource);
            });

    // then
    assertEquals(400, exception.getErrorStatusCode());
    assertEquals(CourseErrorCodes.NAME_LENGTH, exception.getMessage());
  }

  @Test
  void test_validate_firstNameMaxLength() {
    // given
    UpdateCourseRequestResource updateCourseRequestResource =
        CourseObjectMother.getUpdateCourseRequestResourceV1();
    updateCourseRequestResource.setName("abcdefghijabcdefghijabcdef");

    // when
    AbelfgDeveloperException exception =
        assertThrows(
            ValidationRequestException.class,
            () -> {
              updateCourseRequestResourceValidator.validate(updateCourseRequestResource);
            });

    // then
    assertEquals(400, exception.getErrorStatusCode());
    assertEquals(CourseErrorCodes.NAME_LENGTH, exception.getMessage());
  }
}
