package billing.controller;

import billing.dto.MedicineDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/medicine")
public interface MedicineController {

    @PostMapping("add")
    ResponseEntity<?> add(@RequestBody MedicineDto medicineDto);
    @GetMapping("get/{id}")
    ResponseEntity<?> getById (@PathVariable Long id);

    @GetMapping("search")
    ResponseEntity<?> search(
            @RequestParam(value = "query") String query,
            @RequestParam(value = "page", defaultValue = "0") short page,
            @RequestParam(value = "sortBy", defaultValue = "serviceName") String sortBy,
            @RequestParam(defaultValue = "10") byte size);

    @GetMapping("get/all")
    ResponseEntity<?> getAllByPagination(@RequestParam(value = "page", defaultValue = "0") short page,
                                         @RequestParam(value = "sortBy", defaultValue = "serviceName") String sortBy,
                                         @RequestParam(defaultValue = "10") byte size);

//    @DeleteMapping("delete/{id}")
//    ResponseEntity<?> delete(@PathVariable Long id);
    @PutMapping("update")
    ResponseEntity<?> update (@RequestBody MedicineDto medicineDto);
}
