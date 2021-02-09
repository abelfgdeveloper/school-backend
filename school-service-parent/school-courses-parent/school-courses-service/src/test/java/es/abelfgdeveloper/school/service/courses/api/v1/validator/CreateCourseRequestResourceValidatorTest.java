package es.abelfgdeveloper.school.service.courses.api.v1.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import es.abelfgdeveloper.common.exception.AbelfgDeveloperException;
import es.abelfgdeveloper.common.exception.client.ValidationRequestException;
import es.abelfgdeveloper.school.service.courses.api.v1.resource.request.CreateCourseRequestResource;
import es.abelfgdeveloper.school.service.courses.objectmother.CourseObjectMother;
import es.abelfgdeveloper.school.service.courses.util.CourseErrorCodes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CreateCourseRequestResourceValidatorTest {

  private CreateCourseRequestResourceValidator createCourseRequestResourceValidator;

  @BeforeEach
  void setUp() {
    createCourseRequestResourceValidator = new CreateCourseRequestResourceValidator();
  }

  @Test
  void test_validate_ok() {
    // given
    CreateCourseRequestResource createCourseRequestResource =
        CourseObjectMother.getCreateCourseRequestResourceV1();

    // when
    createCourseRequestResourceValidator.validate(createCourseRequestResource);

    // then
  }

  @Test
  void test_validate_objectNull() {
    // given
    CreateCourseRequestResource createCourseRequestResource = null;

    // when
    AbelfgDeveloperException exception =
        assertThrows(
            ValidationRequestException.class,
            () -> {
              createCourseRequestResourceValidator.validate(createCourseRequestResource);
            });

    // then
    assertEquals(400, exception.getErrorStatusCode());
    assertEquals(CourseErrorCodes.CREATE_BODY_NOT_NULL, exception.getMessage());
  }

  @Test
  void test_validate_nameNull() {
    // given
    CreateCourseRequestResource createCourseRequestResource =
        CourseObjectMother.getCreateCourseRequestResourceV1();
    createCourseRequestResource.setName(null);

    // when
    AbelfgDeveloperException exception =
        assertThrows(
            ValidationRequestException.class,
            () -> {
              createCourseRequestResourceValidator.validate(createCourseRequestResource);
            });

    // then
    assertEquals(400, exception.getErrorStatusCode());
    assertEquals(CourseErrorCodes.NAME_NOT_NULL, exception.getMessage());
  }

  @Test
  void test_validate_nameEmpty() {
    // given
    CreateCourseRequestResource createCourseRequestResource =
        CourseObjectMother.getCreateCourseRequestResourceV1();
    createCourseRequestResource.setName(" ");

    // when
    AbelfgDeveloperException exception =
        assertThrows(
            ValidationRequestException.class,
            () -> {
              createCourseRequestResourceValidator.validate(createCourseRequestResource);
            });

    // then
    assertEquals(400, exception.getErrorStatusCode());
    assertEquals(CourseErrorCodes.NAME_NOT_NULL, exception.getMessage());
  }

  @Test
  void test_validate_firstNameMinLength() {
    // given
    CreateCourseRequestResource createCourseRequestResource =
        CourseObjectMother.getCreateCourseRequestResourceV1();
    createCourseRequestResource.setName("a");

    // when
    AbelfgDeveloperException exception =
        assertThrows(
            ValidationRequestException.class,
            () -> {
              createCourseRequestResourceValidator.validate(createCourseRequestResource);
            });

    // then
    assertEquals(400, exception.getErrorStatusCode());
    assertEquals(CourseErrorCodes.NAME_LENGTH, exception.getMessage());
  }

  @Test
  void test_validate_firstNameMaxLength() {
    // given
    CreateCourseRequestResource createCourseRequestResource =
        CourseObjectMother.getCreateCourseRequestResourceV1();
    createCourseRequestResource.setName("abcdefghijabcdefghijabcdef");

    // when
    AbelfgDeveloperException exception =
        assertThrows(
            ValidationRequestException.class,
            () -> {
              createCourseRequestResourceValidator.validate(createCourseRequestResource);
            });

    // then
    assertEquals(400, exception.getErrorStatusCode());
    assertEquals(CourseErrorCodes.NAME_LENGTH, exception.getMessage());
  }
}
