package es.abelfgdeveloper.school.service.students.api.v1.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import es.abelfgdeveloper.common.exception.AbelfgDeveloperException;
import es.abelfgdeveloper.common.exception.client.ValidationRequestException;
import es.abelfgdeveloper.school.service.students.api.v1.resource.request.UpdateStudentRequestResource;
import es.abelfgdeveloper.school.service.students.objectmother.StudentObjectMother;
import es.abelfgdeveloper.school.service.students.util.StudentErrorCodes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UpdateStudentRequestResourceValidatorTest {

  private UpdateStudentRequestResourceValidator updateStudentRequestResourceValidator;

  @BeforeEach
  void setUp() {
    updateStudentRequestResourceValidator = new UpdateStudentRequestResourceValidator();
  }

  @Test
  void test_validate_ok() {
    // given
    UpdateStudentRequestResource updateStudentRequestResource =
        StudentObjectMother.getUpdateStudentRequestResourceV1();

    // when
    updateStudentRequestResourceValidator.validate(updateStudentRequestResource);

    // then
  }

  @Test
  void test_validate_objectNull() {
    // given
    UpdateStudentRequestResource updateStudentRequestResource = null;

    // when
    AbelfgDeveloperException exception =
        assertThrows(
            ValidationRequestException.class,
            () -> {
              updateStudentRequestResourceValidator.validate(updateStudentRequestResource);
            });

    // then
    assertEquals(400, exception.getErrorStatusCode());
    assertEquals(StudentErrorCodes.UPDATE_BODY_NOT_NULL, exception.getMessage());
  }

  @Test
  void test_validate_firstNameNull() {
    // given
    UpdateStudentRequestResource updateStudentRequestResource =
        StudentObjectMother.getUpdateStudentRequestResourceV1();
    updateStudentRequestResource.setFirstName(null);

    // when
    AbelfgDeveloperException exception =
        assertThrows(
            ValidationRequestException.class,
            () -> {
              updateStudentRequestResourceValidator.validate(updateStudentRequestResource);
            });

    // then
    assertEquals(400, exception.getErrorStatusCode());
    assertEquals(StudentErrorCodes.FIRST_NAME_NOT_NULL, exception.getMessage());
  }

  @Test
  void test_validate_firstNameEmpty() {
    // given
    UpdateStudentRequestResource updateStudentRequestResource =
        StudentObjectMother.getUpdateStudentRequestResourceV1();
    updateStudentRequestResource.setFirstName(" ");

    // when
    AbelfgDeveloperException exception =
        assertThrows(
            ValidationRequestException.class,
            () -> {
              updateStudentRequestResourceValidator.validate(updateStudentRequestResource);
            });

    // then
    assertEquals(400, exception.getErrorStatusCode());
    assertEquals(StudentErrorCodes.FIRST_NAME_NOT_NULL, exception.getMessage());
  }

  @Test
  void test_validate_firstNameMinLength() {
    // given
    UpdateStudentRequestResource updateStudentRequestResource =
        StudentObjectMother.getUpdateStudentRequestResourceV1();
    updateStudentRequestResource.setFirstName("a");

    // when
    AbelfgDeveloperException exception =
        assertThrows(
            ValidationRequestException.class,
            () -> {
              updateStudentRequestResourceValidator.validate(updateStudentRequestResource);
            });

    // then
    assertEquals(400, exception.getErrorStatusCode());
    assertEquals(StudentErrorCodes.FIRST_NAME_LENGTH, exception.getMessage());
  }

  @Test
  void test_validate_firstNameMaxLength() {
    // given
    UpdateStudentRequestResource updateStudentRequestResource =
        StudentObjectMother.getUpdateStudentRequestResourceV1();
    updateStudentRequestResource.setFirstName("abcdefghijabcdefghijabcdef");

    // when
    AbelfgDeveloperException exception =
        assertThrows(
            ValidationRequestException.class,
            () -> {
              updateStudentRequestResourceValidator.validate(updateStudentRequestResource);
            });

    // then
    assertEquals(400, exception.getErrorStatusCode());
    assertEquals(StudentErrorCodes.FIRST_NAME_LENGTH, exception.getMessage());
  }

  @Test
  void test_validate_lastNameNull() {
    // given
    UpdateStudentRequestResource updateStudentRequestResource =
        StudentObjectMother.getUpdateStudentRequestResourceV1();
    updateStudentRequestResource.setLastName(null);

    // when
    AbelfgDeveloperException exception =
        assertThrows(
            ValidationRequestException.class,
            () -> {
              updateStudentRequestResourceValidator.validate(updateStudentRequestResource);
            });

    // then
    assertEquals(400, exception.getErrorStatusCode());
    assertEquals(StudentErrorCodes.LAST_NAME_NOT_NULL, exception.getMessage());
  }

  @Test
  void test_validate_lastNameEmpty() {
    // given
    UpdateStudentRequestResource updateStudentRequestResource =
        StudentObjectMother.getUpdateStudentRequestResourceV1();
    updateStudentRequestResource.setLastName(" ");

    // when
    AbelfgDeveloperException exception =
        assertThrows(
            ValidationRequestException.class,
            () -> {
              updateStudentRequestResourceValidator.validate(updateStudentRequestResource);
            });

    // then
    assertEquals(400, exception.getErrorStatusCode());
    assertEquals(StudentErrorCodes.LAST_NAME_NOT_NULL, exception.getMessage());
  }

  @Test
  void test_validate_lastNameMinLength() {
    // given
    UpdateStudentRequestResource updateStudentRequestResource =
        StudentObjectMother.getUpdateStudentRequestResourceV1();
    updateStudentRequestResource.setLastName("a");

    // when
    AbelfgDeveloperException exception =
        assertThrows(
            ValidationRequestException.class,
            () -> {
              updateStudentRequestResourceValidator.validate(updateStudentRequestResource);
            });

    // then
    assertEquals(400, exception.getErrorStatusCode());
    assertEquals(StudentErrorCodes.LAST_NAME_LENGTH, exception.getMessage());
  }

  @Test
  void test_validate_lastNameMaxLength() {
    // given
    UpdateStudentRequestResource updateStudentRequestResource =
        StudentObjectMother.getUpdateStudentRequestResourceV1();
    updateStudentRequestResource.setLastName("abcdefghijabcdefghijabcdef");

    // when
    AbelfgDeveloperException exception =
        assertThrows(
            ValidationRequestException.class,
            () -> {
              updateStudentRequestResourceValidator.validate(updateStudentRequestResource);
            });

    // then
    assertEquals(400, exception.getErrorStatusCode());
    assertEquals(StudentErrorCodes.LAST_NAME_LENGTH, exception.getMessage());
  }
}
