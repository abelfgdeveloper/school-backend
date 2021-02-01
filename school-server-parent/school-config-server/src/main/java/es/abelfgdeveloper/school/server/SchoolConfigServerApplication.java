package es.abelfgdeveloper.school.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class SchoolConfigServerApplication {

  public static void main(String[] args) {
    SpringApplication.run(SchoolConfigServerApplication.class, args);
  }
}
