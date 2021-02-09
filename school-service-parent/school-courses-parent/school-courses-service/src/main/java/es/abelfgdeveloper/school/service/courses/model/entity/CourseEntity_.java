package es.abelfgdeveloper.school.service.courses.model.entity;

import java.time.LocalDateTime;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(CourseEntity.class)
public class CourseEntity_ {

  public static volatile SingularAttribute<CourseEntity, String> id;
  public static volatile SingularAttribute<CourseEntity, String> name;
  public static volatile SingularAttribute<CourseEntity, LocalDateTime> createAt;
}
