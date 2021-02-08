package es.abelfgdeveloper.school.service.students.cucumber.common;

import java.util.Map;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class MakeRestCall {

  private final CommonCucumberContext commonCucumberContext;

  public MakeRestCall(CommonCucumberContext commonCucumberContext) {
    this.commonCucumberContext = commonCucumberContext;
  }

  public void call() throws Exception {
    switch (commonCucumberContext.getMethod()) {
      case "POST":
        makePostCall();
        break;
      case "PUT":
        makePutCall();
        break;
      case "GET":
        makeGetCall();
        break;
      case "DELETE":
        makeDeleteCall();
        break;
      default:
        throw new Exception(
            "The method '" + commonCucumberContext.getEndpoint() + "' is not supported");
    }
  }

  private void makePostCall() throws Exception {
    HttpHeaders requestHeaders = new HttpHeaders();
    requestHeaders.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<Object> requestHttpEntity =
        new HttpEntity<>(commonCucumberContext.getRequestBody(), requestHeaders);
    makeRestCall(requestHttpEntity);
  }

  private void makePutCall() throws Exception {
    HttpHeaders requestHeaders = new HttpHeaders();
    requestHeaders.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<Object> requestHttpEntity =
        new HttpEntity<>(commonCucumberContext.getRequestBody(), requestHeaders);
    makeRestCall(requestHttpEntity);
  }

  private void makeGetCall() throws Exception {
    HttpHeaders requestHeaders = new HttpHeaders();
    HttpEntity<Object> requestHttpEntity =
        new HttpEntity<>(commonCucumberContext.getRequestBody(), requestHeaders);
    makeRestCall(requestHttpEntity);
  }

  private void makeDeleteCall() throws Exception {
    HttpHeaders requestHeaders = new HttpHeaders();
    HttpEntity<Object> requestHttpEntity =
        new HttpEntity<>(commonCucumberContext.getRequestBody(), requestHeaders);
    makeRestCall(requestHttpEntity);
  }

  private void makeRestCall(HttpEntity<Object> requestHttpEntity) throws Exception {
    System.out.println();
    try {
      ResponseEntity<String> response =
          new RestTemplate()
              .exchange(
                  generateUri(),
                  HttpMethod.resolve(commonCucumberContext.getMethod()),
                  requestHttpEntity,
                  String.class);
      commonCucumberContext.setResponseStatus(response.getStatusCodeValue());
      commonCucumberContext.setResponseBody(response.getBody());
    } catch (HttpClientErrorException e) {
      commonCucumberContext.setResponseStatus(e.getStatusCode().value());
      commonCucumberContext.setResponseBody(e.getResponseBodyAsString());
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    }
    System.out.println();
  }

  private String generateUri() {
    UriComponentsBuilder uriBuilder =
        UriComponentsBuilder.fromHttpUrl(commonCucumberContext.getFullEndpoint());
    if (commonCucumberContext.getQueryParams() != null) {
      for (Map.Entry<String, String> entry : commonCucumberContext.getQueryParams().entrySet()) {
        uriBuilder.queryParam(entry.getKey(), entry.getValue());
      }
    }
    return uriBuilder.toUriString();
  }
}
