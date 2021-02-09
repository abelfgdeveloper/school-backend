package es.abelfgdeveloper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SchoolCoursesServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(SchoolCoursesServiceApplication.class, args);
  }
}
