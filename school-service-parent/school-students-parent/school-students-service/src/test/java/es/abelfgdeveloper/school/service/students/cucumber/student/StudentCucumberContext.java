package es.abelfgdeveloper.school.service.students.cucumber.student;

import es.abelfgdeveloper.common.api.BaseResource;
import es.abelfgdeveloper.school.service.students.cucumber.common.CommonCucumberContext;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class StudentCucumberContext extends CommonCucumberContext {

  private BaseResource baseResource;
}
