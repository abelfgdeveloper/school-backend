package es.abelfgdeveloper.school.service.students.objectmother;

import es.abelfgdeveloper.common.api.v1.resource.response.PaginationResponseResource;
import es.abelfgdeveloper.common.domain.PaginationOut;
import es.abelfgdeveloper.school.service.students.api.v1.resource.request.CreateStudentRequestResource;
import es.abelfgdeveloper.school.service.students.api.v1.resource.request.UpdateStudentRequestResource;
import es.abelfgdeveloper.school.service.students.api.v1.resource.response.StudentPaginatedResponseResource;
import es.abelfgdeveloper.school.service.students.api.v1.resource.response.StudentResponseResource;
import es.abelfgdeveloper.school.service.students.domain.Student;
import es.abelfgdeveloper.school.service.students.domain.StudentPaginated;
import es.abelfgdeveloper.school.service.students.model.entity.StudentEntity;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StudentObjectMother {

  public static StudentEntity getStudentEntity() {
    return StudentEntity.builder()
        .id(UUID.randomUUID().toString())
        .firstName("Samsagaz")
        .lastName("Gamyi")
        .email("samsagazgamyi@email.com")
        .createAt(LocalDateTime.now())
        .build();
  }

  public static Student getStudent() {
    return Student.builder()
        .id(UUID.randomUUID().toString())
        .firstName("Gandalf")
        .lastName("El gris")
        .email("gandalfelgris@email.com")
        .createAt(LocalDateTime.now())
        .build();
  }

  public static StudentPaginated getStudentPaginated() {
    return StudentPaginated.builder()
        .pagination(PaginationOut.builder().build())
        .students(Arrays.asList(getStudent()))
        .build();
  }

  public static CreateStudentRequestResource getCreateStudentRequestResourceV1() {
    return CreateStudentRequestResource.builder()
        .firstName("Frodo")
        .lastName("Bolson")
        .email("frodo@mail.com")
        .build();
  }

  public static UpdateStudentRequestResource getUpdateStudentRequestResourceV1() {
    return UpdateStudentRequestResource.builder().firstName("Bilbo").lastName("Baggins").build();
  }

  public static StudentResponseResource getStudentResponseResourceV1() {
    return StudentResponseResource.builder()
        .id(UUID.randomUUID().toString())
        .firstName("Gollum")
        .lastName("Smeagol")
        .email("gollumsmeagol@email.com")
        .createAt(LocalDateTime.now())
        .build();
  }

  public static StudentPaginatedResponseResource getStudentPaginatedResponseResourceV1() {
    return StudentPaginatedResponseResource.builder()
        .pagination(PaginationResponseResource.builder().build())
        .students(Arrays.asList(getStudentResponseResourceV1()))
        .build();
  }
}
