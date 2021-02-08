package es.abelfgdeveloper.school.service.students.api.v1.resource.response;

import es.abelfgdeveloper.common.api.BaseResource;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class StudentResponseResource extends BaseResource {

  private String firstName;
  private String lastName;
  private String email;
  private LocalDateTime createAt;
}
