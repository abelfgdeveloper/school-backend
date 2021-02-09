package es.abelfgdeveloper.school.service.courses.model.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "courses")
public class CourseEntity {

  @Id @EqualsAndHashCode.Include private String id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "create_at")
  private LocalDateTime createAt;

  @ElementCollection
  @CollectionTable(name = "courses_students", joinColumns = @JoinColumn(name = "course_id"))
  @Column(name = "student_id")
  private List<String> students;

  @PrePersist
  public void prePersist() {
    this.id = UUID.randomUUID().toString();
    this.createAt = LocalDateTime.now();
  }
}
