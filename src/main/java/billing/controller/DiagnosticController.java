package billing.controller;

import billing.dto.DiagnosticDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/diagnostic")
public interface DiagnosticController {

    @PostMapping("add")
    ResponseEntity<?> add(@RequestBody DiagnosticDto diagnosticDto);

    @PostMapping("add/all")
    ResponseEntity<?> addAll(@RequestBody @Valid List<DiagnosticDto> diagnosticDtoList);

    @GetMapping("get/{id}")
    ResponseEntity<?> getById (@PathVariable Long id);

    @GetMapping("search")
    ResponseEntity<?> search(
            @RequestParam(value = "query") String query,
            @RequestParam(value = "page", defaultValue = "0") short page,
            @RequestParam(value = "sortBy", defaultValue = "serviceName") String sortBy,
            @RequestParam(defaultValue = "10") byte size);

    @GetMapping("get/all")
    ResponseEntity<?> getAllByPagination(@RequestParam(value = "page", defaultValue = "0", required = false) short page,
                                         @RequestParam(value = "sortBy", defaultValue = "serviceName", required = false) String sortBy,
                                         @RequestParam(defaultValue = "10") byte size);

    @DeleteMapping("delete/{id}")
    ResponseEntity<?> delete(@PathVariable Long id);

    @PutMapping("update")
    ResponseEntity<?> update (@RequestBody DiagnosticDto diagnosticDto);
}
