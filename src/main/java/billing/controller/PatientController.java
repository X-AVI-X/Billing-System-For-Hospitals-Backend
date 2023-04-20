package billing.controller;

import billing.dto.PatientDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/patient")
public interface PatientController {

    @PostMapping("add")
    public ResponseEntity<?> add(@RequestBody PatientDto patientDto);

    @GetMapping("get/{id}")
    public ResponseEntity<?> getById (@PathVariable Long id);

    @GetMapping("get/all")
    public ResponseEntity<?> getAll ();

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id);

    @PutMapping("update")
    public ResponseEntity<?> update (@RequestBody PatientDto patientDto);

    @GetMapping("search")
    ResponseEntity<?> search(@RequestParam(value = "query", defaultValue = "") String query,
                             @RequestParam(value = "sortBy", defaultValue = "name") String sortBy,
                             @RequestParam(value = "page", defaultValue = "0") short page,
                             @RequestParam(defaultValue = "10") byte size);
}
