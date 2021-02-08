package es.abelfgdeveloper.school.service.students.usecase.v1.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import es.abelfgdeveloper.school.service.students.api.v1.resource.response.StudentPaginatedResponseResource;
import es.abelfgdeveloper.school.service.students.model.entity.StudentEntity;
import es.abelfgdeveloper.school.service.students.objectmother.StudentObjectMother;
import es.abelfgdeveloper.school.service.students.util.CommonApiTest;
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
class FindStudentsPaginatedUseCaseImplTest extends CommonApiTest {

  private static final String BASE_ENDPOINT = "/students/v1";
  private static final String QUERY_PARAM_QUERY = "query";

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
    StudentPaginatedResponseResource response =
        convertJsonAsStringToObject(
            mockHttpResponse.getContentAsString(), StudentPaginatedResponseResource.class);

    // then
    assertEquals(HttpStatus.OK, responseStatus);
    assertEquals(1, response.getStudents().size());
    assertEquals(studentInDataBase.getId(), response.getStudents().get(0).getId());
    assertEquals(studentInDataBase.getFirstName(), response.getStudents().get(0).getFirstName());
    assertEquals(studentInDataBase.getLastName(), response.getStudents().get(0).getLastName());
    assertEquals(studentInDataBase.getEmail(), response.getStudents().get(0).getEmail());
    assertNotNull(response.getStudents().get(0).getCreateAt());
  }

  @Test
  void test_find_ok_queryParams() throws Exception {
    // given
    cleanDataBase();
    StudentEntity studentInDataBase =
        studentSpringDataJpaRepository.save(StudentObjectMother.getStudentEntity());

    // when
    MockHttpServletRequestBuilder requestBuilder =
        MockMvcRequestBuilders.get(BASE_ENDPOINT, studentInDataBase.getId())
            .queryParam(QUERY_PARAM_QUERY, studentInDataBase.getEmail());
    MockHttpServletResponse mockHttpResponse =
        mvc.perform(requestBuilder).andReturn().getResponse();

    HttpStatus responseStatus = HttpStatus.valueOf(mockHttpResponse.getStatus());
    StudentPaginatedResponseResource response =
        convertJsonAsStringToObject(
            mockHttpResponse.getContentAsString(), StudentPaginatedResponseResource.class);

    // then
    assertEquals(HttpStatus.OK, responseStatus);
    assertEquals(1, response.getStudents().size());
    assertEquals(studentInDataBase.getId(), response.getStudents().get(0).getId());
    assertEquals(studentInDataBase.getFirstName(), response.getStudents().get(0).getFirstName());
    assertEquals(studentInDataBase.getLastName(), response.getStudents().get(0).getLastName());
    assertEquals(studentInDataBase.getEmail(), response.getStudents().get(0).getEmail());
    assertNotNull(response.getStudents().get(0).getCreateAt());
  }
}
