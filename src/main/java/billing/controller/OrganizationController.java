package billing.controller;

import billing.dto.OrganizationDto;
import billing.pageResponse.OrganizationResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OrganizationController{

    ResponseEntity<OrganizationDto> add(OrganizationDto organizationDto);
    ResponseEntity<OrganizationDto> getById(Long id);
    ResponseEntity<OrganizationResponse> getAll(int pageNo, int pageSize, String sortBy);
    ResponseEntity<OrganizationDto> update(OrganizationDto organizationDto, Long id);
    ResponseEntity<String> delete(Long id);
    ResponseEntity<OrganizationResponse> searchOrganization(int pageNo, int pageSize, String sortBy, String query);
}
