package es.abelfgdeveloper.school.service.courses.api.v1.mapper;

import es.abelfgdeveloper.common.api.v1.resource.mapper.PaginationMapper;
import es.abelfgdeveloper.school.service.courses.api.v1.resource.request.CreateCourseRequestResource;
import es.abelfgdeveloper.school.service.courses.api.v1.resource.request.UpdateCourseRequestResource;
import es.abelfgdeveloper.school.service.courses.api.v1.resource.response.CoursePaginatedResponseResource;
import es.abelfgdeveloper.school.service.courses.api.v1.resource.response.CourseResponseResource;
import es.abelfgdeveloper.school.service.courses.domain.Course;
import es.abelfgdeveloper.school.service.courses.domain.CoursePaginated;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component("courseResourceMapperV1")
public class CourseMapper {

  private final PaginationMapper paginationMapper;

  public Course map(CreateCourseRequestResource course) {
    return Course.builder().name(course.getName()).build();
  }

  public Course map(String id, UpdateCourseRequestResource course) {
    return Course.builder().id(id).name(course.getName()).build();
  }

  public CourseResponseResource map(Course course) {
    return CourseResponseResource.builder()
        .id(course.getId())
        .name(course.getName())
        .createAt(course.getCreateAt())
        .build();
  }

  public CoursePaginatedResponseResource map(CoursePaginated student) {
    return CoursePaginatedResponseResource.builder()
        .pagination(paginationMapper.map(student.getPagination()))
        .courses(student.getCourses().stream().map(this::map).collect(Collectors.toList()))
        .build();
  }
}
