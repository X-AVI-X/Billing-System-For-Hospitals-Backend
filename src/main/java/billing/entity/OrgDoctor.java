package billing.entity;

import javax.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class OrgDoctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private double consultationFee;

    @NonNull
    private double followupFee;

    @NonNull
    private double reportFee;

    @NonNull
    @ElementCollection
    private List<String> availableTimes = new ArrayList<>();

    @ManyToOne()
    @JoinColumn(name = "org_id")
    private Organization organization;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne()
    @JoinColumn(name = "app_user_id")
    private AppUser appUser;


}
