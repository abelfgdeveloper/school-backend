package es.abelfgdeveloper.school.service.courses.api.v1.resource.response;

import es.abelfgdeveloper.common.api.v1.resource.response.PaginationResponseResource;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CoursePaginatedResponseResource {

  private PaginationResponseResource pagination;
  private List<CourseResponseResource> courses;
}
