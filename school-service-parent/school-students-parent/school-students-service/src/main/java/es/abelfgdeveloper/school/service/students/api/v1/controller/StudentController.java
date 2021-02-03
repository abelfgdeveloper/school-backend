package es.abelfgdeveloper.school.service.students.api.v1.controller;

import es.abelfgdeveloper.common.api.v1.resource.mapper.PaginationMapper;
import es.abelfgdeveloper.school.service.students.api.v1.StudentApi;
import es.abelfgdeveloper.school.service.students.api.v1.mapper.StudentMapper;
import es.abelfgdeveloper.school.service.students.api.v1.resource.request.CreateStudentRequestResource;
import es.abelfgdeveloper.school.service.students.api.v1.resource.request.UpdateStudentRequestResource;
import es.abelfgdeveloper.school.service.students.api.v1.resource.response.StudentPaginatedResponseResource;
import es.abelfgdeveloper.school.service.students.api.v1.resource.response.StudentResponseResource;
import es.abelfgdeveloper.school.service.students.api.v1.validator.CreateStudentRequestResourceValidator;
import es.abelfgdeveloper.school.service.students.api.v1.validator.StudentPaginatedResponseResourceValidator;
import es.abelfgdeveloper.school.service.students.api.v1.validator.StudentResponseResourceValidator;
import es.abelfgdeveloper.school.service.students.api.v1.validator.UpdateStudentRequestResourceValidator;
import es.abelfgdeveloper.school.service.students.usecase.v1.CreateStudentUseCase;
import es.abelfgdeveloper.school.service.students.usecase.v1.DeleteStudentByIdUseCase;
import es.abelfgdeveloper.school.service.students.usecase.v1.FindStudentByIdUseCase;
import es.abelfgdeveloper.school.service.students.usecase.v1.FindStudentsPaginatedUseCase;
import es.abelfgdeveloper.school.service.students.usecase.v1.UpdateStudentUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class StudentController implements StudentApi {

  private final CreateStudentUseCase createStudentUseCase;
  private final UpdateStudentUseCase updateStudentUseCase;
  private final DeleteStudentByIdUseCase deleteStudentByIdUseCase;
  private final FindStudentByIdUseCase findStudentByIdUseCase;
  private final FindStudentsPaginatedUseCase findStudentsPaginatedUseCase;

  private final CreateStudentRequestResourceValidator createStudentRequestResourceValidator;
  private final UpdateStudentRequestResourceValidator updateStudentRequestResourceValidator;
  private final StudentResponseResourceValidator studentResponseResourceValidator;
  private final StudentPaginatedResponseResourceValidator studentPaginatedResponseResourceValidator;

  private final StudentMapper studentMapper;
  private final PaginationMapper paginationMapper;

  @Override
  public StudentResponseResource create(CreateStudentRequestResource student) {
    createStudentRequestResourceValidator.validate(student);
    StudentResponseResource response =
        studentMapper.map(createStudentUseCase.execute(studentMapper.map(student)));
    studentResponseResourceValidator.validate(response);
    return response;
  }

  @Override
  public StudentResponseResource update(String id, UpdateStudentRequestResource student) {
    updateStudentRequestResourceValidator.validate(student);
    StudentResponseResource response =
        studentMapper.map(updateStudentUseCase.execute(studentMapper.map(id, student)));
    studentResponseResourceValidator.validate(response);
    return response;
  }

  @Override
  public void deleteById(String id) {
    deleteStudentByIdUseCase.execute(id);
  }

  @Override
  public StudentResponseResource findById(String id) {
    StudentResponseResource response = studentMapper.map(findStudentByIdUseCase.execute(id));
    studentResponseResourceValidator.validate(response);
    return response;
  }

  @Override
  public StudentPaginatedResponseResource findPaginated(Integer page, Integer size, String query) {
    StudentPaginatedResponseResource response =
        studentMapper.map(
            findStudentsPaginatedUseCase.execute(paginationMapper.map(page, size), query));
    studentPaginatedResponseResourceValidator.validate(response);
    return response;
  }
}
