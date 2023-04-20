package billing.projection;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AdminDashboardProjection {
    private long doctors;
    private long medicines;
    private long diagnostics;
    private long employees;
    private long organizations;
//    private double totalRev;
}
