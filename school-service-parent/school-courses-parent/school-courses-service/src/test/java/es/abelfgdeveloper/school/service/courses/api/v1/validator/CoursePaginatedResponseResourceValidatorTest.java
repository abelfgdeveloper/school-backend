package es.abelfgdeveloper.school.service.courses.api.v1.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import es.abelfgdeveloper.common.api.v1.resource.response.PaginationResponseResource;
import es.abelfgdeveloper.common.api.v1.resource.validator.PaginationResponseResourceValidator;
import es.abelfgdeveloper.common.exception.AbelfgDeveloperException;
import es.abelfgdeveloper.common.exception.server.ValidationResponseException;
import es.abelfgdeveloper.school.service.courses.api.v1.resource.response.CoursePaginatedResponseResource;
import es.abelfgdeveloper.school.service.courses.api.v1.resource.response.CourseResponseResource;
import es.abelfgdeveloper.school.service.courses.objectmother.CourseObjectMother;
import es.abelfgdeveloper.school.service.courses.util.CourseErrorCodes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CoursePaginatedResponseResourceValidatorTest {

  @Mock private CourseResponseResourceValidator courseResponseResourceValidator;
  @Mock private PaginationResponseResourceValidator paginationResponseResourceValidator;

  private CoursePaginatedResponseResourceValidator coursePaginatedResponseResourceValidator;

  @BeforeEach
  void setUp() {
    coursePaginatedResponseResourceValidator =
        new CoursePaginatedResponseResourceValidator(
            courseResponseResourceValidator, paginationResponseResourceValidator);
  }

  @Test
  void test_validate_ok() {
    // given
    CoursePaginatedResponseResource coursePaginatedResponseResource =
        CourseObjectMother.getCoursePaginatedResponseResourceV1();

    Mockito.doNothing()
        .when(courseResponseResourceValidator)
        .validate(Mockito.any(CourseResponseResource.class));
    Mockito.doNothing()
        .when(paginationResponseResourceValidator)
        .validate(Mockito.any(PaginationResponseResource.class));

    // when
    coursePaginatedResponseResourceValidator.validate(coursePaginatedResponseResource);

    // then
    Mockito.verify(paginationResponseResourceValidator, Mockito.times(1))
        .validate(Mockito.any(PaginationResponseResource.class));
    Mockito.verify(courseResponseResourceValidator, Mockito.times(1))
        .validate(Mockito.any(CourseResponseResource.class));
  }

  @Test
  void test_validate_objectNull() {
    // given
    CoursePaginatedResponseResource coursePaginatedResponseResource = null;

    // when
    AbelfgDeveloperException exception =
        assertThrows(
            ValidationResponseException.class,
            () -> {
              coursePaginatedResponseResourceValidator.validate(coursePaginatedResponseResource);
            });

    // then
    assertEquals(500, exception.getErrorStatusCode());
    assertEquals(CourseErrorCodes.PAGINATED_RESPONSE_BODY_NOT_NULL, exception.getMessage());
  }
}
