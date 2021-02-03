package es.abelfgdeveloper.school.service.students.mapper;

import es.abelfgdeveloper.school.service.students.domain.Student;
import es.abelfgdeveloper.school.service.students.model.entity.StudentEntity;
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
