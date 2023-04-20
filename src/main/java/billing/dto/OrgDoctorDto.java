package billing.dto;

import lombok.Data;

import javax.validation.constraints.*;
import java.util.List;

@Data
public class OrgDoctorDto {

    private DoctorDto doctor;
    private OrganizationDto organization;
    private Long id;

    @DecimalMax("10000.0") @DecimalMin("0.0")
    @NotNull
    private double consultationFee;

    @DecimalMax("10000.0") @DecimalMin("0.0")
    @NotNull
    private double followupFee;

    @DecimalMax("10000.0") @DecimalMin("0.0")
    @NotNull
    private double reportFee;

    @NotEmpty
    private  List<String> availableTimes;

}
