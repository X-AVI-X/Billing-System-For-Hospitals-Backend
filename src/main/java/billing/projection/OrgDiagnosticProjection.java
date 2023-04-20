package billing.projection;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrgDiagnosticProjection {
    private Long id;
    private double price;
    private Long organizationId;
    private String serviceName;


}
