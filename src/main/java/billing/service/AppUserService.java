package billing.service;

import billing.dto.AppUserDto;
import billing.pageResponse.AppUserResponse;
import billing.pageResponse.OrgDoctorResponse;

import java.util.List;
public interface AppUserService {
    AppUserDto add(Long orgId, AppUserDto appUserDto);
    AppUserDto getAppUserById(Long id);
    AppUserResponse getAllAppUser(int pageNo, int pageSize, String sortBy);
    AppUserResponse getAllByOrgId(int pageNo, int pageSize, String sortBy, Long orgId);
    AppUserDto updateAppUserById(AppUserDto instructorDto, Long id);
    void deleteAppUserById(Long id);
    AppUserResponse searchEmployee(int pageNo, int pageSize, String sortBy, String query);
    AppUserResponse searchOrgEmployee(int pageNo, int pageSize, String sortBy, Long orgId, String query);
}
