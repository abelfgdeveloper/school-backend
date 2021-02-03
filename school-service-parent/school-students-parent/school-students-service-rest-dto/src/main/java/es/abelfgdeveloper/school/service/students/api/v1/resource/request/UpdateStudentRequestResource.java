package es.abelfgdeveloper.school.service.students.api.v1.resource.request;

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
public class UpdateStudentRequestResource {

  private String firstName;
  private String lastName;
}
