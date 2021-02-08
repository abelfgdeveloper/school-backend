package es.abelfgdeveloper.school.service.students.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import es.abelfgdeveloper.school.service.students.model.repository.StudentSpringDataJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

public abstract class CommonApiTest {

  protected static final String EMPTY_STRING = "";

  @Autowired private ObjectMapper objectMapper;
  @Autowired protected MockMvc mvc;
  @Autowired protected StudentSpringDataJpaRepository studentSpringDataJpaRepository;

  protected void cleanDataBase() {
    studentSpringDataJpaRepository.deleteAll();
  }

  protected <T> T convertJsonAsStringToObject(String jsonAsString, Class<T> valueType)
      throws Exception {
    return objectMapper.readValue(jsonAsString, valueType);
  }

  protected String convertObjectToJsonAsString(Object content) throws Exception {
    return objectMapper.writeValueAsString(content);
  }
}
