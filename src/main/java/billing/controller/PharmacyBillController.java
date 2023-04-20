package billing.controller;

import billing.dto.PharmacyBillDto;
import billing.pageResponse.PharmacyBillResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PharmacyBillController {

    ResponseEntity<?> add(List<Long> medId, Long patientId, Long orgId, Long appUserId, PharmacyBillDto pharmacyBillDto);
    ResponseEntity<PharmacyBillDto> getById(Long id);
    ResponseEntity<PharmacyBillResponse> getAll(int pageNo, int pageSize, String sortBy);
    ResponseEntity<PharmacyBillResponse> getAllByOrgId(int pageNo, int pageSize, String sortBy, Long orgId);
    ResponseEntity<PharmacyBillResponse> getAllByOrgAndUserId(int pageNo, int pageSize, String sortBy, Long orgId, Long userId);

}
