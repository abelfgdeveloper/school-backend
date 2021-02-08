package es.abelfgdeveloper.school.service.students.api.v1.mapper;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import es.abelfgdeveloper.common.api.v1.resource.mapper.PaginationMapper;
import es.abelfgdeveloper.school.service.students.api.v1.resource.request.CreateStudentRequestResource;
import es.abelfgdeveloper.school.service.students.api.v1.resource.request.UpdateStudentRequestResource;
import es.abelfgdeveloper.school.service.students.api.v1.resource.response.StudentPaginatedResponseResource;
import es.abelfgdeveloper.school.service.students.api.v1.resource.response.StudentResponseResource;
import es.abelfgdeveloper.school.service.students.domain.Student;
import es.abelfgdeveloper.school.service.students.domain.StudentPaginated;
import es.abelfgdeveloper.school.service.students.objectmother.StudentObjectMother;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class StudentMapperTest {

  @Mock private PaginationMapper paginationMapper;

  private StudentMapper studentMapper;

  @BeforeEach
  void setUp() {
    studentMapper = new StudentMapper(paginationMapper);
  }

  @Test
  void test_map_createStudentRequestResourceToStudent() {
    // given
    CreateStudentRequestResource request = StudentObjectMother.getCreateStudentRequestResourceV1();

    // when
    Student response = studentMapper.map(request);

    // then
    assertNull(response.getId());
    assertEquals(request.getFirstName(), response.getFirstName());
    assertEquals(request.getLastName(), response.getLastName());
    assertEquals(request.getEmail(), response.getEmail());
    assertNull(response.getCreateAt());
  }

  @Test
  void test_map_updateStudentRequestResourceToStudent() {
    // given
    UpdateStudentRequestResource request = StudentObjectMother.getUpdateStudentRequestResourceV1();
    String id = UUID.randomUUID().toString();
    // when
    Student response = studentMapper.map(id, request);

    // then
    assertEquals(id, response.getId());
    assertEquals(request.getFirstName(), response.getFirstName());
    assertEquals(request.getLastName(), response.getLastName());
    assertNull(response.getEmail());
    assertNull(response.getCreateAt());
  }

  @Test
  void test_map_studentToStudentResponseResource() {
    // given
    Student request = StudentObjectMother.getStudent();
    // when
    StudentResponseResource response = studentMapper.map(request);

    // then
    assertEquals(request.getId(), response.getId());
    assertEquals(request.getFirstName(), response.getFirstName());
    assertEquals(request.getLastName(), response.getLastName());
    assertEquals(request.getEmail(), response.getEmail());
    assertEquals(request.getCreateAt(), response.getCreateAt());
  }

  @Test
  void test_map_studentPaginatedToStudentPaginatedResponseResource() {
    // given
    StudentPaginated request = StudentObjectMother.getStudentPaginated();
    // when
    StudentPaginatedResponseResource response = studentMapper.map(request);

    // then
    assertEquals(request.getStudents().size(), response.getStudents().size());
    assertEquals(request.getStudents().get(0).getId(), response.getStudents().get(0).getId());
    assertEquals(
        request.getStudents().get(0).getFirstName(), response.getStudents().get(0).getFirstName());
    assertEquals(
        request.getStudents().get(0).getLastName(), response.getStudents().get(0).getLastName());
    assertEquals(request.getStudents().get(0).getEmail(), response.getStudents().get(0).getEmail());
    assertEquals(
        request.getStudents().get(0).getCreateAt(), response.getStudents().get(0).getCreateAt());
  }
}
