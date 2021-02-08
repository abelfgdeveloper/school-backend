package es.abelfgdeveloper.school.service.students.api.v1.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import es.abelfgdeveloper.common.api.v1.resource.response.PaginationResponseResource;
import es.abelfgdeveloper.common.api.v1.resource.validator.PaginationResponseResourceValidator;
import es.abelfgdeveloper.common.exception.AbelfgDeveloperException;
import es.abelfgdeveloper.common.exception.server.ValidationResponseException;
import es.abelfgdeveloper.school.service.students.api.v1.resource.response.StudentPaginatedResponseResource;
import es.abelfgdeveloper.school.service.students.api.v1.resource.response.StudentResponseResource;
import es.abelfgdeveloper.school.service.students.objectmother.StudentObjectMother;
import es.abelfgdeveloper.school.service.students.util.StudentErrorCodes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class StudentPaginatedResponseResourceValidatorTest {

  @Mock private StudentResponseResourceValidator studentResponseResourceValidator;
  @Mock private PaginationResponseResourceValidator paginationResponseResourceValidator;

  private StudentPaginatedResponseResourceValidator studentPaginatedResponseResourceValidator;

  @BeforeEach
  void setUp() {
    studentPaginatedResponseResourceValidator =
        new StudentPaginatedResponseResourceValidator(
            studentResponseResourceValidator, paginationResponseResourceValidator);
  }

  @Test
  void test_validate_ok() {
    // given
    StudentPaginatedResponseResource studentPaginatedResponseResource =
        StudentObjectMother.getStudentPaginatedResponseResourceV1();

    Mockito.doNothing()
        .when(studentResponseResourceValidator)
        .validate(Mockito.any(StudentResponseResource.class));
    Mockito.doNothing()
        .when(paginationResponseResourceValidator)
        .validate(Mockito.any(PaginationResponseResource.class));

    // when
    studentPaginatedResponseResourceValidator.validate(studentPaginatedResponseResource);

    // then
    Mockito.verify(paginationResponseResourceValidator, Mockito.times(1))
        .validate(Mockito.any(PaginationResponseResource.class));
    Mockito.verify(studentResponseResourceValidator, Mockito.times(1))
        .validate(Mockito.any(StudentResponseResource.class));
  }

  @Test
  void test_validate_objectNull() {
    // given
    StudentPaginatedResponseResource studentPaginatedResponseResource = null;

    // when
    AbelfgDeveloperException exception =
        assertThrows(
            ValidationResponseException.class,
            () -> {
              studentPaginatedResponseResourceValidator.validate(studentPaginatedResponseResource);
            });

    // then
    assertEquals(500, exception.getErrorStatusCode());
    assertEquals(StudentErrorCodes.PAGINATED_RESPONSE_BODY_NOT_NULL, exception.getMessage());
  }
}
