package billing.projection;

import billing.entity.Medicine;
import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrgMedicineProjection {
    private Long id;
    private Medicine medicine;
}
