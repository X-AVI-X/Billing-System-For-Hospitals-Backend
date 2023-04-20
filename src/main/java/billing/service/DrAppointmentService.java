package billing.service;

import billing.dto.DrAppointmentBillDto;
import billing.pageResponse.AppointmentResponse;

import java.util.List;

public interface DrAppointmentService {

    DrAppointmentBillDto add(Long appUserId, Long orgDrId, Long patientId, Long orgId, DrAppointmentBillDto drAppointmentBillDto);
    DrAppointmentBillDto getById(Long apptId);
    AppointmentResponse getAll(int pageNo, int pageSize, String sortBy);
    AppointmentResponse getAllByOrgAndUserId(int pageNo, int pageSize, String sortBy, Long orgId, Long userId);
    AppointmentResponse getAllByOrgId(int pageNo, int pageSize, String sortBy, Long orgId);
    AppointmentResponse getAllByOrgAndOrgDr(int pageNo, int pageSize, String sortBy, Long orgId, Long orgDrId);
}
