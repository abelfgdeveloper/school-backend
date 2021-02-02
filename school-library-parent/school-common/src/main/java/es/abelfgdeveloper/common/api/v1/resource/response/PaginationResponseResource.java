package es.abelfgdeveloper.common.api.v1.resource.response;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class PaginationResponseResource {

  private Integer page;
  private Integer size;
  private Integer numberOfElements;
  private Integer totalPages;
  private Long totalElements;

  @JsonProperty("isFirst")
  private Boolean first;

  @JsonProperty("isLast")
  private Boolean last;
}
