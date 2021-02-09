package es.abelfgdeveloper.school.service.courses.api.v1.mapper;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import es.abelfgdeveloper.common.api.v1.resource.mapper.PaginationMapper;
import es.abelfgdeveloper.school.service.courses.api.v1.resource.request.CreateCourseRequestResource;
import es.abelfgdeveloper.school.service.courses.api.v1.resource.request.UpdateCourseRequestResource;
import es.abelfgdeveloper.school.service.courses.api.v1.resource.response.CoursePaginatedResponseResource;
import es.abelfgdeveloper.school.service.courses.api.v1.resource.response.CourseResponseResource;
import es.abelfgdeveloper.school.service.courses.domain.Course;
import es.abelfgdeveloper.school.service.courses.domain.CoursePaginated;
import es.abelfgdeveloper.school.service.courses.objectmother.CourseObjectMother;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CourseMapperTest {

  @Mock private PaginationMapper paginationMapper;

  private CourseMapper courseMapper;

  @BeforeEach
  void setUp() {
    courseMapper = new CourseMapper(paginationMapper);
  }

  @Test
  void test_map_createCourseRequestResourceToCourse() {
    // given
    CreateCourseRequestResource request = CourseObjectMother.getCreateCourseRequestResourceV1();

    // when
    Course response = courseMapper.map(request);

    // then
    assertNull(response.getId());
    assertEquals(request.getName(), response.getName());
    assertNull(response.getCreateAt());
  }

  @Test
  void test_map_updateCourseRequestResourceToCourse() {
    // given
    UpdateCourseRequestResource request = CourseObjectMother.getUpdateCourseRequestResourceV1();
    String id = UUID.randomUUID().toString();
    // when
    Course response = courseMapper.map(id, request);

    // then
    assertEquals(id, response.getId());
    assertEquals(request.getName(), response.getName());
    assertNull(response.getCreateAt());
  }

  @Test
  void test_map_courseToCourseResponseResource() {
    // given
    Course request = CourseObjectMother.getCourse();

    // when
    CourseResponseResource response = courseMapper.map(request);

    // then
    assertEquals(request.getId(), response.getId());
    assertEquals(request.getName(), response.getName());
    assertEquals(request.getCreateAt(), response.getCreateAt());
  }

  @Test
  void test_map_coursePaginatedToCoursePaginatedResponseResource() {
    // given
    CoursePaginated request = CourseObjectMother.getCoursePaginated();
    // when
    CoursePaginatedResponseResource response = courseMapper.map(request);

    // then
    assertEquals(request.getCourses().size(), response.getCourses().size());
    assertEquals(request.getCourses().get(0).getId(), response.getCourses().get(0).getId());
    assertEquals(request.getCourses().get(0).getName(), response.getCourses().get(0).getName());
    assertEquals(
        request.getCourses().get(0).getCreateAt(), response.getCourses().get(0).getCreateAt());
  }
}
