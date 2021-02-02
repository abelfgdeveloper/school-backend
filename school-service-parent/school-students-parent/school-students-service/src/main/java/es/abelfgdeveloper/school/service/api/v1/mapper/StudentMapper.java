package es.abelfgdeveloper.school.service.api.v1.mapper;

import es.abelfgdeveloper.common.api.v1.resource.mapper.PaginationMapper;
import es.abelfgdeveloper.school.service.api.v1.resource.request.CreateStudentRequestResource;
import es.abelfgdeveloper.school.service.api.v1.resource.request.UpdateStudentRequestResource;
import es.abelfgdeveloper.school.service.api.v1.resource.response.StudentPaginatedResponseResource;
import es.abelfgdeveloper.school.service.api.v1.resource.response.StudentResponseResource;
import es.abelfgdeveloper.school.service.domain.Student;
import es.abelfgdeveloper.school.service.domain.StudentPaginated;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component("studentResourceMapperV1")
public class StudentMapper {

  private final PaginationMapper paginationMapper;

  public Student map(CreateStudentRequestResource student) {
    return Student.builder()
        .firstName(student.getFirstName())
        .lastName(student.getLastName())
        .email(student.getEmail())
        .build();
  }

  public Student map(String id, UpdateStudentRequestResource student) {
    return Student.builder()
        .id(id)
        .firstName(student.getFirstName())
        .lastName(student.getLastName())
        .build();
  }

  public StudentResponseResource map(Student student) {
    return StudentResponseResource.builder()
        .id(student.getId())
        .firstName(student.getFirstName())
        .lastName(student.getLastName())
        .email(student.getEmail())
        .createAt(student.getCreateAt())
        .build();
  }

  public StudentPaginatedResponseResource map(StudentPaginated student) {
    return StudentPaginatedResponseResource.builder()
        .pagination(paginationMapper.map(student.getPagination()))
        .students(student.getStudents().stream().map(this::map).collect(Collectors.toList()))
        .build();
  }
}
