package billing.controller.impl;

import billing.controller.OrgMedicineController;
import billing.dto.OrgMedicineDto;
import billing.service.OrgMedicineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/org-medicine")
public class OrgMedicineControllerImpl implements OrgMedicineController {

    private final OrgMedicineService orgMedicineService;

    public OrgMedicineControllerImpl(OrgMedicineService orgMedicineService) {
        this.orgMedicineService = orgMedicineService;
    }

    @PostMapping("/add/organization/{orgId}/medicine/{medicineId}")
    public ResponseEntity<?> add(@PathVariable Long orgId, @PathVariable Long medicineId) {
        return ResponseEntity.ok(orgMedicineService.add(orgId, medicineId));
    }

    @Override
    @PostMapping("/add")
    public ResponseEntity<?> addAll(@RequestBody @Valid List<OrgMedicineDto> orgMedicineDtoList) throws Exception {
        return ResponseEntity.ok(orgMedicineService.addAll(orgMedicineDtoList));
    }

    @Override
    @GetMapping("get/{id}")
    public ResponseEntity<?>  getById(@PathVariable Long id) {
        return ResponseEntity.ok(orgMedicineService.get(id));
    }

    @Override
    @GetMapping("/get/organization/{orgId}/all")
    public ResponseEntity<?>  getAll(@PathVariable Long orgId,
                                     @RequestParam(value = "page", defaultValue = "0") short page,
                                     @RequestParam(defaultValue = "10") byte size,
                                     @RequestParam(value = "sortBy", defaultValue = "medicine.name") String sortBy) {
        return ResponseEntity.ok(orgMedicineService.getAll(orgId, page, size, sortBy ));
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return ResponseEntity.ok(orgMedicineService.delete(id));
    }

    @Override
    @GetMapping("/organization/{orgId}/search")
    public ResponseEntity<?> search(@PathVariable Long orgId, @RequestParam(value = "query", defaultValue = "") String query,
                                    @RequestParam(value = "sortBy", defaultValue = "medicine.name") String sortBy,
                                    @RequestParam(value = "page", defaultValue = "0") short page,
                                    @RequestParam(defaultValue = "10") byte size) {
        return ResponseEntity.ok(orgMedicineService.search(orgId,query,page,sortBy,size));
    }
}
