package es.abelfgdeveloper.common.jpa.mapper;

import es.abelfgdeveloper.common.domain.PaginationOut;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component("paginationJpaDomainMapper")
public class PaginationMapper {

  public PaginationOut map(Page<? extends Object> page) {
    return PaginationOut.builder()
        .page(page.getNumber())
        .size(page.getSize())
        .numberOfElements(page.getNumberOfElements())
        .totalPages(page.getTotalPages())
        .totalElements(page.getTotalElements())
        .first(page.isFirst())
        .last(page.isLast())
        .build();
  }
}
