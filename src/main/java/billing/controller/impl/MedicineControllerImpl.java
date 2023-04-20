package billing.controller.impl;

import billing.controller.MedicineController;
import billing.dto.MedicineDto;
import billing.service.MedicineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/medicine")
public class MedicineControllerImpl implements MedicineController {
    private final MedicineService medicineService;

    public MedicineControllerImpl(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    @Override
    @PostMapping("add")
    public ResponseEntity<?> add(@RequestBody @Valid MedicineDto medicineDto){
        return ResponseEntity.ok(medicineService.add(medicineDto));
    }
    @Override
    @GetMapping("get/{id}")
    public ResponseEntity<?> getById (@PathVariable Long id){

        return ResponseEntity.ok(medicineService.get(id));
    }
    @Override
    @GetMapping("search")
    public ResponseEntity<?> search (
                                        @RequestParam(value = "query", defaultValue = "") String query,
                                        @RequestParam(value="page", defaultValue = "0") short page,
                                        @RequestParam(value = "sortBy", defaultValue = "name") String sortBy,
                                        @RequestParam(defaultValue = "10") byte size ){
        return ResponseEntity.ok(medicineService.search(query, page, sortBy, size));
    }

    @Override
    @GetMapping("get/all")
    public ResponseEntity<?> getAllByPagination(
                                                @RequestParam(value = "page", defaultValue = "0") short page,
                                                @RequestParam(value = "sortBy", defaultValue = "name") String sortBy,
                                                @RequestParam(defaultValue = "10") byte size){
        return ResponseEntity.ok(medicineService.getAll(page, sortBy, size));
    }

//    @Override
//    @DeleteMapping("delete/{id}")
//    public ResponseEntity<?> delete(@PathVariable Long id){
//        return ResponseEntity.ok(medicineService.delete(id));
//    }

    @Override
    @PutMapping("update")
    public ResponseEntity<?> update (@RequestBody @Valid MedicineDto medicineDto){
        return ResponseEntity.ok(medicineService.update(medicineDto));
    }
}
