package billing.service;

import billing.dto.DoctorDto;
import billing.dto.OrgDoctorDto;
import billing.entity.OrgDoctor;
import billing.pageResponse.OrgDoctorResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OrgDoctorService {
    OrgDoctorDto add(Long appUserId, Long orgId, Long doctorId, OrgDoctorDto orgDoctorDto);
    OrgDoctorDto getById(Long id);
    OrgDoctorResponse getAll(int pageNo, int pageSize, String sortBy);
    OrgDoctorResponse getAllByAppUserOrgId(int pageNo, int pageSize, String sortBy, Long appUserId, Long orgId);
    OrgDoctorResponse getAllByOrgId(int pageNo, int pageSize, String sortBy, Long orgId);
    OrgDoctorDto updateById(OrgDoctorDto orgDoctorDto, Long id);
    OrgDoctorResponse searchOrgDoctor(int pageNo, int pageSize, String sortBy, Long orgId, String query);

}
