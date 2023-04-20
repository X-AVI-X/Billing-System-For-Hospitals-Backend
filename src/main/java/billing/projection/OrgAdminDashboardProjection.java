package billing.projection;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrgAdminDashboardProjection {
    private long orgDoctors;
    private long orgMedicines;
    private long orgDiagnostics;
    private long orgEmployees;

//    private long orgRevenue;
}
