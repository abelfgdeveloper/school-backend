package es.abelfgdeveloper.school.service.model.repository;

import es.abelfgdeveloper.school.service.model.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StudentRepository
    extends JpaRepository<StudentEntity, String>, JpaSpecificationExecutor<StudentEntity> {}
