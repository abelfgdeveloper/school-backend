package es.abelfgdeveloper.school.service.courses.model.repository;

import es.abelfgdeveloper.school.service.courses.model.entity.CourseEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CourseSpringDataJpaRepository
    extends JpaRepository<CourseEntity, String>, JpaSpecificationExecutor<CourseEntity> {

  Optional<CourseEntity> findByName(String name);

  @Query("SELECT c FROM CourseEntity c WHERE :studentId IN ELEMENTS(c.students)")
  List<CourseEntity> findByStudentId(@Param("studentId") String studentId);
}
