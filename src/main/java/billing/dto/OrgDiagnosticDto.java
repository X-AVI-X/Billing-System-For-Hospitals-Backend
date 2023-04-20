package billing.dto;

import lombok.*;

import javax.validation.constraints.Min;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrgDiagnosticDto {
    private Long id;
    @Min(value = 0, message = "Price can't be 0 or less than 0.")
    private double price;

    @Min(value=1, message = "Organization id invalid")
    private Long organizationId;

    @Min(value = 1, message = "Diagnostic id invalid")
    private Long diagnosticId;
}
