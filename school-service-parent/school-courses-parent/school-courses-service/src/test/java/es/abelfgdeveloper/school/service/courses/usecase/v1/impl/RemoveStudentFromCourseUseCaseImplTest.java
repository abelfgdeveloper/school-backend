package es.abelfgdeveloper.school.service.courses.usecase.v1.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import es.abelfgdeveloper.common.api.v1.resource.response.ErrorResponseResource;
import es.abelfgdeveloper.school.service.courses.api.v1.resource.response.CourseResponseResource;
import es.abelfgdeveloper.school.service.courses.model.entity.CourseEntity;
import es.abelfgdeveloper.school.service.courses.objectmother.CourseObjectMother;
import es.abelfgdeveloper.school.service.courses.util.CommonApiTest;
import es.abelfgdeveloper.school.service.courses.util.CourseErrorCodes;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import java.util.Arrays;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureEmbeddedDatabase
@AutoConfigureMockMvc
class RemoveStudentFromCourseUseCaseImplTest extends CommonApiTest {

  private static final String BASE_ENDPOINT = "/courses/v1/{courseId}/students/{studentId}";

  @Test
  void test_remove_ok() throws Exception {
    // given
    cleanDataBase();
    String studentId = UUID.randomUUID().toString();
    CourseEntity courseEntity = CourseObjectMother.getCourseEntity();
    courseEntity.setStudents(Arrays.asList(studentId));
    courseEntity = courseSpringDataJpaRepository.save(courseEntity);

    // when
    MockHttpServletRequestBuilder requestBuilder =
        MockMvcRequestBuilders.delete(BASE_ENDPOINT, courseEntity.getId(), studentId);
    MockHttpServletResponse mockHttpResponse =
        mvc.perform(requestBuilder).andReturn().getResponse();
    setCharacterEncoding(mockHttpResponse);

    HttpStatus responseStatus = HttpStatus.valueOf(mockHttpResponse.getStatus());
    CourseResponseResource response =
        convertJsonAsStringToObject(
            mockHttpResponse.getContentAsString(), CourseResponseResource.class);

    // then validate the response
    assertEquals(HttpStatus.OK, responseStatus);
    assertEquals(0, response.getStudents().size());
  }

  @Test
  void test_remove_ko_studentNotExist() throws Exception {
    // given
    cleanDataBase();
    String studentId = UUID.randomUUID().toString();
    CourseEntity courseEntity = CourseObjectMother.getCourseEntity();
    courseEntity = courseSpringDataJpaRepository.save(courseEntity);

    // when
    MockHttpServletRequestBuilder requestBuilder =
        MockMvcRequestBuilders.delete(BASE_ENDPOINT, courseEntity.getId(), studentId);
    MockHttpServletResponse mockHttpResponse =
        mvc.perform(requestBuilder).andReturn().getResponse();
    setCharacterEncoding(mockHttpResponse);

    HttpStatus responseStatus = HttpStatus.valueOf(mockHttpResponse.getStatus());
    ErrorResponseResource response =
        convertJsonAsStringToObject(
            mockHttpResponse.getContentAsString(), ErrorResponseResource.class);

    // then validate the response
    assertEquals(HttpStatus.BAD_REQUEST, responseStatus);
    assertEquals(CourseErrorCodes.STUDENT_ID_NOT_EXIST, response.getCode());
  }
}
