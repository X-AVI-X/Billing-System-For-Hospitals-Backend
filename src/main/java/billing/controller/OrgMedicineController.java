package billing.controller;

import billing.dto.OrgMedicineDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/org-medicine")
public interface OrgMedicineController {
    @PostMapping("/add/organization/{orgId}/medicine/{medicineId}")
    public ResponseEntity<?> add(@PathVariable Long orgId, @PathVariable Long medicineId);

    @PostMapping("/add")
    ResponseEntity<?> addAll (@RequestBody @Valid List<OrgMedicineDto> orgMedicineDtoList) throws Exception;

    @GetMapping("get/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id);

    @GetMapping("/get/organization/{orgId}/all")
    ResponseEntity<?>  getAll(@PathVariable Long orgId,
                              @RequestParam(value = "page", defaultValue = "0") short page,
                              @RequestParam(defaultValue = "10") byte size,
                              @RequestParam(value = "sortBy", defaultValue = "medicine.name") String sortBy);

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id);

    @GetMapping("/organization/{orgId}/search")
    ResponseEntity<?> search(@PathVariable Long orgId, @RequestParam(value = "query") String query,
                             @RequestParam(value = "sortBy", defaultValue = "medicine.name") String sortBy,
                             @RequestParam(value = "page", defaultValue = "0") short page,
                             @RequestParam(defaultValue = "10") byte size);

//    @GetMapping("/search")
//    ResponseEntity<?> search (@RequestParam(value = "query") String query,
//                              @RequestParam(value = "page", defaultValue = "0") short page,
//                              @RequestParam(value = "sortBy", defaultValue = "name") String sortBy,
//                              @RequestParam(defaultValue = "10") byte size);

}
