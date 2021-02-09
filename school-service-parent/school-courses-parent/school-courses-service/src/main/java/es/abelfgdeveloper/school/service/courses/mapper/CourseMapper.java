package es.abelfgdeveloper.school.service.courses.mapper;

import es.abelfgdeveloper.school.service.courses.domain.Course;
import es.abelfgdeveloper.school.service.courses.model.entity.CourseEntity;
import org.springframework.stereotype.Component;

@Component("courseDomainMapper")
public class CourseMapper {

  public CourseEntity map(Course course) {
    return CourseEntity.builder()
        .id(course.getId())
        .name(course.getName())
        .students(course.getStudents())
        .createAt(course.getCreateAt())
        .build();
  }

  public Course map(CourseEntity course) {
    return Course.builder()
        .id(course.getId())
        .name(course.getName())
        .createAt(course.getCreateAt())
        .students(course.getStudents())
        .build();
  }
}
