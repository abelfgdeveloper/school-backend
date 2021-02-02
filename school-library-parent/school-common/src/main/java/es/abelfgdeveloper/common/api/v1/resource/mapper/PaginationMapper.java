package es.abelfgdeveloper.common.api.v1.resource.mapper;

import es.abelfgdeveloper.common.api.v1.resource.response.PaginationResponseResource;
import es.abelfgdeveloper.common.domain.PaginationIn;
import es.abelfgdeveloper.common.domain.PaginationIn.PaginationInBuilder;
import es.abelfgdeveloper.common.domain.PaginationOut;
import es.abelfgdeveloper.common.exception.client.BadRequestException;
import org.springframework.stereotype.Component;

@Component("paginationResourceMapperV1")
public class PaginationMapper {

  public PaginationIn map(Integer page, Integer size) {
    PaginationInBuilder paginationInBuilder = PaginationIn.builder();
    if (page == null) {
      paginationInBuilder.page(0);
    } else {
      validatePage(page);
      paginationInBuilder.page(page - 1);
    }
    if (size == null) {
      paginationInBuilder.size(10);
    } else {
      validatePageSize(size);
      paginationInBuilder.size(size);
    }
    return paginationInBuilder.build();
  }

  public PaginationResponseResource map(PaginationOut pagination) {
    return PaginationResponseResource.builder()
        .page(pagination.getPage() + 1)
        .size(pagination.getSize())
        .numberOfElements(pagination.getNumberOfElements())
        .totalPages(pagination.getTotalPages())
        .totalElements(pagination.getTotalElements())
        .first(pagination.isFirst())
        .last(pagination.isLast())
        .build();
  }

  private void validatePage(Integer page) {
    if (page.intValue() < 1) {
      throw new BadRequestException("El numero de pagina no puede ser inferior a 1");
    }
  }

  private void validatePageSize(Integer size) {
    if (size.intValue() < 1) {
      throw new BadRequestException("El tamaño de pagina no puede ser inferior a 1");
    }
    if (size.intValue() > 50) {
      throw new BadRequestException("El tamaño de pagina no puede ser superior a 50");
    }
  }
}
