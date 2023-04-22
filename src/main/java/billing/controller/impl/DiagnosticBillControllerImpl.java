package billing.controller.impl;

import billing.controller.DiagnosticBillController;
import billing.dto.DiagnosticBillDto;
import billing.service.DiagnosticBillService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping ("/diagnostic-bill")
public class DiagnosticBillControllerImpl implements DiagnosticBillController {
    private final DiagnosticBillService diagnosticBillService;


    public DiagnosticBillControllerImpl(DiagnosticBillService diagnosticBillService) {
        this.diagnosticBillService = diagnosticBillService;
    }

    @Override
    @PostMapping("/add")
    public ResponseEntity<?> add (@RequestBody @Valid DiagnosticBillDto diagnosticBillDto){
        return ResponseEntity.ok(diagnosticBillService.add(diagnosticBillDto));
    }

    @Override
    @GetMapping("/view/{id}")
    public ResponseEntity<?> view (@PathVariable Long id){
        return ResponseEntity.ok(diagnosticBillService.viewInvoice(id));
    }

    @Override
    @GetMapping("view/{orgId}/all")
    public Page<?> viewAll (
                                    @PathVariable Long orgId,
                                    @RequestParam(value = "query", defaultValue = "") String query,
                                    @RequestParam(value="page", defaultValue = "0") short page,
                                    @RequestParam(value = "sortBy", defaultValue = "timestamp") String sortBy,
                                    @RequestParam(value = "sortOrder", defaultValue = "Sort.Direction.DESC") String sortOrder,
                                    @RequestParam(defaultValue = "10") byte size ){

        return diagnosticBillService.search(orgId,query,page,size,sortBy, sortOrder);
    }
}
