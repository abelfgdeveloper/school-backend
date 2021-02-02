package es.abelfgdeveloper.school.service.domain;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Student {

  @EqualsAndHashCode.Include private String id;
  private String firstName;
  private String lastName;
  private String email;
  private LocalDateTime createAt;
}
