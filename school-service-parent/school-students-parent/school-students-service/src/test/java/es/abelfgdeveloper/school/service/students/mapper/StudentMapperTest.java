package es.abelfgdeveloper.school.service.students.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import es.abelfgdeveloper.school.service.students.domain.Student;
import es.abelfgdeveloper.school.service.students.model.entity.StudentEntity;
import es.abelfgdeveloper.school.service.students.objectmother.StudentObjectMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class StudentMapperTest {

  private StudentMapper studentMapper;

  @BeforeEach
  void setUp() {
    studentMapper = new StudentMapper();
  }

  @Test
  void test_map_studentToStudentEntity() {
    // given
    Student request = StudentObjectMother.getStudent();

    // when
    StudentEntity response = studentMapper.map(request);

    // then
    assertEquals(request.getId(), response.getId());
    assertEquals(request.getFirstName(), response.getFirstName());
    assertEquals(request.getLastName(), response.getLastName());
    assertEquals(request.getEmail(), response.getEmail());
    assertEquals(request.getCreateAt(), response.getCreateAt());
  }

  @Test
  void test_map_studentEntityToStudent() {
    // given
    StudentEntity request = StudentObjectMother.getStudentEntity();

    // when
    Student response = studentMapper.map(request);

    // then
    assertEquals(request.getId(), response.getId());
    assertEquals(request.getFirstName(), response.getFirstName());
    assertEquals(request.getLastName(), response.getLastName());
    assertEquals(request.getEmail(), response.getEmail());
    assertEquals(request.getCreateAt(), response.getCreateAt());
  }
}
