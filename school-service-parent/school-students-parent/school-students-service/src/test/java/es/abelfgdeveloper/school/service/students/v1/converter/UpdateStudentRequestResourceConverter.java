package es.abelfgdeveloper.school.service.students.v1.converter;

import es.abelfgdeveloper.school.service.students.api.v1.resource.request.UpdateStudentRequestResource;
import es.abelfgdeveloper.school.service.students.cucumber.common.CucumberConverter;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class UpdateStudentRequestResourceConverter implements CucumberConverter {

  @Override
  public String getName() {
    return "UpdateStudentRequestResourceV1";
  }

  @Override
  public Object convert(Map<String, String> data) {
    return UpdateStudentRequestResource.builder()
        .firstName(data.get("firstName"))
        .lastName(data.get("lastName"))
        .build();
  }
}
