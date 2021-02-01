package es.abelfgdeveloper.school.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class SchoolEurekaServerApplication {

  public static void main(String[] args) {
    SpringApplication.run(SchoolEurekaServerApplication.class, args);
  }
}
