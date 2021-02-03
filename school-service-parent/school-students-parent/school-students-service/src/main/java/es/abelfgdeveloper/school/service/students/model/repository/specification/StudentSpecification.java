package es.abelfgdeveloper.school.service.students.model.repository.specification;

import es.abelfgdeveloper.school.service.students.model.entity.StudentEntity;
import es.abelfgdeveloper.school.service.students.model.entity.StudentEntity_;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class StudentSpecification {

  public Specification<StudentEntity> firstNameOrLastNameOrEmailLike(String query) {
    return firstNameLike(query).or(lastNameLike(query)).or(emailLike(query));
  }

  public Specification<StudentEntity> firstNameLike(String firstName) {
    return (root, criteriaQuery, criteriaBuilder) ->
        criteriaBuilder.like(
            criteriaBuilder.upper(root.get(StudentEntity_.firstName)),
            "%" + firstName.toUpperCase() + "%");
  }

  public Specification<StudentEntity> lastNameLike(String lastName) {
    return (root, criteriaQuery, criteriaBuilder) ->
        criteriaBuilder.like(
            criteriaBuilder.upper(root.get(StudentEntity_.lastName)),
            "%" + lastName.toUpperCase() + "%");
  }

  public Specification<StudentEntity> emailLike(String email) {
    return (root, criteriaQuery, criteriaBuilder) ->
        criteriaBuilder.like(
            criteriaBuilder.upper(root.get(StudentEntity_.email)), "%" + email.toUpperCase() + "%");
  }
}
