package billing.service;

import billing.dto.OrgMedicineDto;
import billing.projection.OrgMedicineFinalProjection;
import billing.projection.OrgMedicineProjection;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OrgMedicineService {
    OrgMedicineDto add(Long orgId, Long medicineId);
    List<OrgMedicineProjection> addAll(List<OrgMedicineDto> orgMedicineDtoList) throws Exception;
    OrgMedicineDto get(Long id);
//    List<OrgMedicineProjection> getAll (Long orgId, short page, byte size);

    OrgMedicineFinalProjection getAll(Long orgId, short pageNo, byte size, String sortBy);

    String delete (Long id);
    Page<OrgMedicineProjection> search(Long orgId, String query, short page, String sortBy, byte size);
}
