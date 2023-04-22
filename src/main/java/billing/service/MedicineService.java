package billing.service;

import billing.dto.MedicineDto;
import billing.entity.Medicine;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MedicineService {
//    final MedicineRepository medicineRepository = null;
    MedicineDto add(MedicineDto medicineDto);
    MedicineDto get(Long id);
    String delete (Long id);;
    MedicineDto update(MedicineDto medicineDto);
    List<MedicineDto> getAll(short page, String sortBy, byte size);
    Page<Medicine> search(String query, short page, String sortBy, byte size);

    Page<Medicine> findMedicineNotInOrganization(Long orgId, String query, short page, String sortBy, byte size);
}
