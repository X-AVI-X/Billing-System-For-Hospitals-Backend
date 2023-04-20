package billing.service.impl;

import billing.dto.OrganizationDto;
import billing.entity.Organization;
import billing.exceptionHandling.ResourceNotFound;
import billing.pageResponse.OrganizationResponse;
import billing.repository.OrganizationRepository;
import billing.service.OrganizationService;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    private OrganizationRepository organizationRepository;

    @Override
    public OrganizationDto add(OrganizationDto organizationDto) {

        Organization organization = mapToEntity(organizationDto);
        Organization newOrganization = organizationRepository.save(organization);

        return mapToDto(newOrganization);
    }

    @Override
    public OrganizationDto getOrganizationById(Long id) {
        Organization organization = organizationRepository.findById(id).orElseThrow(()->
                new ResourceNotFound("organization", "id", id ));
        return mapToDto(organization);
    }

    @Override
    public OrganizationResponse getAll(int pageNo,
                                       int pageSize,
                                       String sortBy) {

        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Organization> organizations = organizationRepository.findAll(pageable);

        List<Organization> organizationList = organizations.getContent();
        List<OrganizationDto> content = organizationList.stream().map(organization -> mapToDto(organization)).collect(Collectors.toList());

        OrganizationResponse organizationResponse = new OrganizationResponse();
        organizationResponse.setContent(content);
        organizationResponse.setPageNo(organizations.getNumber());
        organizationResponse.setPageSize(organizations.getSize());
        organizationResponse.setTotalElements(organizations.getTotalElements());
        organizationResponse.setTotalPages(organizations.getTotalPages());
        organizationResponse.setLast(organizations.isLast());

        return organizationResponse;
    }

    @Override
    public OrganizationDto updateById(OrganizationDto organizationDto, Long id) {

        Organization organization = organizationRepository.findById(id).orElseThrow(()->
                new ResourceNotFound("Organization","id",id));

        organization.setName(organizationDto.getName());
        organization.setAddress(organizationDto.getAddress());
        organization.setPhone(organizationDto.getPhone());
        organization.setEmail(organizationDto.getEmail());
        organization.setWebsite(organizationDto.getWebsite());

        Organization updateOrg = organizationRepository.save(organization);

        OrganizationDto responseDto = mapToDto(updateOrg);

        return responseDto;
    }

    @Override
    public void deleteById(Long id) {

        Organization organization = organizationRepository.findById(id).orElseThrow(()-> new
                ConfigDataResourceNotFoundException(null));
        organizationRepository.delete(organization);

    }
    @Override
    public OrganizationResponse searchOrganization(int pageNo,
                                                   int pageSize,
                                                   String sortBy,
                                                   String query) {

        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Organization> organizations = organizationRepository.searchOrganization(pageable, query);
        List<Organization> organizationList = organizations.getContent();

        List<OrganizationDto> content = organizationList.stream().map(org-> mapToDto(org)).collect(Collectors.toList());

        OrganizationResponse organizationResponse = new OrganizationResponse();
        organizationResponse.setContent(content);
        organizationResponse.setPageNo(organizations.getNumber());
        organizationResponse.setPageSize(organizations.getSize());
        organizationResponse.setTotalElements(organizations.getTotalElements());
        organizationResponse.setTotalPages(organizations.getTotalPages());
        organizationResponse.setLast(organizations.isLast());

        return organizationResponse;
    }

    public Organization mapToEntity(OrganizationDto organizationDto) {

        Organization organization = new Organization();
        organization.setName(organizationDto.getName());
        organization.setAddress(organizationDto.getAddress());
        organization.setPhone(organizationDto.getPhone());
        organization.setEmail(organizationDto.getEmail());
        organization.setWebsite(organizationDto.getWebsite());

        return organization;
    }

    public OrganizationDto mapToDto(Organization organization) {

        OrganizationDto newOrganization = new OrganizationDto();
        newOrganization.setId(organization.getId());
        newOrganization.setName(organization.getName());
        newOrganization.setAddress(organization.getAddress());
        newOrganization.setPhone(organization.getPhone());
        newOrganization.setEmail(organization.getEmail());
        newOrganization.setWebsite(organization.getWebsite());

        return newOrganization;
    }
}
