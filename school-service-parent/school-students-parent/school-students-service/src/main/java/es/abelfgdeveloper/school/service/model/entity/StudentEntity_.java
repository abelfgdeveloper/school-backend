package es.abelfgdeveloper.school.service.model.entity;

import java.time.LocalDateTime;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(StudentEntity.class)
public class StudentEntity_ {

  public static volatile SingularAttribute<StudentEntity, String> id;
  public static volatile SingularAttribute<StudentEntity, String> firstName;
  public static volatile SingularAttribute<StudentEntity, String> lastName;
  public static volatile SingularAttribute<StudentEntity, String> email;
  public static volatile SingularAttribute<StudentEntity, LocalDateTime> createAt;
}
