package billing.controller.impl;

import billing.controller.PharmacyBillController;
import billing.dto.PharmacyBillDto;
import billing.pageResponse.PharmacyBillResponse;
import billing.service.PharmacyBillService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/pharmacyBill")
public class PharmacyBillControllerImpl implements PharmacyBillController {

    private final PharmacyBillService pharmacyBillService;
    @Override
    @PostMapping("/med/{medId}/patient/{patientId}/org/{orgId}/appUser/{appUserId}/add")
    public ResponseEntity<?> add(@PathVariable List<Long> medId,
                                 @PathVariable Long patientId,
                                 @PathVariable Long orgId,
                                 @PathVariable Long appUserId,
                                 @RequestBody PharmacyBillDto pharmacyBillDto) {

        return new ResponseEntity<>(pharmacyBillService.add(medId,patientId,orgId,appUserId,pharmacyBillDto), HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    @Override
    public ResponseEntity<PharmacyBillDto> getById(@PathVariable Long id) {

        return ResponseEntity.ok(pharmacyBillService.getById(id));
    }

    @GetMapping("/all")
    @Override
    public ResponseEntity<PharmacyBillResponse> getAll(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy
    ) {
        return  ResponseEntity.ok(pharmacyBillService.getAll(pageNo, pageSize, sortBy));
    }

    @Override
    @GetMapping("/get/org/{orgId}/all")
    public ResponseEntity<PharmacyBillResponse> getAllByOrgId(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
            @PathVariable Long orgId) {

        return ResponseEntity.ok(pharmacyBillService.getAllByOrgId(pageNo,pageSize,sortBy,orgId));
    }

    @Override
    @GetMapping("/get/org/{orgId}/appUser/{userId}/all")
    public ResponseEntity<PharmacyBillResponse> getAllByOrgAndUserId(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
            @PathVariable Long orgId,
            @PathVariable Long userId) {

        return ResponseEntity.ok(pharmacyBillService.getAllByOrgAndUserId(pageNo,pageSize,sortBy,orgId,userId));
    }

}
