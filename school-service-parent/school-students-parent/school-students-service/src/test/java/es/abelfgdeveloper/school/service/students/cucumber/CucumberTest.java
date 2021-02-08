package es.abelfgdeveloper.school.service.students.cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "classpath:features",
    plugin = {"pretty", "json:target/cucumber-report.json"})
public class CucumberTest {}
