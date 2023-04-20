package billing.controller.impl;

import billing.controller.OrgDoctorController;
import billing.dto.DoctorDto;
import billing.dto.OrgDoctorDto;
import billing.entity.OrgDoctor;
import billing.pageResponse.OrgDoctorResponse;
import billing.service.OrgDoctorService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/orgDoctor")
public class OrgDoctorControllerImpl implements OrgDoctorController {

    private final OrgDoctorService orgDoctorService;
    @PostMapping("/appuser/{appUserId}/org/{orgId}/doctor/{doctorId}/add")
    @Override
    public ResponseEntity<OrgDoctorDto> add(@PathVariable Long appUserId,
                                            @PathVariable Long orgId,
                                            @PathVariable Long doctorId,
                                            @Valid @RequestBody OrgDoctorDto orgDoctorDto) {
        return new ResponseEntity<>(orgDoctorService.add(appUserId, orgId, doctorId, orgDoctorDto), HttpStatus.CREATED);
    }
    @GetMapping("/get/{id}")
    @Override
    public ResponseEntity<OrgDoctorDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(orgDoctorService.getById(id));
    }

    @GetMapping("/all")
    @Override
    public ResponseEntity<OrgDoctorResponse> getAll(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy
    ) {

        return ResponseEntity.ok(orgDoctorService.getAll(pageNo,pageSize,sortBy));
    }

    @GetMapping("/appUser/{appUserId}/organization/{orgId}/all")
    @Override
    public ResponseEntity<OrgDoctorResponse> getAllByAppUserAndOrgId(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
            @PathVariable Long appUserId,
            @PathVariable Long orgId) {

        return ResponseEntity.ok(orgDoctorService.getAllByAppUserOrgId(pageNo, pageSize, sortBy, appUserId, orgId));
    }

    @GetMapping("/organization/{orgId}/all")
    @Override
    public ResponseEntity<OrgDoctorResponse> getAllByOrgId(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
            @PathVariable Long orgId) {

        return ResponseEntity.ok(orgDoctorService.getAllByOrgId(pageNo, pageSize, sortBy, orgId));
    }

    @PutMapping("/update/{id}")
    @Override
    public ResponseEntity<OrgDoctorDto> update(@Valid @RequestBody OrgDoctorDto orgDoctorDto,
                                               @PathVariable Long id) {

        OrgDoctorDto response = orgDoctorService.updateById(orgDoctorDto, id);

        return new  ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/org/{orgId}/search")
    @Override
    public ResponseEntity<OrgDoctorResponse> searchOrgDoctor(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
            @PathVariable Long orgId,
            @RequestParam(value = "query", defaultValue = "") String query) {

        return ResponseEntity.ok(orgDoctorService.searchOrgDoctor(pageNo, pageSize,sortBy,orgId, query));
    }
}
