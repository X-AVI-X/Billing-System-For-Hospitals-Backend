package billing.controller.impl;

import billing.controller.OrgDiagnosticController;
import billing.dto.OrgDiagnosticDto;
import billing.service.OrgDiagnosticService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/org-diagnostic")
public class OrgDiagnosticControllerImpl implements OrgDiagnosticController {
    private final OrgDiagnosticService orgDiagnosticService;

    public OrgDiagnosticControllerImpl(OrgDiagnosticService orgDiagnosticService) {
        this.orgDiagnosticService = orgDiagnosticService;
    }

    @Override
    @PostMapping("/add/organization/{orgId}/diagnostic/{diagnosticId}")
    public ResponseEntity<?> add(@Valid @PathVariable Long orgId, @PathVariable Long diagnosticId) {
        return ResponseEntity.ok(orgDiagnosticService.add(orgId, diagnosticId));
    }

    @Override
    @PostMapping("/add")
    public ResponseEntity<?> addAll (@RequestBody @Valid List<OrgDiagnosticDto> orgDiagnosticDtoList) throws Exception {
        return ResponseEntity.ok(orgDiagnosticService.addAll(orgDiagnosticDtoList));
    }

    @Override
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok(orgDiagnosticService.get(id));
    }

    @Override
    @GetMapping("/get/organization/{orgId}/all")
    public ResponseEntity<?> getAll(@PathVariable Long orgId) {
        return ResponseEntity.ok(orgDiagnosticService.getAll(orgId));
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return ResponseEntity.ok(orgDiagnosticService.delete(id));
    }

    @Override
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody @Valid OrgDiagnosticDto orgDiagnosticDto) {
        return ResponseEntity.ok(orgDiagnosticService.update(orgDiagnosticDto));
    }

    @Override
    @GetMapping("/organization/{orgId}/search")
    public ResponseEntity<?> search (
            @PathVariable Long orgId,
            @RequestParam(value = "query", defaultValue = "") String query,
            @RequestParam(value="page", defaultValue = "0") short page,
            @RequestParam(value = "sortBy", defaultValue = "diagnostic.serviceName") String sortBy,
            @RequestParam(defaultValue = "10") byte size ){
        return ResponseEntity.ok(orgDiagnosticService.search(orgId, query, page, sortBy, size));
    }
}
