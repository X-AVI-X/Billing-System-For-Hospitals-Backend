package billing.controller;

import billing.dto.DoctorDto;
import billing.pageResponse.DoctorResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DoctorController {
    ResponseEntity<DoctorDto> saveByAdmin(Long appUserId, DoctorDto doctorDto);
    ResponseEntity<DoctorDto> getById(Long id);
    ResponseEntity<DoctorResponse> getAll(int pageNo, int pageSize, String sortBy);
    ResponseEntity<DoctorResponse> getAllByAppUserId(int pageNo, int pageSize, String sortBy, Long appUserId);
    ResponseEntity<DoctorDto> update(DoctorDto doctorDto, Long id);
    ResponseEntity<DoctorResponse> searchDoctor(int pageNo, int pageSize, String sortBy, String query);
}
