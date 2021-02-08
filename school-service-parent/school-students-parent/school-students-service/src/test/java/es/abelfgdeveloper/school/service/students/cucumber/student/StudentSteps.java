package es.abelfgdeveloper.school.service.students.cucumber.student;

import com.fasterxml.jackson.databind.ObjectMapper;
import es.abelfgdeveloper.common.api.BaseResource;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;

public class StudentSteps {

  @Autowired private StudentCucumberContext studentCucumberContext;
  @Autowired private ObjectMapper objectMapper;

  @Then("If response code is {int} i save the resource")
  public void i_verify_the_response_code(Integer expectedStatusCode) throws Exception {
    if (expectedStatusCode.intValue() == studentCucumberContext.getResponseStatus()) {
      studentCucumberContext.getResponseBody();
      BaseResource resource =
          objectMapper.readValue(studentCucumberContext.getResponseBody(), BaseResource.class);
      studentCucumberContext.setBaseResource(resource);
    }
    System.out.println();
  }

  @And("PathVariable {string} is {string}")
  public void andTheIdOfTheInsertedStudent(String pathVariableKey, String pathVariableValue)
      throws Exception {
    String endpoint = studentCucumberContext.getEndpoint();
    if ("inserted resource".equals(pathVariableValue)) {
      endpoint =
          endpoint.replace(pathVariableKey, studentCucumberContext.getBaseResource().getId());
    } else {
      endpoint = endpoint.replace(pathVariableKey, pathVariableValue);
    }
    studentCucumberContext.setEndpoint(endpoint);
  }

  @And("QueryParam {string} is {string}")
  public void andRequestParamStringIsString(String queryParamKey, String queryParamValue)
      throws Exception {
    if (studentCucumberContext.getQueryParams() == null) {
      studentCucumberContext.setQueryParams(new HashMap<>());
    }
    studentCucumberContext.getQueryParams().put(queryParamKey, queryParamValue);
  }

  @And("QueryParam {string} is {int}")
  public void andRequestParamStringIsInt(String queryParamKey, Integer queryParamValue)
      throws Exception {
    if (studentCucumberContext.getQueryParams() == null) {
      studentCucumberContext.setQueryParams(new HashMap<>());
    }
    studentCucumberContext.getQueryParams().put(queryParamKey, queryParamValue.toString());
  }
}
