package billing.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrgDiagnostic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double price;
    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;
    @OneToOne
    @JoinColumn(name = "diagnostic_id")
    private Diagnostic diagnostic;



}
