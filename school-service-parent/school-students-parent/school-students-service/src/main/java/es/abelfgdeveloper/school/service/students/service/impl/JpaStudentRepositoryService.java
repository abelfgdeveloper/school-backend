package es.abelfgdeveloper.school.service.students.service.impl;

import es.abelfgdeveloper.common.domain.PaginationIn;
import es.abelfgdeveloper.common.exception.client.NotFoundException;
import es.abelfgdeveloper.common.jpa.mapper.PaginationMapper;
import es.abelfgdeveloper.school.service.students.domain.Student;
import es.abelfgdeveloper.school.service.students.domain.StudentPaginated;
import es.abelfgdeveloper.school.service.students.mapper.StudentMapper;
import es.abelfgdeveloper.school.service.students.model.entity.StudentEntity;
import es.abelfgdeveloper.school.service.students.model.repository.StudentSpringDataJpaRepository;
import es.abelfgdeveloper.school.service.students.model.repository.specification.StudentSpecification;
import es.abelfgdeveloper.school.service.students.service.StudentRepositoryService;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@RequiredArgsConstructor
@Service
public class JpaStudentRepositoryService implements StudentRepositoryService {

  private final StudentSpringDataJpaRepository studentRepository;
  private final StudentSpecification studentSpecification;
  private final StudentMapper studentMapper;
  private final PaginationMapper paginationMapper;

  @Override
  public Student save(Student student) {
    StudentEntity studentInDataBase = null;
    if (student.getId() == null) {
      studentInDataBase = create(student);
    } else {
      studentInDataBase = update(student);
    }
    return studentMapper.map(studentInDataBase);
  }

  @Override
  public void deleteById(String id) {
    studentRepository.delete(find(id));
  }

  @Override
  public Student findById(String id) {
    return studentMapper.map(find(id));
  }

  @Override
  public StudentPaginated findAll(PaginationIn pagination, String query) {
    PageRequest page = PageRequest.of(pagination.getPage(), pagination.getSize());
    Page<StudentEntity> studentsInDataBase = null;
    if (StringUtils.isEmpty(query)) {
      studentsInDataBase = studentRepository.findAll(page);
    } else {
      studentsInDataBase =
          studentRepository.findAll(
              studentSpecification.firstNameOrLastNameOrEmailLike(query), page);
    }
    return StudentPaginated.builder()
        .pagination(paginationMapper.map(studentsInDataBase))
        .students(
            studentsInDataBase
                .getContent()
                .stream()
                .map(studentMapper::map)
                .collect(Collectors.toList()))
        .build();
  }

  private StudentEntity create(Student student) {
    return studentRepository.save(studentMapper.map(student));
  }

  private StudentEntity update(Student student) {
    StudentEntity studentInDataBase = find(student.getId());
    studentInDataBase.setFirstName(student.getFirstName());
    studentInDataBase.setLastName(student.getLastName());
    return studentRepository.save(studentInDataBase);
  }

  private StudentEntity find(String id) {
    Optional<StudentEntity> studentInDataBase = studentRepository.findById(id);
    if (studentInDataBase.isPresent()) {
      return studentInDataBase.get();
    } else {
      throw new NotFoundException("El estudiante con ID: " + id + " no existe");
    }
  }
}
