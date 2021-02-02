package es.abelfgdeveloper.school.service.api.v1;

import es.abelfgdeveloper.school.service.api.v1.resource.request.CreateStudentRequestResource;
import es.abelfgdeveloper.school.service.api.v1.resource.request.UpdateStudentRequestResource;
import es.abelfgdeveloper.school.service.api.v1.resource.response.StudentPaginatedResponseResource;
import es.abelfgdeveloper.school.service.api.v1.resource.response.StudentResponseResource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

public interface StudentApi {

  @PostMapping("/students/v1")
  @ResponseStatus(HttpStatus.CREATED)
  StudentResponseResource create(@RequestBody CreateStudentRequestResource student);

  @PutMapping("/students/v1/{studentId}")
  @ResponseStatus(HttpStatus.OK)
  StudentResponseResource update(
      @PathVariable("studentId") String id, @RequestBody UpdateStudentRequestResource student);

  @DeleteMapping("/students/v1/{studentId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  void deleteById(@PathVariable("studentId") String id);

  @GetMapping("/students/v1/{studentId}")
  @ResponseStatus(HttpStatus.OK)
  StudentResponseResource findById(@PathVariable("studentId") String id);

  @GetMapping("/students/v1")
  @ResponseStatus(HttpStatus.OK)
  StudentPaginatedResponseResource findPaginated(
      @RequestParam(name = "page", required = false) Integer page,
      @RequestParam(name = "size", required = false) Integer size,
      @RequestParam(name = "query", required = false) String query);
}
