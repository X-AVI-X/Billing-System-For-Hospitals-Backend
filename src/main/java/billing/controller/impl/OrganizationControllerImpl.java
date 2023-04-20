package billing.controller.impl;

import billing.controller.OrganizationController;
import billing.dto.OrganizationDto;
import billing.pageResponse.OrganizationResponse;
import billing.service.OrganizationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/organization")
public class OrganizationControllerImpl implements OrganizationController {

    private OrganizationService organizationService;

    @PostMapping("/add")
//    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public ResponseEntity<OrganizationDto> add(@Valid @RequestBody OrganizationDto organizationDto) {
        return new ResponseEntity<>(organizationService.add(organizationDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<OrganizationDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(organizationService.getOrganizationById(id));
    }

    @GetMapping("/all")
    @Override
    public ResponseEntity<OrganizationResponse> getAll(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy
    ) {
        return ResponseEntity.ok(organizationService.getAll(pageNo,pageSize,sortBy));
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public ResponseEntity<OrganizationDto> update(@Valid @RequestBody OrganizationDto organizationDto,
                                                  @PathVariable Long id) {
        OrganizationDto responseOrg = organizationService.updateById(organizationDto,id);

        return new  ResponseEntity<>(responseOrg,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public ResponseEntity<String> delete(@PathVariable Long id){
        organizationService.deleteById(id);
        return new  ResponseEntity<>("organization deleted", HttpStatus.OK);
    }

    @GetMapping("/search")
    @Override
    public ResponseEntity<OrganizationResponse> searchOrganization(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(value = "query", defaultValue = "") String query) {

        return ResponseEntity.ok(organizationService.searchOrganization(pageNo, pageSize, sortBy, query));
    }
}
