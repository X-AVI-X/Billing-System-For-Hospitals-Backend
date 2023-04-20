package billing.dto;

import lombok.*;

import javax.validation.constraints.Min;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrgMedicineDto {
    private Long id;
    @Min(value = 1, message = "Organization id invalid")
    private Long organizationId;
    @Min(value = 1, message = "Medicine id invalid")
    private Long medicineId;
}
