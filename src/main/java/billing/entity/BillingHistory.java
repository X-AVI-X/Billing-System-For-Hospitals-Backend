package billing.entity;

import lombok.*;
import javax.persistence.*;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class BillingHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private Long invoiceId;

    @NonNull
    private Service serviceType;

    @NonNull
    private double totalAmount;

    @NonNull
    private Date time;


}
