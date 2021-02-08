package es.abelfgdeveloper.school.service.students.cucumber.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.fasterxml.jackson.databind.ObjectMapper;
import es.abelfgdeveloper.common.api.v1.resource.response.ErrorResponseResource;
import es.abelfgdeveloper.school.service.students.cucumber.common.CommonCucumberContext;
import es.abelfgdeveloper.school.service.students.cucumber.common.CucumberConverter;
import es.abelfgdeveloper.school.service.students.cucumber.common.MakeRestCall;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;

public class CommonSteps {

  @LocalServerPort private int port;
  @Autowired private CommonCucumberContext commonCucumberContext;
  @Autowired List<CucumberConverter> converters;
  @Autowired private ObjectMapper objectMapper;

  @Given("The endpoint {string}")
  public void the_endpoint(String endpoint) throws Exception {
    System.out.println();
    commonCucumberContext.setPort(port);
    commonCucumberContext.setEndpoint(endpoint);
    System.out.println();
  }

  @And("The resource {}")
  public void the_resource(String resource) throws Exception {
    System.out.println();
    commonCucumberContext.setResource(resource);
    System.out.println();
  }

  @And("The input data")
  public void the_input_data(DataTable dataTable) throws Exception {
    System.out.println();
    commonCucumberContext.setRequestBody(
        objectMapper.writeValueAsString(getConverter().convert(dataTable.asMaps().get(0))));
    System.out.println();
  }

  @When("Make {string} call")
  public void make_call_to_the_endpoint(String method) throws Exception {
    System.out.println();
    commonCucumberContext.setMethod(method);
    new MakeRestCall(commonCucumberContext).call();
    System.out.println();
  }

  @Then("I verify the {int} response code")
  public void i_verify_the_response_code(Integer expectedStatusCode) {
    System.out.println();
    assertEquals(expectedStatusCode, commonCucumberContext.getResponseStatus());
    System.out.println();
  }

  @And("If response code not {int} i verify the error code {string}")
  public void i_verify_the_error_response_message(
      Integer expectedResponseCode, String errorResponseMessage) throws Exception {
    System.out.println();
    if (expectedResponseCode.intValue() != commonCucumberContext.getResponseStatus()) {
      ErrorResponseResource response =
          objectMapper.readValue(
              commonCucumberContext.getResponseBody(), ErrorResponseResource.class);
      assertEquals(errorResponseMessage, response.getCode());
    }
    System.out.println();
  }

  private CucumberConverter getConverter() {
    CucumberConverter response = null;
    for (CucumberConverter converter : converters) {
      if (converter.getName().equals(commonCucumberContext.getResource())) {
        response = converter;
      }
    }
    return response;
  }
}
