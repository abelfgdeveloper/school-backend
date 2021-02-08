package es.abelfgdeveloper.school.service.students.cucumber.common;

import java.util.Map;

public interface CucumberConverter {

  String getName();

  Object convert(Map<String, String> data);
}
