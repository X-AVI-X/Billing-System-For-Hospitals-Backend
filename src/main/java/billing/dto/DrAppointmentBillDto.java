package billing.dto;

import billing.entity.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class DrAppointmentBillDto {

    private Long id;

    @NotNull
    @Temporal(TemporalType.TIME)
    @DateTimeFormat(style = "hh:mm")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="hh:mm")
    private Date drTime;

    @NotEmpty
    @DecimalMax("10000.0") @DecimalMin("0.0")
    private double fee;

    @NotNull
    private Fees type;

    @NotEmpty
    @DecimalMax("10000.0") @DecimalMin("0.0")
    private double discount;

    @NotEmpty
    @DecimalMax("10000.0") @DecimalMin("0.0")
    private double finalFee;

    @CreationTimestamp
    private LocalDateTime createdAt;

    private PatientDto patient;
    private DoctorDto doctor;
    private AppUserDto createdBy;
    private OrganizationDto organization;

}
