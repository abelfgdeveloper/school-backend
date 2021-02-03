package es.abelfgdeveloper.school.service.students.model.entity;

import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
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
@Entity
@Table(name = "students")
public class StudentEntity {

  @Id @EqualsAndHashCode.Include private String id;

  @Column(name = "first_name", nullable = false)
  private String firstName;

  @Column(name = "last_name", nullable = false)
  private String lastName;

  @Column(name = "email", nullable = false, unique = true)
  private String email;

  @Column(name = "create_at")
  private LocalDateTime createAt;

  @PrePersist
  public void prePersist() {
    this.id = UUID.randomUUID().toString();
    this.createAt = LocalDateTime.now();
  }
}
