package billing.service.impl;

import billing.dto.OrgDiagnosticDto;
import billing.entity.OrgDiagnostic;
import billing.exceptionHandling.ResourceNotFound;
import billing.projection.OrgDiagnosticProjection;
import billing.repository.DiagnosticRepository;
import billing.repository.OrgDiagnosticRepository;
import billing.repository.OrganizationRepository;
import billing.service.OrgDiagnosticService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrgDiagnosticServiceImpl implements OrgDiagnosticService {
    private final OrgDiagnosticRepository orgDiagnosticRepository;
    private final DiagnosticRepository diagnosticRepository;
    private final OrganizationRepository organizationRepository;
    private final ModelMapper modelMapper;

    public OrgDiagnosticServiceImpl(OrgDiagnosticRepository orgDiagnosticRepository, DiagnosticRepository diagnosticRepository, OrganizationRepository organizationRepository, ModelMapper modelMapper) {
        this.orgDiagnosticRepository = orgDiagnosticRepository;
        this.diagnosticRepository = diagnosticRepository;
        this.organizationRepository = organizationRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public OrgDiagnosticDto add(Long orgId, Long diagnosticId) {
        OrgDiagnostic newOrgDiagnostic = new OrgDiagnostic();
        newOrgDiagnostic.setDiagnostic(diagnosticRepository.findById(diagnosticId).orElseThrow(()->new ResourceNotFound("Diagnostic id  #"+ diagnosticId+ " doesn't exist.")));
        newOrgDiagnostic.setOrganization(organizationRepository.findById(orgId).orElseThrow(()->new ResourceNotFound("Organization* id  #"+ orgId+ " doesn't exist.")));
        return modelMapper.map(orgDiagnosticRepository.save(newOrgDiagnostic), OrgDiagnosticDto.class);
    }

    public List<OrgDiagnosticProjection> addAll (List <OrgDiagnosticDto> orgDiagnosticDtoList) throws Exception {

        List<OrgDiagnosticProjection> orgDiagnosticProjectionList = new ArrayList<>();

        for (OrgDiagnosticDto orgDiagnosticDto : orgDiagnosticDtoList) {
            if (!orgDiagnosticRepository.existsByDiagnosticIdAndOrganizationId(orgDiagnosticDto.getDiagnosticId(),
                                                                                orgDiagnosticDto.getOrganizationId())) {
                double price = orgDiagnosticDto.getPrice();
                Long organizationId = orgDiagnosticDto.getOrganizationId();
                Long diagnosticId = orgDiagnosticDto.getDiagnosticId();

                OrgDiagnostic newOrgDiagnostic = new OrgDiagnostic();

                newOrgDiagnostic.setDiagnostic(diagnosticRepository.findById(diagnosticId).orElseThrow(() -> new ResourceNotFound("Diagnostic Id  #" + diagnosticId + " is invalid.")));
                newOrgDiagnostic.setOrganization(organizationRepository.findById(organizationId).orElseThrow(() -> new ResourceNotFound("Organization Id  #" + organizationId + " is invalid.")));
                newOrgDiagnostic.setPrice(price);

                OrgDiagnostic savedOrgDiagnostic = orgDiagnosticRepository.save(newOrgDiagnostic);

                orgDiagnosticProjectionList.add(new OrgDiagnosticProjection(
                        savedOrgDiagnostic.getId(),
                        savedOrgDiagnostic.getPrice(),
                        savedOrgDiagnostic.getOrganization().getId(),
                        savedOrgDiagnostic.getDiagnostic().getServiceName()
                ));
            } else throw new Exception("Diagnostic service #"+ orgDiagnosticDto.getDiagnosticId()+ " already exists in organization");
        }
        return orgDiagnosticProjectionList;
    }

    @Override
    public OrgDiagnosticDto get(Long id) {
        return modelMapper.map(orgDiagnosticRepository.findById(id).orElseThrow(()-> new ResourceNotFound("Organization Diagnostic Id #"+ id+ " is invalid")), OrgDiagnosticDto.class);
    }

    @Override
    public List<OrgDiagnosticProjection> getAll(Long orgId) {
        List<OrgDiagnostic> orgDiagnostics = orgDiagnosticRepository.findAllByOrganizationId(orgId);
        List<OrgDiagnosticProjection> orgDiagnosticProjectionList = new ArrayList<>();

        for (OrgDiagnostic orgDiagnostic : orgDiagnostics){

            orgDiagnosticProjectionList.add(new OrgDiagnosticProjection(
                    orgDiagnostic.getId(),
                    orgDiagnostic.getPrice(),
                    orgDiagnostic.getOrganization().getId(),
                    orgDiagnostic.getDiagnostic().getServiceName()
            ));
        }
        return orgDiagnosticProjectionList;
    }

    @Override
    public String delete(Long id) {
        if (orgDiagnosticRepository.existsById(id)) {
            orgDiagnosticRepository.deleteById(id);
            return "Org-diagnostic #" + id + " deleted";
        }
        else return "Org-diagnostic #" + id + " doesn't exist.";
    }

    @Override
    public OrgDiagnosticProjection update(OrgDiagnosticDto orgDiagnosticDto) {
        OrgDiagnostic orgDiagnostic = orgDiagnosticRepository.findById(orgDiagnosticDto.getId()).orElseThrow(() -> new ResourceNotFound("Organization Diagnostic Id #"+ orgDiagnosticDto.getDiagnosticId()+ " is invalid"));
        orgDiagnostic.setPrice(orgDiagnosticDto.getPrice());
        OrgDiagnostic savedOrgDiagnostic = orgDiagnosticRepository.save(orgDiagnostic);
        return new OrgDiagnosticProjection(
                savedOrgDiagnostic.getId(),
                savedOrgDiagnostic.getPrice(),
                savedOrgDiagnostic.getId(),
                savedOrgDiagnostic.getDiagnostic().getServiceName()
        );
    }

    @Override
    public Page<OrgDiagnosticProjection> search(Long orgId, String query, short page, String sortBy, byte size) {
        Pageable pageable = PageRequest.of(page,size, Sort.by(sortBy));
        return orgDiagnosticRepository.search(orgId, query, pageable);
    }
}
