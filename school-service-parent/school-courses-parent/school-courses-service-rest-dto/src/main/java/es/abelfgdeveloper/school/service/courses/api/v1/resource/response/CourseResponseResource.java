package es.abelfgdeveloper.school.service.courses.api.v1.resource.response;

import java.time.LocalDateTime;
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
public class CourseResponseResource {

  private String id;
  private String name;
  private LocalDateTime createAt;
}
