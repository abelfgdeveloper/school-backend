package es.abelfgdeveloper.school.service.courses.usecase.v1.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import es.abelfgdeveloper.common.api.v1.resource.response.ErrorResponseResource;
import es.abelfgdeveloper.school.service.courses.api.v1.resource.response.CourseResponseResource;
import es.abelfgdeveloper.school.service.courses.model.entity.CourseEntity;
import es.abelfgdeveloper.school.service.courses.objectmother.CourseObjectMother;
import es.abelfgdeveloper.school.service.courses.util.CommonApiTest;
import es.abelfgdeveloper.school.service.courses.util.CourseErrorCodes;
import es.abelfgdeveloper.school.service.students.api.v1.resource.response.StudentResponseResource;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import java.util.Arrays;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureEmbeddedDatabase
@AutoConfigureMockMvc
@AutoConfigureWireMock(port = 8079)
class AddStudentToCourseUseCaseImplTest extends CommonApiTest {

  private static final String BASE_ENDPOINT = "/courses/v1/{courseId}/students/{studentId}";

  @Autowired private WireMockServer wireMockServer;

  @Test
  void test_add_ok() throws Exception {
    // given
    cleanDataBase();
    String studentId = UUID.randomUUID().toString();
    CourseEntity courseEntity =
        courseSpringDataJpaRepository.save(CourseObjectMother.getCourseEntity());

    this.wireMockServer.stubFor(
        WireMock.get("/students/v1/" + studentId)
            .willReturn(
                WireMock.aResponse()
                    .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                    .withStatus(200)
                    .withBody(
                        convertObjectToJsonAsString(
                            StudentResponseResource.builder().id(studentId).build()))));

    // when
    MockHttpServletRequestBuilder requestBuilder =
        MockMvcRequestBuilders.put(BASE_ENDPOINT, courseEntity.getId(), studentId);
    MockHttpServletResponse mockHttpResponse =
        mvc.perform(requestBuilder).andReturn().getResponse();
    setCharacterEncoding(mockHttpResponse);

    HttpStatus responseStatus = HttpStatus.valueOf(mockHttpResponse.getStatus());
    CourseResponseResource response =
        convertJsonAsStringToObject(
            mockHttpResponse.getContentAsString(), CourseResponseResource.class);

    // then validate the response
    assertEquals(HttpStatus.OK, responseStatus);
    assertEquals(1, response.getStudents().size());
  }

  @Test
  void test_add_ko_studentYetInCourse() throws Exception {
    // given
    cleanDataBase();
    String studentId = UUID.randomUUID().toString();
    CourseEntity courseEntity = CourseObjectMother.getCourseEntity();
    courseEntity.setStudents(Arrays.asList(studentId));
    courseEntity = courseSpringDataJpaRepository.save(courseEntity);

    // when
    MockHttpServletRequestBuilder requestBuilder =
        MockMvcRequestBuilders.put(BASE_ENDPOINT, courseEntity.getId(), studentId);
    MockHttpServletResponse mockHttpResponse =
        mvc.perform(requestBuilder).andReturn().getResponse();
    setCharacterEncoding(mockHttpResponse);

    HttpStatus responseStatus = HttpStatus.valueOf(mockHttpResponse.getStatus());
    ErrorResponseResource response =
        convertJsonAsStringToObject(
            mockHttpResponse.getContentAsString(), ErrorResponseResource.class);

    // then validate the response
    assertEquals(HttpStatus.CONFLICT, responseStatus);
    assertEquals(CourseErrorCodes.STUDENT_ID_EXIST, response.getCode());
  }

  @Test
  void test_add_ko_studentNotExist() throws Exception {
    // given
    cleanDataBase();
    String studentId = UUID.randomUUID().toString();
    CourseEntity courseEntity =
        courseSpringDataJpaRepository.save(CourseObjectMother.getCourseEntity());

    this.wireMockServer.stubFor(
        WireMock.get("/students/v1/" + studentId)
            .willReturn(
                WireMock.aResponse()
                    .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                    .withStatus(404)
                    .withBody(
                        convertObjectToJsonAsString(ErrorResponseResource.builder().build()))));

    // when
    MockHttpServletRequestBuilder requestBuilder =
        MockMvcRequestBuilders.put(BASE_ENDPOINT, courseEntity.getId(), studentId);
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
