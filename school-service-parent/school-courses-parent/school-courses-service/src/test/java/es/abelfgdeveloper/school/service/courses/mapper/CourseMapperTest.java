package es.abelfgdeveloper.school.service.courses.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import es.abelfgdeveloper.school.service.courses.domain.Course;
import es.abelfgdeveloper.school.service.courses.model.entity.CourseEntity;
import es.abelfgdeveloper.school.service.courses.objectmother.CourseObjectMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CourseMapperTest {

  private CourseMapper courseMapper;

  @BeforeEach
  void setUp() {
    courseMapper = new CourseMapper();
  }

  @Test
  void test_map_courseToCourseEntity() {
    // given
    Course request = CourseObjectMother.getCourse();

    // when
    CourseEntity response = courseMapper.map(request);

    // then
    assertEquals(request.getId(), response.getId());
    assertEquals(request.getName(), response.getName());
    assertEquals(request.getCreateAt(), response.getCreateAt());
  }

  @Test
  void test_map_courseEntityToCourse() {
    // given
    CourseEntity request = CourseObjectMother.getCourseEntity();

    // when
    Course response = courseMapper.map(request);

    // then
    assertEquals(request.getId(), response.getId());
    assertEquals(request.getName(), response.getName());
    assertEquals(request.getCreateAt(), response.getCreateAt());
  }
}
