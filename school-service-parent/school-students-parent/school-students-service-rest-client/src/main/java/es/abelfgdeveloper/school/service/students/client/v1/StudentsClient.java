package es.abelfgdeveloper.school.service.students.client.v1;

import es.abelfgdeveloper.school.service.students.api.v1.resource.response.StudentResponseResource;

public interface StudentsClient {

  StudentResponseResource findById(String id);
}
