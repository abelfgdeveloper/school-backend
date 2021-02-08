package es.abelfgdeveloper.school.service.students.api.v1.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import es.abelfgdeveloper.common.exception.AbelfgDeveloperException;
import es.abelfgdeveloper.common.exception.server.ValidationResponseException;
import es.abelfgdeveloper.school.service.students.api.v1.resource.response.StudentResponseResource;
import es.abelfgdeveloper.school.service.students.objectmother.StudentObjectMother;
import es.abelfgdeveloper.school.service.students.util.StudentErrorCodes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class StudentResponseResourceValidatorTest {

  private StudentResponseResourceValidator studentResponseResourceValidator;

  @BeforeEach
  void setUp() {
    studentResponseResourceValidator = new StudentResponseResourceValidator();
  }

  @Test
  void test_validate_ok() {
    // given
    StudentResponseResource studentResponseResource =
        StudentObjectMother.getStudentResponseResourceV1();

    // when
    studentResponseResourceValidator.validate(studentResponseResource);

    // then
  }

  @Test
  void test_validate_objectNull() {
    // given
    StudentResponseResource studentResponseResource = null;

    // when
    AbelfgDeveloperException exception =
        assertThrows(
            ValidationResponseException.class,
            () -> {
              studentResponseResourceValidator.validate(studentResponseResource);
            });

    // then
    assertEquals(500, exception.getErrorStatusCode());
    assertEquals(StudentErrorCodes.RESPONSE_BODY_NOT_NULL, exception.getMessage());
  }

  @Test
  void test_validate_idNull() {
    // given
    StudentResponseResource studentResponseResource =
        StudentObjectMother.getStudentResponseResourceV1();
    studentResponseResource.setId(null);

    // when
    AbelfgDeveloperException exception =
        assertThrows(
            ValidationResponseException.class,
            () -> {
              studentResponseResourceValidator.validate(studentResponseResource);
            });

    // then
    assertEquals(500, exception.getErrorStatusCode());
    assertEquals(StudentErrorCodes.ID_NOT_NULL, exception.getMessage());
  }

  @Test
  void test_validate_firstNameNull() {
    // given
    StudentResponseResource studentResponseResource =
        StudentObjectMother.getStudentResponseResourceV1();
    studentResponseResource.setFirstName(null);

    // when
    AbelfgDeveloperException exception =
        assertThrows(
            ValidationResponseException.class,
            () -> {
              studentResponseResourceValidator.validate(studentResponseResource);
            });

    // then
    assertEquals(500, exception.getErrorStatusCode());
    assertEquals(StudentErrorCodes.FIRST_NAME_NOT_NULL, exception.getMessage());
  }

  @Test
  void test_validate_firstNameEmpty() {
    // given
    StudentResponseResource studentResponseResource =
        StudentObjectMother.getStudentResponseResourceV1();
    studentResponseResource.setFirstName(" ");

    // when
    AbelfgDeveloperException exception =
        assertThrows(
            ValidationResponseException.class,
            () -> {
              studentResponseResourceValidator.validate(studentResponseResource);
            });

    // then
    assertEquals(500, exception.getErrorStatusCode());
    assertEquals(StudentErrorCodes.FIRST_NAME_NOT_NULL, exception.getMessage());
  }

  @Test
  void test_validate_firstNameMinLength() {
    // given
    StudentResponseResource studentResponseResource =
        StudentObjectMother.getStudentResponseResourceV1();
    studentResponseResource.setFirstName("a");

    // when
    AbelfgDeveloperException exception =
        assertThrows(
            ValidationResponseException.class,
            () -> {
              studentResponseResourceValidator.validate(studentResponseResource);
            });

    // then
    assertEquals(500, exception.getErrorStatusCode());
    assertEquals(StudentErrorCodes.FIRST_NAME_LENGTH, exception.getMessage());
  }

  @Test
  void test_validate_firstNameMaxLength() {
    // given
    StudentResponseResource studentResponseResource =
        StudentObjectMother.getStudentResponseResourceV1();
    studentResponseResource.setFirstName("abcdefghijabcdefghijabcdef");

    // when
    AbelfgDeveloperException exception =
        assertThrows(
            ValidationResponseException.class,
            () -> {
              studentResponseResourceValidator.validate(studentResponseResource);
            });

    // then
    assertEquals(500, exception.getErrorStatusCode());
    assertEquals(StudentErrorCodes.FIRST_NAME_LENGTH, exception.getMessage());
  }

  @Test
  void test_validate_lastNameNull() {
    // given
    StudentResponseResource studentResponseResource =
        StudentObjectMother.getStudentResponseResourceV1();
    studentResponseResource.setLastName(null);

    // when
    AbelfgDeveloperException exception =
        assertThrows(
            ValidationResponseException.class,
            () -> {
              studentResponseResourceValidator.validate(studentResponseResource);
            });

    // then
    assertEquals(500, exception.getErrorStatusCode());
    assertEquals(StudentErrorCodes.LAST_NAME_NOT_NULL, exception.getMessage());
  }

  @Test
  void test_validate_lastNameEmpty() {
    // given
    StudentResponseResource studentResponseResource =
        StudentObjectMother.getStudentResponseResourceV1();
    studentResponseResource.setLastName(" ");

    // when
    AbelfgDeveloperException exception =
        assertThrows(
            ValidationResponseException.class,
            () -> {
              studentResponseResourceValidator.validate(studentResponseResource);
            });

    // then
    assertEquals(500, exception.getErrorStatusCode());
    assertEquals(StudentErrorCodes.LAST_NAME_NOT_NULL, exception.getMessage());
  }

  @Test
  void test_validate_lastNameMinLength() {
    // given
    StudentResponseResource studentResponseResource =
        StudentObjectMother.getStudentResponseResourceV1();
    studentResponseResource.setLastName("a");

    // when
    AbelfgDeveloperException exception =
        assertThrows(
            ValidationResponseException.class,
            () -> {
              studentResponseResourceValidator.validate(studentResponseResource);
            });

    // then
    assertEquals(500, exception.getErrorStatusCode());
    assertEquals(StudentErrorCodes.LAST_NAME_LENGTH, exception.getMessage());
  }

  @Test
  void test_validate_lastNameMaxLength() {
    // given
    StudentResponseResource studentResponseResource =
        StudentObjectMother.getStudentResponseResourceV1();
    studentResponseResource.setLastName("abcdefghijabcdefghijabcdef");

    // when
    AbelfgDeveloperException exception =
        assertThrows(
            ValidationResponseException.class,
            () -> {
              studentResponseResourceValidator.validate(studentResponseResource);
            });

    // then
    assertEquals(500, exception.getErrorStatusCode());
    assertEquals(StudentErrorCodes.LAST_NAME_LENGTH, exception.getMessage());
  }

  @Test
  void test_validate_emailNull() {
    // given
    StudentResponseResource studentResponseResource =
        StudentObjectMother.getStudentResponseResourceV1();
    studentResponseResource.setEmail(null);

    // when
    AbelfgDeveloperException exception =
        assertThrows(
            ValidationResponseException.class,
            () -> {
              studentResponseResourceValidator.validate(studentResponseResource);
            });

    // then
    assertEquals(500, exception.getErrorStatusCode());
    assertEquals(StudentErrorCodes.EMAIL_NOT_NULL, exception.getMessage());
  }

  @Test
  void test_validate_emailEmpty() {
    // given
    StudentResponseResource studentResponseResource =
        StudentObjectMother.getStudentResponseResourceV1();
    studentResponseResource.setEmail(" ");

    // when
    AbelfgDeveloperException exception =
        assertThrows(
            ValidationResponseException.class,
            () -> {
              studentResponseResourceValidator.validate(studentResponseResource);
            });

    // then
    assertEquals(500, exception.getErrorStatusCode());
    assertEquals(StudentErrorCodes.EMAIL_NOT_NULL, exception.getMessage());
  }

  @Test
  void test_validate_emailMinLength() {
    // given
    StudentResponseResource studentResponseResource =
        StudentObjectMother.getStudentResponseResourceV1();
    studentResponseResource.setEmail("a");

    // when
    AbelfgDeveloperException exception =
        assertThrows(
            ValidationResponseException.class,
            () -> {
              studentResponseResourceValidator.validate(studentResponseResource);
            });

    // then
    assertEquals(500, exception.getErrorStatusCode());
    assertEquals(StudentErrorCodes.EMAIL_LENGTH, exception.getMessage());
  }

  @Test
  void test_validate_emailMaxLength() {
    // given
    StudentResponseResource studentResponseResource =
        StudentObjectMother.getStudentResponseResourceV1();
    studentResponseResource.setEmail("abcdefghijabcdefghijabcdefghijabcdefghijabcdefghija");

    // when
    AbelfgDeveloperException exception =
        assertThrows(
            ValidationResponseException.class,
            () -> {
              studentResponseResourceValidator.validate(studentResponseResource);
            });

    // then
    assertEquals(500, exception.getErrorStatusCode());
    assertEquals(StudentErrorCodes.EMAIL_LENGTH, exception.getMessage());
  }

  @Test
  void test_validate_emailPattern() {
    // given
    StudentResponseResource studentResponseResource =
        StudentObjectMother.getStudentResponseResourceV1();
    studentResponseResource.setEmail("frodomailcom");

    // when
    AbelfgDeveloperException exception =
        assertThrows(
            ValidationResponseException.class,
            () -> {
              studentResponseResourceValidator.validate(studentResponseResource);
            });

    // then
    assertEquals(500, exception.getErrorStatusCode());
    assertEquals(StudentErrorCodes.EMAIL_PATTERN, exception.getMessage());
  }

  @Test
  void test_validate_createAtNull() {
    // given
    StudentResponseResource studentResponseResource =
        StudentObjectMother.getStudentResponseResourceV1();
    studentResponseResource.setCreateAt(null);

    // when
    AbelfgDeveloperException exception =
        assertThrows(
            ValidationResponseException.class,
            () -> {
              studentResponseResourceValidator.validate(studentResponseResource);
            });

    // then
    assertEquals(500, exception.getErrorStatusCode());
    assertEquals(StudentErrorCodes.CREATE_AT_NOT_NULL, exception.getMessage());
  }
}
