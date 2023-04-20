package billing.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DiagnosticBill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany (cascade=CascadeType.ALL)
    @ToString.Exclude
    private List<OrgDiagnosticAndDiscount> orgDiagnosticAndDiscounts;

    private double totalFeeWithoutAnyDiscount;

    private double totalFeeAfterIndividualDiscount;

    private byte overallDiscount;

    private double finalFeeAfterAllDiscount;

    @CreationTimestamp
    private LocalDateTime timestamp;
    @ManyToOne
    private AppUser appUser;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;

    @ManyToOne
    private Patient patient;
}
