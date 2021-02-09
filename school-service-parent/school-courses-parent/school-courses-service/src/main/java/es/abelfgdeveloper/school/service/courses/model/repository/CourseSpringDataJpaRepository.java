package es.abelfgdeveloper.school.service.courses.model.repository;

import es.abelfgdeveloper.school.service.courses.model.entity.CourseEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CourseSpringDataJpaRepository
    extends JpaRepository<CourseEntity, String>, JpaSpecificationExecutor<CourseEntity> {

  Optional<CourseEntity> findByName(String name);
}
