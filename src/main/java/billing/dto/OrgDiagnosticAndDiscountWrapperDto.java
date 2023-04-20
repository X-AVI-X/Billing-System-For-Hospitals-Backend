package billing.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrgDiagnosticAndDiscountWrapperDto {
    private Long id;
    @NotNull(message = "Org-Diagnostic service id shouldn't be blank.")
    private Long orgDiagnosticId;
    private Double invoicePrice;
    @NotNull(message = "Org-Diagnostic service discount shouldn't be blank. Put 0 (zero) if it's no discount")
    @Positive(message =  "Minimum value 0")
    private byte discount;
    @JsonIgnore
    public void setId(Long id) {
        this.id = id;
    }
}
