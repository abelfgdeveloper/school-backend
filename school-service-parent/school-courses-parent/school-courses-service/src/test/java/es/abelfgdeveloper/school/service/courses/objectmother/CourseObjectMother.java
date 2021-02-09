package es.abelfgdeveloper.school.service.courses.objectmother;

import es.abelfgdeveloper.common.api.v1.resource.response.PaginationResponseResource;
import es.abelfgdeveloper.common.domain.PaginationOut;
import es.abelfgdeveloper.school.service.courses.api.v1.resource.request.CreateCourseRequestResource;
import es.abelfgdeveloper.school.service.courses.api.v1.resource.request.UpdateCourseRequestResource;
import es.abelfgdeveloper.school.service.courses.api.v1.resource.response.CoursePaginatedResponseResource;
import es.abelfgdeveloper.school.service.courses.api.v1.resource.response.CourseResponseResource;
import es.abelfgdeveloper.school.service.courses.domain.Course;
import es.abelfgdeveloper.school.service.courses.domain.CoursePaginated;
import es.abelfgdeveloper.school.service.courses.model.entity.CourseEntity;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CourseObjectMother {

  public static CourseEntity getCourseEntity() {
    return CourseEntity.builder()
        .id(UUID.randomUUID().toString())
        .name("1º ESO")
        .createAt(LocalDateTime.now())
        .build();
  }

  public static Course getCourse() {
    return Course.builder()
        .id(UUID.randomUUID().toString())
        .name("2º ESO")
        .createAt(LocalDateTime.now())
        .build();
  }

  public static CoursePaginated getCoursePaginated() {
    return CoursePaginated.builder()
        .pagination(PaginationOut.builder().build())
        .courses(Arrays.asList(getCourse()))
        .build();
  }

  public static CreateCourseRequestResource getCreateCourseRequestResourceV1() {
    return CreateCourseRequestResource.builder().name("3º ESO").build();
  }

  public static UpdateCourseRequestResource getUpdateCourseRequestResourceV1() {
    return UpdateCourseRequestResource.builder().name("4º ESO").build();
  }

  public static CourseResponseResource getCourseResponseResourceV1() {
    return CourseResponseResource.builder()
        .id(UUID.randomUUID().toString())
        .name("5º ESO")
        .createAt(LocalDateTime.now())
        .build();
  }

  public static CoursePaginatedResponseResource getCoursePaginatedResponseResourceV1() {
    return CoursePaginatedResponseResource.builder()
        .pagination(PaginationResponseResource.builder().build())
        .courses(Arrays.asList(getCourseResponseResourceV1()))
        .build();
  }
}
