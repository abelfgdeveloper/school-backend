package es.abelfgdeveloper.school.service.students.model.repository;

import es.abelfgdeveloper.school.service.students.model.entity.StudentEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StudentSpringDataJpaRepository
    extends JpaRepository<StudentEntity, String>, JpaSpecificationExecutor<StudentEntity> {

  Optional<StudentEntity> findByEmail(String email);
}
