package billing.projection;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrgMedicineFinalProjection {
    private short totalMedicines;
    private List<OrgMedicineProjection> orgMedicines;
}
