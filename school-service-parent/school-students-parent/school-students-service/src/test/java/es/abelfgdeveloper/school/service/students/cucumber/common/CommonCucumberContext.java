package es.abelfgdeveloper.school.service.students.cucumber.common;

import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class CommonCucumberContext {

  private int port;
  private String endpoint;
  private String resource;
  private String requestBody;
  private String method;
  private int responseStatus;
  private String responseBody;
  private Map<String, String> queryParams;

  public String getFullEndpoint() {
    return "http://localhost:" + port + this.endpoint;
  }
}
