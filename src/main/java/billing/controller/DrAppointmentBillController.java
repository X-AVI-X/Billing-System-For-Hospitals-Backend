package billing.controller;

import billing.dto.DrAppointmentBillDto;
import billing.pageResponse.AppointmentResponse;
import org.springframework.http.ResponseEntity;

public interface DrAppointmentBillController {

    ResponseEntity<DrAppointmentBillDto> add(Long appUserId, Long orgDrId, Long patientId, Long orgId, DrAppointmentBillDto drAppointmentBillDto);
    ResponseEntity<DrAppointmentBillDto> getById(Long apptId);
    ResponseEntity<AppointmentResponse> getAll(int pageNo, int pageSize, String sortBy);
    ResponseEntity<AppointmentResponse> getAllByOrgId(int pageNo, int pageSize, String sortBy, Long orgId);
    ResponseEntity<AppointmentResponse> getAllByOrgAndUserId(int pageNo, int pageSize, String sortBy, Long orgId, Long userId);
    ResponseEntity<AppointmentResponse> getAllByOrgAndOrgDrId(int pageNo, int pageSize, String sortBy, Long orgId, Long orgDrId);

}
