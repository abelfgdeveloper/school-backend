package es.abelfgdeveloper.school.service.students.v1.converter;

import es.abelfgdeveloper.school.service.students.api.v1.resource.request.CreateStudentRequestResource;
import es.abelfgdeveloper.school.service.students.cucumber.common.CucumberConverter;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class CreateStudentRequestResourceConverter implements CucumberConverter {

  @Override
  public String getName() {
    return "CreateStudentRequestResourceV1";
  }

  @Override
  public Object convert(Map<String, String> data) {
    return CreateStudentRequestResource.builder()
        .firstName(data.get("firstName"))
        .lastName(data.get("lastName"))
        .email(data.get("email"))
        .build();
  }
}
