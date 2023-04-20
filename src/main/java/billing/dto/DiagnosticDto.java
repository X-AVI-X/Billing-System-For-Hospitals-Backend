package billing.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class DiagnosticDto {
    private Long id;
    @NotBlank(message = "Diagnostic service name shouldn't be blank.")
    private String serviceName;

}
