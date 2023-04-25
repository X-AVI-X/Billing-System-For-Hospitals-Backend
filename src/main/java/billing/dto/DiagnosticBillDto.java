package billing.dto;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class DiagnosticBillDto {

    private Long id;

    @NotNull(message = "Diagnostic service list and discount shouldn't be blank.")
    private List<OrgDiagnosticAndDiscountWrapperDto> orgDiagnosticAndDiscounts;

    @NotNull(message = "Diagnostic service totalFeeWithoutDiscount shouldn't be blank.")
    private double totalFeeWithoutAnyDiscount;

    @NotNull(message = "Diagnostic service totalFeeWithoutDiscount shouldn't be blank.")
    private double totalFeeAfterIndividualDiscount;

    @NotNull(message = "Diagnostic service overallDiscount shouldn't be blank.")
    private byte overallDiscount;

    @NotNull(message = "Diagnostic service finalFee shouldn't be blank.")
    private double finalFeeAfterAllDiscount;

    private LocalDateTime timestamp;

    @Min(1)
    @NotNull(message = "Patient ID shouldn't be blank.")
    private Long patientId;

}
