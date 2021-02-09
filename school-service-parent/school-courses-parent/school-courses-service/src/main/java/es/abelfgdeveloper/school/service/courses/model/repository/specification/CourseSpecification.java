package es.abelfgdeveloper.school.service.courses.model.repository.specification;

import es.abelfgdeveloper.school.service.courses.model.entity.CourseEntity;
import es.abelfgdeveloper.school.service.courses.model.entity.CourseEntity_;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class CourseSpecification {

  public Specification<CourseEntity> nameLike(String name) {
    return (root, criteriaQuery, criteriaBuilder) ->
        criteriaBuilder.like(
            criteriaBuilder.upper(root.get(CourseEntity_.name)), "%" + name.toUpperCase() + "%");
  }
}
