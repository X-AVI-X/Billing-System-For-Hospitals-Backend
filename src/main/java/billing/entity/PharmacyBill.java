package billing.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class PharmacyBill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    private List<Integer> medQuantities = new ArrayList<>();

    @NotNull
    private double totalBill;

    @NotNull
    private double discount;

    @NotNull
    private double finalBill;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @NotNull
    @ManyToMany
    @ToString.Exclude
    private List<Medicine> medicines = new ArrayList<>();

    @NotNull
    @ManyToOne
    private Patient patient;

    @NotNull
    @ManyToOne
    private Organization organization;

    @ManyToOne
    private AppUser createdBy;

}
