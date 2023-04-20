package billing.dto;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.ElementCollection;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class PharmacyBillDto {

    private Long id;

    private List<Integer> medQuantities;

    @NotNull
    private double totalBill;

    @NotNull
    private double discount;

    @NotNull
    private double finalBill;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
