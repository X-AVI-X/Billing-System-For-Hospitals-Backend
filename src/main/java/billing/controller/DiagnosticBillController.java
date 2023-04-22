package billing.controller;

import billing.dto.DiagnosticBillDto;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public interface DiagnosticBillController {

    @PostMapping("/add")
    ResponseEntity<?> add (@RequestBody @Valid DiagnosticBillDto diagnosticBillDto);

    @GetMapping("/view/{id}")
    ResponseEntity<?> view (@PathVariable Long id);

    @GetMapping("view/{orgId}/all")
    Page<?> viewAll(
            @PathVariable Long orgId,
            @RequestParam(value = "query", defaultValue = "") String query,
            @RequestParam(value = "page", defaultValue = "0") short page,
            @RequestParam(value = "sortBy", defaultValue = "timestamp") String sortBy,
            @RequestParam(value = "sortOrder", defaultValue = "Sort.Direction.DESC") String sortOrder,
            @RequestParam(defaultValue = "10") byte size);
}
