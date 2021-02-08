package es.abelfgdeveloper.school.service.students.usecase.v1.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import es.abelfgdeveloper.common.api.v1.resource.response.ErrorResponseResource;
import es.abelfgdeveloper.school.service.students.api.v1.resource.request.CreateStudentRequestResource;
import es.abelfgdeveloper.school.service.students.api.v1.resource.response.StudentResponseResource;
import es.abelfgdeveloper.school.service.students.model.entity.StudentEntity;
import es.abelfgdeveloper.school.service.students.objectmother.StudentObjectMother;
import es.abelfgdeveloper.school.service.students.util.CommonApiTest;
import es.abelfgdeveloper.school.service.students.util.StudentErrorCodes;
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
class CreateStudentUseCaseImplTest extends CommonApiTest {

  private static final String BASE_ENDPOINT = "/students/v1";

  @Test
  void test_create_ok() throws Exception {
    // given
    cleanDataBase();
    CreateStudentRequestResource request = StudentObjectMother.getCreateStudentRequestResourceV1();

    // when
    MockHttpServletRequestBuilder requestBuilder =
        MockMvcRequestBuilders.post(BASE_ENDPOINT)
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
            .content(convertObjectToJsonAsString(request));
    MockHttpServletResponse mockHttpResponse =
        mvc.perform(requestBuilder).andReturn().getResponse();

    HttpStatus responseStatus = HttpStatus.valueOf(mockHttpResponse.getStatus());
    StudentResponseResource response =
        convertJsonAsStringToObject(
            mockHttpResponse.getContentAsString(), StudentResponseResource.class);

    // then validate the response
    assertEquals(HttpStatus.CREATED, responseStatus);
    assertNotNull(response.getId());
    assertEquals(request.getFirstName(), response.getFirstName());
    assertEquals(request.getLastName(), response.getLastName());
    assertEquals(request.getEmail(), response.getEmail());
    assertNotNull(response.getCreateAt());

    // then validate the database
    StudentEntity studentInDataBase =
        studentSpringDataJpaRepository.findById(response.getId()).get();
    assertNotNull(studentInDataBase.getId());
    assertEquals(request.getFirstName(), studentInDataBase.getFirstName());
    assertEquals(request.getLastName(), studentInDataBase.getLastName());
    assertEquals(request.getEmail(), studentInDataBase.getEmail());
    assertNotNull(studentInDataBase.getCreateAt());
  }

  @Test
  void test_create_ko_emailExist() throws Exception {
    // given
    cleanDataBase();
    StudentEntity studentInDataBase =
        studentSpringDataJpaRepository.save(StudentObjectMother.getStudentEntity());
    CreateStudentRequestResource request = StudentObjectMother.getCreateStudentRequestResourceV1();
    request.setEmail(studentInDataBase.getEmail());

    // when
    MockHttpServletRequestBuilder requestBuilder =
        MockMvcRequestBuilders.post(BASE_ENDPOINT)
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
            .content(convertObjectToJsonAsString(request));
    MockHttpServletResponse mockHttpResponse =
        mvc.perform(requestBuilder).andReturn().getResponse();

    HttpStatus responseStatus = HttpStatus.valueOf(mockHttpResponse.getStatus());
    ErrorResponseResource response =
        convertJsonAsStringToObject(
            mockHttpResponse.getContentAsString(), ErrorResponseResource.class);

    // then
    assertEquals(HttpStatus.BAD_REQUEST, responseStatus);
    assertNotNull(response.getCode());
    assertEquals(StudentErrorCodes.EMAIL_EXIST, response.getCode());
  }
}
