package billing.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
//@Table(uniqueConstraints = {
//        @UniqueConstraint(columnNames = {"name"})
//})
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;

    private double price;

    @NonNull
    private String genericName;

    private String formulation;

    @NonNull
    private String strength;

    private String vendor;
}
