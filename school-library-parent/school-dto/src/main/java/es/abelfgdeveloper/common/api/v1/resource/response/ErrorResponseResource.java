package es.abelfgdeveloper.common.api.v1.resource.response;

import java.time.LocalDateTime;
import java.util.List;
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
public class ErrorResponseResource {

  private LocalDateTime timestamp;
  private String code;
  private int respondeCode;
  private String status;
  private String detail;
  private List<String> stackTrace;
  private ErrorResponseResource cause;
}
