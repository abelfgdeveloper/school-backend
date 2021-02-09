package es.abelfgdeveloper.school.service.courses.usecase.v1.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import es.abelfgdeveloper.school.service.courses.api.v1.resource.response.CoursePaginatedResponseResource;
import es.abelfgdeveloper.school.service.courses.model.entity.CourseEntity;
import es.abelfgdeveloper.school.service.courses.objectmother.CourseObjectMother;
import es.abelfgdeveloper.school.service.courses.util.CommonApiTest;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
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
class FindCoursesPaginatedUseCaseImplTest extends CommonApiTest {

  private static final String BASE_ENDPOINT = "/courses/v1";
  private static final String QUERY_PARAM_QUERY = "query";

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
    CoursePaginatedResponseResource response =
        convertJsonAsStringToObject(
            mockHttpResponse.getContentAsString(), CoursePaginatedResponseResource.class);

    // then
    assertEquals(HttpStatus.OK, responseStatus);
    assertEquals(1, response.getCourses().size());
    assertEquals(courseInDataBase.getId(), response.getCourses().get(0).getId());
    assertEquals(courseInDataBase.getName(), response.getCourses().get(0).getName());
    assertNotNull(response.getCourses().get(0).getCreateAt());
  }

  @Test
  void test_find_ok_queryParams() throws Exception {
    // given
    cleanDataBase();
    CourseEntity studentInDataBase =
        courseSpringDataJpaRepository.save(CourseObjectMother.getCourseEntity());

    // when
    MockHttpServletRequestBuilder requestBuilder =
        MockMvcRequestBuilders.get(BASE_ENDPOINT, studentInDataBase.getId())
            .queryParam(QUERY_PARAM_QUERY, studentInDataBase.getName());
    MockHttpServletResponse mockHttpResponse =
        mvc.perform(requestBuilder).andReturn().getResponse();
    setCharacterEncoding(mockHttpResponse);

    HttpStatus responseStatus = HttpStatus.valueOf(mockHttpResponse.getStatus());
    CoursePaginatedResponseResource response =
        convertJsonAsStringToObject(
            mockHttpResponse.getContentAsString(), CoursePaginatedResponseResource.class);

    // then
    assertEquals(HttpStatus.OK, responseStatus);
    assertEquals(1, response.getCourses().size());
    assertEquals(studentInDataBase.getId(), response.getCourses().get(0).getId());
    assertEquals(studentInDataBase.getName(), response.getCourses().get(0).getName());
    assertNotNull(response.getCourses().get(0).getCreateAt());
  }
}
