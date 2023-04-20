package billing.controller.impl;

import billing.controller.PatientController;
import billing.dto.PatientDto;
import billing.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/patient")
public class PatientControllerImpl implements PatientController {
    private final PatientService patientService;

    public PatientControllerImpl(PatientService patientService) {
        this.patientService = patientService;
    }

    @Override
    @PostMapping("add")
    public ResponseEntity<?> add(@RequestBody @Valid PatientDto patientDto){
        return ResponseEntity.ok(patientService.add(patientDto));
    }

    @Override
    @GetMapping("get/{id}")
    public ResponseEntity<?> getById (@PathVariable Long id){

        return ResponseEntity.ok(patientService.get(id));
    }
    @Override
    @GetMapping("get/all")
    public ResponseEntity<?> getAll (){
        return ResponseEntity.ok(patientService.getAll());
    }

    @Override
    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        return ResponseEntity.ok(patientService.delete(id));
    }

    @Override
    @PutMapping("update")
    public ResponseEntity<?> update (@RequestBody @Valid PatientDto patientDto){
        return ResponseEntity.ok(patientService.update(patientDto));
    }

    @Override
    @GetMapping("search")
    public ResponseEntity<?> search(@RequestParam(value = "query", defaultValue = "") String query,
                                    @RequestParam(value = "sortBy", defaultValue = "name") String sortBy,
                                    @RequestParam(value = "page", defaultValue = "0") short page,
                                    @RequestParam(defaultValue = "10") byte size){
        return ResponseEntity.ok(patientService.search(query,page,sortBy,size));
    }
}
