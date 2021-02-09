package es.abelfgdeveloper.school.service.courses.domain;

import es.abelfgdeveloper.common.domain.PaginationOut;
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
public class CoursePaginated {

  private PaginationOut pagination;
  private List<Course> courses;
}
