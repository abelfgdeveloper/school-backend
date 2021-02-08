package es.abelfgdeveloper.school.service.students.usecase.v1.impl;

import es.abelfgdeveloper.common.exception.client.BadRequestException;
import es.abelfgdeveloper.school.service.students.domain.Student;
import es.abelfgdeveloper.school.service.students.service.StudentRepositoryService;
import es.abelfgdeveloper.school.service.students.usecase.v1.CreateStudentUseCase;
import es.abelfgdeveloper.school.service.students.util.StudentErrorCodes;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CreateStudentUseCaseImpl implements CreateStudentUseCase {

  private final StudentRepositoryService studentRepositoryService;

  @Transactional
  @Override
  public Student execute(Student student) {
    Optional<Student> studentInDataBase = studentRepositoryService.findByEmail(student.getEmail());
    if (studentInDataBase.isPresent()) {
      throw new BadRequestException(StudentErrorCodes.EMAIL_EXIST);
    }
    return studentRepositoryService.save(student);
  }
}
