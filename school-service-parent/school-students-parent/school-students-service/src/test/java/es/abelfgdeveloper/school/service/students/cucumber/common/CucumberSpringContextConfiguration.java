package es.abelfgdeveloper.school.service.students.cucumber.common;

import io.cucumber.java.Before;
import io.cucumber.spring.CucumberContextConfiguration;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

@Slf4j
@ActiveProfiles("test")
@CucumberContextConfiguration
@AutoConfigureEmbeddedDatabase
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CucumberSpringContextConfiguration {

  @LocalServerPort int randomServerPort;

  @Before
  public void setUp() {
    log.info("");
    log.info("");
    log.info("");
    log.info("");
    log.info(
        "-------------- Spring Context Initialized For Executing Cucumber Tests --------------");
    log.info("");
    log.info("");
    log.info("");
    log.info("");
  }
}
