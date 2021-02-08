package es.abelfgdeveloper.school.service.students.api.v1.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import es.abelfgdeveloper.common.exception.AbelfgDeveloperException;
import es.abelfgdeveloper.common.exception.client.ValidationRequestException;
import es.abelfgdeveloper.school.service.students.api.v1.resource.request.CreateStudentRequestResource;
import es.abelfgdeveloper.school.service.students.objectmother.StudentObjectMother;
import es.abelfgdeveloper.school.service.students.util.StudentErrorCodes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CreateStudentRequestResourceValidatorTest {

  private CreateStudentRequestResourceValidator createStudentRequestResourceValidator;

  @BeforeEach
  void setUp() {
    createStudentRequestResourceValidator = new CreateStudentRequestResourceValidator();
  }

  @Test
  void test_validate_ok() {
    // given
    CreateStudentRequestResource createStudentRequestResource =
        StudentObjectMother.getCreateStudentRequestResourceV1();

    // when
    createStudentRequestResourceValidator.validate(createStudentRequestResource);

    // then
  }

  @Test
  void test_validate_objectNull() {
    // given
    CreateStudentRequestResource createStudentRequestResource = null;

    // when
    AbelfgDeveloperException exception =
        assertThrows(
            ValidationRequestException.class,
            () -> {
              createStudentRequestResourceValidator.validate(createStudentRequestResource);
            });

    // then
    assertEquals(400, exception.getErrorStatusCode());
    assertEquals(StudentErrorCodes.CREATE_BODY_NOT_NULL, exception.getMessage());
  }

  @Test
  void test_validate_firstNameNull() {
    // given
    CreateStudentRequestResource createStudentRequestResource =
        StudentObjectMother.getCreateStudentRequestResourceV1();
    createStudentRequestResource.setFirstName(null);

    // when
    AbelfgDeveloperException exception =
        assertThrows(
            ValidationRequestException.class,
            () -> {
              createStudentRequestResourceValidator.validate(createStudentRequestResource);
            });

    // then
    assertEquals(400, exception.getErrorStatusCode());
    assertEquals(StudentErrorCodes.FIRST_NAME_NOT_NULL, exception.getMessage());
  }

  @Test
  void test_validate_firstNameEmpty() {
    // given
    CreateStudentRequestResource createStudentRequestResource =
        StudentObjectMother.getCreateStudentRequestResourceV1();
    createStudentRequestResource.setFirstName(" ");

    // when
    AbelfgDeveloperException exception =
        assertThrows(
            ValidationRequestException.class,
            () -> {
              createStudentRequestResourceValidator.validate(createStudentRequestResource);
            });

    // then
    assertEquals(400, exception.getErrorStatusCode());
    assertEquals(StudentErrorCodes.FIRST_NAME_NOT_NULL, exception.getMessage());
  }

  @Test
  void test_validate_firstNameMinLength() {
    // given
    CreateStudentRequestResource createStudentRequestResource =
        StudentObjectMother.getCreateStudentRequestResourceV1();
    createStudentRequestResource.setFirstName("a");

    // when
    AbelfgDeveloperException exception =
        assertThrows(
            ValidationRequestException.class,
            () -> {
              createStudentRequestResourceValidator.validate(createStudentRequestResource);
            });

    // then
    assertEquals(400, exception.getErrorStatusCode());
    assertEquals(StudentErrorCodes.FIRST_NAME_LENGTH, exception.getMessage());
  }

  @Test
  void test_validate_firstNameMaxLength() {
    // given
    CreateStudentRequestResource createStudentRequestResource =
        StudentObjectMother.getCreateStudentRequestResourceV1();
    createStudentRequestResource.setFirstName("abcdefghijabcdefghijabcdef");

    // when
    AbelfgDeveloperException exception =
        assertThrows(
            ValidationRequestException.class,
            () -> {
              createStudentRequestResourceValidator.validate(createStudentRequestResource);
            });

    // then
    assertEquals(400, exception.getErrorStatusCode());
    assertEquals(StudentErrorCodes.FIRST_NAME_LENGTH, exception.getMessage());
  }

  @Test
  void test_validate_lastNameNull() {
    // given
    CreateStudentRequestResource createStudentRequestResource =
        StudentObjectMother.getCreateStudentRequestResourceV1();
    createStudentRequestResource.setLastName(null);

    // when
    AbelfgDeveloperException exception =
        assertThrows(
            ValidationRequestException.class,
            () -> {
              createStudentRequestResourceValidator.validate(createStudentRequestResource);
            });

    // then
    assertEquals(400, exception.getErrorStatusCode());
    assertEquals(StudentErrorCodes.LAST_NAME_NOT_NULL, exception.getMessage());
  }

  @Test
  void test_validate_lastNameEmpty() {
    // given
    CreateStudentRequestResource createStudentRequestResource =
        StudentObjectMother.getCreateStudentRequestResourceV1();
    createStudentRequestResource.setLastName(" ");

    // when
    AbelfgDeveloperException exception =
        assertThrows(
            ValidationRequestException.class,
            () -> {
              createStudentRequestResourceValidator.validate(createStudentRequestResource);
            });

    // then
    assertEquals(400, exception.getErrorStatusCode());
    assertEquals(StudentErrorCodes.LAST_NAME_NOT_NULL, exception.getMessage());
  }

  @Test
  void test_validate_lastNameMinLength() {
    // given
    CreateStudentRequestResource createStudentRequestResource =
        StudentObjectMother.getCreateStudentRequestResourceV1();
    createStudentRequestResource.setLastName("a");

    // when
    AbelfgDeveloperException exception =
        assertThrows(
            ValidationRequestException.class,
            () -> {
              createStudentRequestResourceValidator.validate(createStudentRequestResource);
            });

    // then
    assertEquals(400, exception.getErrorStatusCode());
    assertEquals(StudentErrorCodes.LAST_NAME_LENGTH, exception.getMessage());
  }

  @Test
  void test_validate_lastNameMaxLength() {
    // given
    CreateStudentRequestResource createStudentRequestResource =
        StudentObjectMother.getCreateStudentRequestResourceV1();
    createStudentRequestResource.setLastName("abcdefghijabcdefghijabcdef");

    // when
    AbelfgDeveloperException exception =
        assertThrows(
            ValidationRequestException.class,
            () -> {
              createStudentRequestResourceValidator.validate(createStudentRequestResource);
            });

    // then
    assertEquals(400, exception.getErrorStatusCode());
    assertEquals(StudentErrorCodes.LAST_NAME_LENGTH, exception.getMessage());
  }

  @Test
  void test_validate_emailNull() {
    // given
    CreateStudentRequestResource createStudentRequestResource =
        StudentObjectMother.getCreateStudentRequestResourceV1();
    createStudentRequestResource.setEmail(null);

    // when
    AbelfgDeveloperException exception =
        assertThrows(
            ValidationRequestException.class,
            () -> {
              createStudentRequestResourceValidator.validate(createStudentRequestResource);
            });

    // then
    assertEquals(400, exception.getErrorStatusCode());
    assertEquals(StudentErrorCodes.EMAIL_NOT_NULL, exception.getMessage());
  }

  @Test
  void test_validate_emailEmpty() {
    // given
    CreateStudentRequestResource createStudentRequestResource =
        StudentObjectMother.getCreateStudentRequestResourceV1();
    createStudentRequestResource.setEmail(" ");

    // when
    AbelfgDeveloperException exception =
        assertThrows(
            ValidationRequestException.class,
            () -> {
              createStudentRequestResourceValidator.validate(createStudentRequestResource);
            });

    // then
    assertEquals(400, exception.getErrorStatusCode());
    assertEquals(StudentErrorCodes.EMAIL_NOT_NULL, exception.getMessage());
  }

  @Test
  void test_validate_emailMinLength() {
    // given
    CreateStudentRequestResource createStudentRequestResource =
        StudentObjectMother.getCreateStudentRequestResourceV1();
    createStudentRequestResource.setEmail("a");

    // when
    AbelfgDeveloperException exception =
        assertThrows(
            ValidationRequestException.class,
            () -> {
              createStudentRequestResourceValidator.validate(createStudentRequestResource);
            });

    // then
    assertEquals(400, exception.getErrorStatusCode());
    assertEquals(StudentErrorCodes.EMAIL_LENGTH, exception.getMessage());
  }

  @Test
  void test_validate_emailMaxLength() {
    // given
    CreateStudentRequestResource createStudentRequestResource =
        StudentObjectMother.getCreateStudentRequestResourceV1();
    createStudentRequestResource.setEmail("abcdefghijabcdefghijabcdefghijabcdefghijabcdefghija");

    // when
    AbelfgDeveloperException exception =
        assertThrows(
            ValidationRequestException.class,
            () -> {
              createStudentRequestResourceValidator.validate(createStudentRequestResource);
            });

    // then
    assertEquals(400, exception.getErrorStatusCode());
    assertEquals(StudentErrorCodes.EMAIL_LENGTH, exception.getMessage());
  }

  @Test
  void test_validate_emailPattern() {
    // given
    CreateStudentRequestResource createStudentRequestResource =
        StudentObjectMother.getCreateStudentRequestResourceV1();
    createStudentRequestResource.setEmail("frodomailcom");

    // when
    AbelfgDeveloperException exception =
        assertThrows(
            ValidationRequestException.class,
            () -> {
              createStudentRequestResourceValidator.validate(createStudentRequestResource);
            });

    // then
    assertEquals(400, exception.getErrorStatusCode());
    assertEquals(StudentErrorCodes.EMAIL_PATTERN, exception.getMessage());
  }
}
