package es.abelfgdeveloper.school.service.courses.usecase.v1.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import es.abelfgdeveloper.common.api.v1.resource.response.ErrorResponseResource;
import es.abelfgdeveloper.school.service.courses.api.v1.resource.request.CreateCourseRequestResource;
import es.abelfgdeveloper.school.service.courses.api.v1.resource.response.CourseResponseResource;
import es.abelfgdeveloper.school.service.courses.model.entity.CourseEntity;
import es.abelfgdeveloper.school.service.courses.objectmother.CourseObjectMother;
import es.abelfgdeveloper.school.service.courses.util.CommonApiTest;
import es.abelfgdeveloper.school.service.courses.util.CourseErrorCodes;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureEmbeddedDatabase
@AutoConfigureMockMvc
class CreateCourseUseCaseImplTest extends CommonApiTest {

  private static final String BASE_ENDPOINT = "/courses/v1";

  @Test
  void test_create_ok() throws Exception {
    // given
    cleanDataBase();
    CreateCourseRequestResource request = CourseObjectMother.getCreateCourseRequestResourceV1();

    // when
    MockHttpServletRequestBuilder requestBuilder =
        MockMvcRequestBuilders.post(BASE_ENDPOINT)
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
            .content(convertObjectToJsonAsString(request));
    MockHttpServletResponse mockHttpResponse =
        mvc.perform(requestBuilder).andReturn().getResponse();
    setCharacterEncoding(mockHttpResponse);

    HttpStatus responseStatus = HttpStatus.valueOf(mockHttpResponse.getStatus());
    CourseResponseResource response =
        convertJsonAsStringToObject(
            mockHttpResponse.getContentAsString(), CourseResponseResource.class);

    // then validate the response
    assertEquals(HttpStatus.CREATED, responseStatus);
    assertNotNull(response.getId());
    assertEquals(request.getName(), response.getName());
    assertNotNull(response.getCreateAt());

    // then validate the database
    CourseEntity courseInDataBase = courseSpringDataJpaRepository.findById(response.getId()).get();
    assertNotNull(courseInDataBase.getId());
    assertEquals(request.getName(), courseInDataBase.getName());
    assertNotNull(courseInDataBase.getCreateAt());
  }

  @Test
  void test_create_ko_nameExist() throws Exception {
    // given
    cleanDataBase();
    CourseEntity courseInDataBase =
        courseSpringDataJpaRepository.save(CourseObjectMother.getCourseEntity());
    CreateCourseRequestResource request = CourseObjectMother.getCreateCourseRequestResourceV1();
    request.setName(courseInDataBase.getName());

    // when
    MockHttpServletRequestBuilder requestBuilder =
        MockMvcRequestBuilders.post(BASE_ENDPOINT)
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
            .content(convertObjectToJsonAsString(request));
    MockHttpServletResponse mockHttpResponse =
        mvc.perform(requestBuilder).andReturn().getResponse();
    setCharacterEncoding(mockHttpResponse);

    HttpStatus responseStatus = HttpStatus.valueOf(mockHttpResponse.getStatus());
    ErrorResponseResource response =
        convertJsonAsStringToObject(
            mockHttpResponse.getContentAsString(), ErrorResponseResource.class);

    // then
    assertEquals(HttpStatus.BAD_REQUEST, responseStatus);
    assertNotNull(response.getCode());
    assertEquals(CourseErrorCodes.NAME_EXIST, response.getCode());
  }
}
