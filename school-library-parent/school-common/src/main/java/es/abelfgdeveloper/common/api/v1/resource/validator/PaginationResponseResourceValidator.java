package es.abelfgdeveloper.common.api.v1.resource.validator;

import es.abelfgdeveloper.common.api.v1.resource.response.PaginationResponseResource;
import es.abelfgdeveloper.common.exception.server.ValidationResponseException;
import org.springframework.stereotype.Component;

@Component
public class PaginationResponseResourceValidator {

  public void validate(PaginationResponseResource pagination) {
    validatePage(pagination.getPage());
    validateSize(pagination.getSize());
    validateNumberOfElements(pagination.getNumberOfElements());
    validateTotalPages(pagination.getTotalPages());
    validateTotalElements(pagination.getTotalElements());
    validateFirst(pagination.getFirst());
    validateLast(pagination.getLast());
  }

  private void validatePage(Integer page) {
    if (page == null) {
      throw new ValidationResponseException("El campo page es obligatorio");
    }
    if (page < 1) {
      throw new ValidationResponseException("El totalElements no puede ser inferior a 1");
    }
  }

  private void validateSize(Integer size) {
    if (size == null) {
      throw new ValidationResponseException("El campo size es obligatorio");
    }
    if (size < 1) {
      throw new ValidationResponseException("El campo size no puede ser inferior a 1");
    }
  }

  private void validateNumberOfElements(Integer numberOfElements) {
    if (numberOfElements == null) {
      throw new ValidationResponseException("El campo numberOfElements es obligatorio");
    }
    if (numberOfElements < 0) {
      throw new ValidationResponseException("El campo numberOfElements no puede ser inferior a 0");
    }
  }

  private void validateTotalPages(Integer totalPages) {
    if (totalPages == null) {
      throw new ValidationResponseException("El campo totalPages es obligatorio");
    }
    if (totalPages < 0) {
      throw new ValidationResponseException("El campo totalPages no puede ser inferior a 0");
    }
  }

  private void validateTotalElements(Long totalElements) {
    if (totalElements == null) {
      throw new ValidationResponseException("El campo totalElements es obligatorio");
    }
    if (totalElements < 0) {
      throw new ValidationResponseException("El totalElements no puede ser inferior a 0");
    }
  }

  private void validateFirst(Boolean first) {
    if (first == null) {
      throw new ValidationResponseException("El campo first es obligatorio");
    }
  }

  private void validateLast(Boolean last) {
    if (last == null) {
      throw new ValidationResponseException("El campo last es obligatorio");
    }
  }
}
