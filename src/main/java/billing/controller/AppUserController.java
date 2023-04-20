package billing.controller;

import billing.dto.AppUserDto;
import billing.dto.DoctorDto;
import billing.pageResponse.AppUserResponse;
import billing.pageResponse.OrgDoctorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface AppUserController {

    ResponseEntity<AppUserDto> add(Long orgId, AppUserDto appUserDto);
    ResponseEntity<AppUserResponse> getAll(int pageNo, int pageSize, String sortBy);
    ResponseEntity<AppUserDto> getById(Long id);
    ResponseEntity<AppUserResponse> getAllByOrgId(int pageNo, int pageSize, String sortBy, Long orgId);
    ResponseEntity<AppUserDto> update(AppUserDto instructorDto, Long id);
    ResponseEntity<String> delete(Long id);
    ResponseEntity<AppUserResponse> searchEmployee(int pageNo, int pageSize, String sortBy, String query);
    ResponseEntity<AppUserResponse> searchOrgEmployee(int pageNo, int pageSize, String sortBy, Long orgId, String query);
}
