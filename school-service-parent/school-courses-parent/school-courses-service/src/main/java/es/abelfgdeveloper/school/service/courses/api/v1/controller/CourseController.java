package es.abelfgdeveloper.school.service.courses.api.v1.controller;

import es.abelfgdeveloper.common.api.v1.resource.mapper.PaginationMapper;
import es.abelfgdeveloper.school.service.courses.api.v1.CourseApi;
import es.abelfgdeveloper.school.service.courses.api.v1.mapper.CourseMapper;
import es.abelfgdeveloper.school.service.courses.api.v1.resource.request.CreateCourseRequestResource;
import es.abelfgdeveloper.school.service.courses.api.v1.resource.request.UpdateCourseRequestResource;
import es.abelfgdeveloper.school.service.courses.api.v1.resource.response.CoursePaginatedResponseResource;
import es.abelfgdeveloper.school.service.courses.api.v1.resource.response.CourseResponseResource;
import es.abelfgdeveloper.school.service.courses.api.v1.validator.CoursePaginatedResponseResourceValidator;
import es.abelfgdeveloper.school.service.courses.api.v1.validator.CourseResponseResourceValidator;
import es.abelfgdeveloper.school.service.courses.api.v1.validator.CreateCourseRequestResourceValidator;
import es.abelfgdeveloper.school.service.courses.api.v1.validator.UpdateCourseRequestResourceValidator;
import es.abelfgdeveloper.school.service.courses.usecase.v1.CreateCourseUseCase;
import es.abelfgdeveloper.school.service.courses.usecase.v1.DeleteCourseByIdUseCase;
import es.abelfgdeveloper.school.service.courses.usecase.v1.FindCourseByIdUseCase;
import es.abelfgdeveloper.school.service.courses.usecase.v1.FindCoursesPaginatedUseCase;
import es.abelfgdeveloper.school.service.courses.usecase.v1.UpdateCourseUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CourseController implements CourseApi {

  private final CreateCourseUseCase createCourseUseCase;
  private final UpdateCourseUseCase updateCourseUseCase;
  private final DeleteCourseByIdUseCase deleteCourseByIdUseCase;
  private final FindCourseByIdUseCase findCourseByIdUseCase;
  private final FindCoursesPaginatedUseCase findCoursesPaginatedUseCase;

  private final CreateCourseRequestResourceValidator createCourseRequestResourceValidator;
  private final UpdateCourseRequestResourceValidator updateCourseRequestResourceValidator;
  private final CourseResponseResourceValidator courseResponseResourceValidator;
  private final CoursePaginatedResponseResourceValidator coursePaginatedResponseResourceValidator;

  private final CourseMapper courseMapper;
  private final PaginationMapper paginationMapper;

  @Override
  public CourseResponseResource create(CreateCourseRequestResource course) {
    createCourseRequestResourceValidator.validate(course);
    CourseResponseResource response =
        courseMapper.map(createCourseUseCase.execute(courseMapper.map(course)));
    courseResponseResourceValidator.validate(response);
    return response;
  }

  @Override
  public CourseResponseResource update(String id, UpdateCourseRequestResource course) {
    updateCourseRequestResourceValidator.validate(course);
    CourseResponseResource response =
        courseMapper.map(updateCourseUseCase.execute(courseMapper.map(id, course)));
    courseResponseResourceValidator.validate(response);
    return response;
  }

  @Override
  public void deleteById(String id) {
    deleteCourseByIdUseCase.execute(id);
  }

  @Override
  public CourseResponseResource findById(String id) {
    CourseResponseResource response = courseMapper.map(findCourseByIdUseCase.execute(id));
    courseResponseResourceValidator.validate(response);
    return response;
  }

  @Override
  public CoursePaginatedResponseResource findPaginated(Integer page, Integer size, String query) {
    CoursePaginatedResponseResource response =
        courseMapper.map(
            findCoursesPaginatedUseCase.execute(paginationMapper.map(page, size), query));
    coursePaginatedResponseResourceValidator.validate(response);
    return response;
  }
}
