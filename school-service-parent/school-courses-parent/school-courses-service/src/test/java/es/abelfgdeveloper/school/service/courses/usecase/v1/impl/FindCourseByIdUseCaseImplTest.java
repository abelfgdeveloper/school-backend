package es.abelfgdeveloper.school.service.courses.usecase.v1.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import es.abelfgdeveloper.common.api.v1.resource.response.ErrorResponseResource;
import es.abelfgdeveloper.school.service.courses.api.v1.resource.response.CourseResponseResource;
import es.abelfgdeveloper.school.service.courses.model.entity.CourseEntity;
import es.abelfgdeveloper.school.service.courses.objectmother.CourseObjectMother;
import es.abelfgdeveloper.school.service.courses.util.CommonApiTest;
import es.abelfgdeveloper.school.service.courses.util.CourseErrorCodes;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
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
class FindCourseByIdUseCaseImplTest extends CommonApiTest {

  private static final String BASE_ENDPOINT = "/courses/v1/{courseId}";

  @Test
  void test_find_ok() throws Exception {
    // given
    cleanDataBase();
    CourseEntity courseInDataBase =
        courseSpringDataJpaRepository.save(CourseObjectMother.getCourseEntity());

    // when
    MockHttpServletRequestBuilder requestBuilder =
        MockMvcRequestBuilders.get(BASE_ENDPOINT, courseInDataBase.getId());
    MockHttpServletResponse mockHttpResponse =
        mvc.perform(requestBuilder).andReturn().getResponse();
    setCharacterEncoding(mockHttpResponse);

    HttpStatus responseStatus = HttpStatus.valueOf(mockHttpResponse.getStatus());
    CourseResponseResource response =
        convertJsonAsStringToObject(
            mockHttpResponse.getContentAsString(), CourseResponseResource.class);

    // then
    assertEquals(HttpStatus.OK, responseStatus);
    assertEquals(courseInDataBase.getId(), response.getId());
    assertEquals(courseInDataBase.getName(), response.getName());
    assertNotNull(response.getCreateAt());
  }

  @Test
  void test_delete_ko_notFound() throws Exception {
    // given
    cleanDataBase();
    String id = UUID.randomUUID().toString();

    // when
    MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(BASE_ENDPOINT, id);
    MockHttpServletResponse mockHttpResponse =
        mvc.perform(requestBuilder).andReturn().getResponse();

    HttpStatus responseStatus = HttpStatus.valueOf(mockHttpResponse.getStatus());
    ErrorResponseResource response =
        convertJsonAsStringToObject(
            mockHttpResponse.getContentAsString(), ErrorResponseResource.class);

    // then
    assertEquals(HttpStatus.NOT_FOUND, responseStatus);
    assertEquals(CourseErrorCodes.ID_NOT_FOUND, response.getCode());
  }
}
