package es.abelfgdeveloper.school.service.mapper;

import es.abelfgdeveloper.school.service.domain.Student;
import es.abelfgdeveloper.school.service.model.entity.StudentEntity;
import org.springframework.stereotype.Component;

@Component("studentDomainMapper")
public class StudentMapper {

  public StudentEntity map(Student student) {
    return StudentEntity.builder()
        .id(student.getId())
        .firstName(student.getFirstName())
        .lastName(student.getLastName())
        .email(student.getEmail())
        .createAt(student.getCreateAt())
        .build();
  }

  public Student map(StudentEntity student) {
    return Student.builder()
        .id(student.getId())
        .firstName(student.getFirstName())
        .lastName(student.getLastName())
        .email(student.getEmail())
        .createAt(student.getCreateAt())
        .build();
  }
}
