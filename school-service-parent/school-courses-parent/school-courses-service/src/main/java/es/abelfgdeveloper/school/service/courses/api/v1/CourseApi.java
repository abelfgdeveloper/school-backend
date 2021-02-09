package es.abelfgdeveloper.school.service.courses.api.v1;

import es.abelfgdeveloper.school.service.courses.api.v1.resource.request.CreateCourseRequestResource;
import es.abelfgdeveloper.school.service.courses.api.v1.resource.request.UpdateCourseRequestResource;
import es.abelfgdeveloper.school.service.courses.api.v1.resource.response.CoursePaginatedResponseResource;
import es.abelfgdeveloper.school.service.courses.api.v1.resource.response.CourseResponseResource;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

public interface CourseApi {

  @PostMapping("/courses/v1")
  @ResponseStatus(HttpStatus.CREATED)
  CourseResponseResource create(@RequestBody CreateCourseRequestResource student);

  @PutMapping("/courses/v1/{courseId}")
  @ResponseStatus(HttpStatus.OK)
  CourseResponseResource update(
      @PathVariable("courseId") String id, @RequestBody UpdateCourseRequestResource student);

  @DeleteMapping("/courses/v1/{courseId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  void deleteById(@PathVariable("courseId") String id);

  @GetMapping("/courses/v1/{courseId}")
  @ResponseStatus(HttpStatus.OK)
  CourseResponseResource findById(@PathVariable("courseId") String id);

  @GetMapping("/courses/v1")
  @ResponseStatus(HttpStatus.OK)
  CoursePaginatedResponseResource findPaginated(
      @RequestParam(name = "page", required = false) Integer page,
      @RequestParam(name = "size", required = false) Integer size,
      @RequestParam(name = "query", required = false) String query);

  @PutMapping("/courses/v1/{courseId}/students/{studentId}")
  @ResponseStatus(HttpStatus.OK)
  public CourseResponseResource addStudent(
      @PathVariable("courseId") String courseId, @PathVariable("studentId") String studentId);

  @DeleteMapping("/courses/v1/{courseId}/students/{studentId}")
  @ResponseStatus(HttpStatus.OK)
  public CourseResponseResource removeStudent(
      @PathVariable("courseId") String courseId, @PathVariable("studentId") String studentId);

  @GetMapping("/courses/v1/students/{studentId}")
  @ResponseStatus(HttpStatus.OK)
  public List<CourseResponseResource> findCoursesByStudentId(
      @PathVariable("studentId") String studentId);
}
