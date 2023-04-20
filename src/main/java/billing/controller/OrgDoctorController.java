package billing.controller;

import billing.dto.DoctorDto;
import billing.dto.OrgDoctorDto;
import billing.entity.OrgDoctor;
import billing.pageResponse.OrgDoctorResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OrgDoctorController {
    ResponseEntity<OrgDoctorDto> add(Long appUserId, Long orgId, Long doctorId, OrgDoctorDto orgDoctorDto);
    ResponseEntity<OrgDoctorDto> getById(Long id);
    ResponseEntity<OrgDoctorResponse> getAll(int pageNo, int pageSize, String sortBy);
    ResponseEntity<OrgDoctorResponse> getAllByOrgId(int pageNo, int pageSize, String sortBy, Long orgId);
    ResponseEntity<OrgDoctorResponse> getAllByAppUserAndOrgId(int pageNo, int pageSize, String sortBy, Long appUserId, Long orgId);
    ResponseEntity<OrgDoctorDto> update(OrgDoctorDto orgDoctorDto, Long id);
    ResponseEntity<OrgDoctorResponse> searchOrgDoctor(int pageNo, int pageSize, String sortBy, Long orgId, String query);
}
