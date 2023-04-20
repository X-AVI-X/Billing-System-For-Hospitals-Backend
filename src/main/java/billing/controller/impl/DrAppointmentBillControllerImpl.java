package billing.controller.impl;

import billing.controller.DrAppointmentBillController;
import billing.dto.DrAppointmentBillDto;
import billing.pageResponse.AppointmentResponse;
import billing.service.DrAppointmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/appointmentBill")
public class DrAppointmentBillControllerImpl implements DrAppointmentBillController {

    private final DrAppointmentService drAppointmentService;
    @Override
    @PostMapping("/appuser/{appUserId}/orgDoc/{orgDrId}/patient/{patientId}/org/{orgId}/add")
    public ResponseEntity<DrAppointmentBillDto> add(@PathVariable Long appUserId,
                                                    @PathVariable Long orgDrId,
                                                    @PathVariable Long patientId,
                                                    @PathVariable Long orgId,
                                                    @RequestBody DrAppointmentBillDto drAppointmentBillDto) {

        return new ResponseEntity<>(drAppointmentService.add(appUserId,orgDrId,patientId,orgId,drAppointmentBillDto), HttpStatus.CREATED);
    }

    @GetMapping("/get/{apptId}")
    @Override
    public ResponseEntity<DrAppointmentBillDto> getById(@PathVariable Long apptId) {

        return ResponseEntity.ok(drAppointmentService.getById(apptId));
    }

    @GetMapping("/all")
    @Override
    public ResponseEntity<AppointmentResponse> getAll(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy
    ) {
        return  ResponseEntity.ok(drAppointmentService.getAll(pageNo, pageSize, sortBy));
    }

    @GetMapping("/get/org/{orgId}/all")
    @Override
    public ResponseEntity<AppointmentResponse> getAllByOrgId (
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
            @PathVariable Long orgId) {

        return ResponseEntity.ok(drAppointmentService.getAllByOrgId(pageNo, pageSize, sortBy, orgId));
    }

    @GetMapping("/get/org/{orgId}/user/{userId}/all")
    @Override
    public ResponseEntity<AppointmentResponse> getAllByOrgAndUserId (
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
            @PathVariable Long orgId, @PathVariable Long userId) {

        return ResponseEntity.ok(drAppointmentService.getAllByOrgAndUserId(pageNo, pageSize, sortBy, orgId, userId));
    }

    @GetMapping("/get/org/{orgId}/orgDr/{orgDrId}/all")
    @Override
    public ResponseEntity<AppointmentResponse> getAllByOrgAndOrgDrId (
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
            @PathVariable Long orgId, @PathVariable Long orgDrId) {

        return ResponseEntity.ok(drAppointmentService.getAllByOrgAndOrgDr(pageNo, pageSize, sortBy, orgId, orgDrId));
    }
}
