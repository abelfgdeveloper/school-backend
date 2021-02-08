package es.abelfgdeveloper.school.service.students.usecase.v1.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import es.abelfgdeveloper.common.api.v1.resource.response.ErrorResponseResource;
import es.abelfgdeveloper.school.service.students.api.v1.resource.response.StudentResponseResource;
import es.abelfgdeveloper.school.service.students.model.entity.StudentEntity;
import es.abelfgdeveloper.school.service.students.objectmother.StudentObjectMother;
import es.abelfgdeveloper.school.service.students.util.CommonApiTest;
import es.abelfgdeveloper.school.service.students.util.StudentErrorCodes;
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
class FindStudentByIdUseCaseImplTest extends CommonApiTest {

  private static final String BASE_ENDPOINT = "/students/v1/{studentId}";

  @Test
  void test_find_ok() throws Exception {
    // given
    cleanDataBase();
    StudentEntity studentInDataBase =
        studentSpringDataJpaRepository.save(StudentObjectMother.getStudentEntity());

    // when
    MockHttpServletRequestBuilder requestBuilder =
        MockMvcRequestBuilders.get(BASE_ENDPOINT, studentInDataBase.getId());
    MockHttpServletResponse mockHttpResponse =
        mvc.perform(requestBuilder).andReturn().getResponse();

    HttpStatus responseStatus = HttpStatus.valueOf(mockHttpResponse.getStatus());
    StudentResponseResource response =
        convertJsonAsStringToObject(
            mockHttpResponse.getContentAsString(), StudentResponseResource.class);

    // then
    assertEquals(HttpStatus.OK, responseStatus);
    assertEquals(studentInDataBase.getId(), response.getId());
    assertEquals(studentInDataBase.getFirstName(), response.getFirstName());
    assertEquals(studentInDataBase.getLastName(), response.getLastName());
    assertEquals(studentInDataBase.getEmail(), response.getEmail());
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
    assertEquals(StudentErrorCodes.ID_NOT_FOUND, response.getCode());
  }
}
