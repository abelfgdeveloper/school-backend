package es.abelfgdeveloper.school.service.courses.usecase.v1.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import es.abelfgdeveloper.school.service.courses.api.v1.resource.response.CourseResponseResource;
import es.abelfgdeveloper.school.service.courses.model.entity.CourseEntity;
import es.abelfgdeveloper.school.service.courses.objectmother.CourseObjectMother;
import es.abelfgdeveloper.school.service.courses.util.CommonApiTest;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import java.util.Arrays;
import java.util.List;
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
class FindCoursesByStudentIdUseCaseImplTest extends CommonApiTest {

  private static final String BASE_ENDPOINT = "/courses/v1/students/{studentId}";

  @Test
  void test_find_ok() throws Exception {
    // given
    cleanDataBase();
    String studentId = UUID.randomUUID().toString();
    CourseEntity courseEntity = CourseObjectMother.getCourseEntity();
    courseEntity.setStudents(Arrays.asList(studentId));
    courseEntity = courseSpringDataJpaRepository.save(courseEntity);

    // when
    MockHttpServletRequestBuilder requestBuilder =
        MockMvcRequestBuilders.get(BASE_ENDPOINT, studentId);
    MockHttpServletResponse mockHttpResponse =
        mvc.perform(requestBuilder).andReturn().getResponse();
    setCharacterEncoding(mockHttpResponse);

    HttpStatus responseStatus = HttpStatus.valueOf(mockHttpResponse.getStatus());
    List<CourseResponseResource> response =
        Arrays.asList(
            convertJsonAsStringToObject(
                mockHttpResponse.getContentAsString(), CourseResponseResource[].class));

    // then validate the response
    assertEquals(HttpStatus.OK, responseStatus);
    assertEquals(1, response.size());
  }
}
