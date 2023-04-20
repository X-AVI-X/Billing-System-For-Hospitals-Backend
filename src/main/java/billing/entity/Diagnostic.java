package billing.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
//@Table(uniqueConstraints = {
//        @UniqueConstraint(columnNames = {"serviceName"})
//})
public class Diagnostic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String serviceName;
}
