package billing.controller;

import billing.dto.OrgDiagnosticDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/org-diagnostic")
public interface OrgDiagnosticController {
    @PostMapping("/add/organization/{orgId}/diagnostic/{diagnosticId}")
    public ResponseEntity<?> add(@PathVariable Long orgId, @PathVariable Long diagnosticId);

    @PostMapping("/add/all")
    ResponseEntity<?> addAll (@RequestBody List<OrgDiagnosticDto> orgDiagnosticDtoList) throws Exception;

    @GetMapping("get/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id);

    @GetMapping("get/organization/{orgId}/all")
    public ResponseEntity<?> getAll (@PathVariable Long orgId);

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id);

    @PutMapping("/update")
    ResponseEntity<?> update (@RequestBody @Valid OrgDiagnosticDto orgDiagnosticDto);

    @GetMapping("/organization/{orgId}/search")
    ResponseEntity<?> search(@PathVariable Long orgId,
                            @RequestParam(value = "query", defaultValue = "") String query,
                            @RequestParam(value = "page", defaultValue = "0") short page,
                            @RequestParam(value = "sortBy", defaultValue = "diagnostic.serviceName") String sortBy,
                            @RequestParam(defaultValue = "10") byte size);
}
