package es.abelfgdeveloper.common.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PaginationOut {

  private Integer page;
  private Integer size;
  private Integer numberOfElements;
  private Integer totalPages;
  private Long totalElements;
  private boolean first;
  private boolean last;
}
