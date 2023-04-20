package billing.projection;

import billing.entity.AppUser;
import billing.entity.OrgDiagnosticAndDiscount;
import billing.entity.Organization;
import billing.entity.Patient;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DiagnosticBillProjection {
    private Long id;

    private List<OrgDiagnosticAndDiscount> orgDiagnosticAndDiscounts;

    private double totalFeeWithoutAnyDiscount;

    private double totalFeeAfterIndividualDiscount;

    private byte overallDiscount;

    private double finalFeeAfterAllDiscount;

    private String timestamp;

    private AppUser appUser;

    private Organization organization;

    private Patient patient;
}
