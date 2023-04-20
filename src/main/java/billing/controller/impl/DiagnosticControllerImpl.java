package billing.controller.impl;

import billing.controller.DiagnosticController;
import billing.dto.DiagnosticDto;
import billing.service.DiagnosticService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/diagnostic")
public class DiagnosticControllerImpl implements DiagnosticController {
    private final DiagnosticService diagnosticService;

    public DiagnosticControllerImpl(DiagnosticService diagnosticService) {
        this.diagnosticService = diagnosticService;
    }

    @Override
    @PostMapping("add")
    public ResponseEntity<?> add(@RequestBody @Valid DiagnosticDto diagnosticDto){
        return ResponseEntity.ok(diagnosticService.add(diagnosticDto));
    }

    @Override
    @PostMapping("add/all")
    public ResponseEntity<?> addAll(@RequestBody @Valid List<DiagnosticDto> diagnosticDtoList){
        return ResponseEntity.ok(diagnosticService.addAll(diagnosticDtoList));
    }

    @Override
    @GetMapping("get/{id}")
    public ResponseEntity<?> getById (@PathVariable Long id){
        return ResponseEntity.ok(diagnosticService.get(id));
    }

    @Override
    @GetMapping("search")
    public ResponseEntity<?> search (
                                     @RequestParam(value = "query", defaultValue = "") String query,
                                     @RequestParam(value="page", defaultValue = "0") short page,
                                     @RequestParam(value = "sortBy", defaultValue = "serviceName") String sortBy,
                                     @RequestParam(defaultValue = "10") byte size ){
        return ResponseEntity.ok(diagnosticService.search(query, page, sortBy, size));
    }

    @Override
    @GetMapping("get/all")
    public ResponseEntity<?> getAllByPagination(@RequestParam(value = "page", defaultValue = "0") short page,
                                                @RequestParam(value = "sortBy", defaultValue = "serviceName") String sortBy,
                                                @RequestParam(defaultValue = "10") byte size){
        return ResponseEntity.ok(diagnosticService.getAll(page, sortBy, size));
    }

    @Override
    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        return ResponseEntity.ok(diagnosticService.delete(id));
    }

    @Override
    @PutMapping("update")
    public ResponseEntity<?> update (@RequestBody @Valid DiagnosticDto diagnosticDto){
        return ResponseEntity.ok(diagnosticService.update(diagnosticDto));
    }
}
