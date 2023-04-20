package billing.service.impl;

import billing.dto.OrgMedicineDto;
import billing.entity.OrgMedicine;
import billing.exceptionHandling.ResourceNotFound;
import billing.projection.OrgMedicineFinalProjection;
import billing.projection.OrgMedicineProjection;
import billing.repository.MedicineRepository;
import billing.repository.OrgMedicineRepository;
import billing.repository.OrganizationRepository;
import billing.service.OrgMedicineService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrgMedicineServiceImpl implements OrgMedicineService {

    private final OrgMedicineRepository orgMedicineRepository;
    private final MedicineRepository medicineRepository;
    private final OrganizationRepository organizationRepository;
    private final ModelMapper modelMapper;

    public OrgMedicineServiceImpl(OrgMedicineRepository orgMedicineRepository, MedicineRepository medicineRepository, OrganizationRepository organizationRepository, ModelMapper modelMapper) {
        this.orgMedicineRepository = orgMedicineRepository;
        this.medicineRepository = medicineRepository;
        this.organizationRepository = organizationRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public OrgMedicineDto add(Long orgId, Long medicineId) {
        OrgMedicine newOrgMedicine = new OrgMedicine();
        newOrgMedicine.setMedicine(medicineRepository.findById(medicineId).orElseThrow(()
                ->new ResourceNotFound("Medicine id  #"+ medicineId+ " doesn't exist.")));
        newOrgMedicine.setOrganization(organizationRepository.findById(orgId).orElseThrow(()
                ->new ResourceNotFound("Organization id  #"+ orgId+ " doesn't exist.")));
        return modelMapper.map(orgMedicineRepository.save(newOrgMedicine), OrgMedicineDto.class);
    }

    @Override
    public List<OrgMedicineProjection> addAll(List<OrgMedicineDto> orgMedicineDtoList) throws Exception {
        List<OrgMedicineProjection> orgMedicineProjectionList = new ArrayList<>();
        for (OrgMedicineDto orgMedicineDto: orgMedicineDtoList) {
            if (!orgMedicineRepository.existsByMedicineIdAndOrganizationId(orgMedicineDto.getMedicineId(),
                    orgMedicineDto.getOrganizationId())) {
                OrgMedicine orgMedicine = new OrgMedicine(
                        null,
                        organizationRepository.findById(orgMedicineDto.getOrganizationId()).orElseThrow(()
                                -> new ResourceNotFound("Organization id doesn't exist.")),
                        medicineRepository.findById(orgMedicineDto.getMedicineId()).orElseThrow(()
                                -> new ResourceNotFound("Medicine id doesn't exists."))
                );
                OrgMedicine savedOrgMedicine = orgMedicineRepository.save(orgMedicine);
                orgMedicineProjectionList.add(new OrgMedicineProjection(
                        savedOrgMedicine.getId(),
                        savedOrgMedicine.getMedicine()
                ));
            }
            else throw new Exception("Medicine  #"+ orgMedicineDto.getMedicineId()+ " already exists in organization");
        }
        return orgMedicineProjectionList;
    }

    @Override
    public OrgMedicineDto get(Long id) {
        return modelMapper.map(orgMedicineRepository.findById(id).orElseThrow(()-> new ResourceNotFound("Org-medicine id  #"+ id+ " doesn't exist.")), OrgMedicineDto.class);
    }

    @Override
    public OrgMedicineFinalProjection getAll(Long orgId, short pageNo, byte size, String sortBy) {
        Pageable pageable = PageRequest.of(pageNo, size, Sort.by(sortBy));
        Page<OrgMedicine> orgMedicines = orgMedicineRepository.findAllByOrganizationId(orgId,pageable);
        List<OrgMedicineProjection> orgMedicineProjectionList = new ArrayList<>();
        for (OrgMedicine orgMedicine: orgMedicines.getContent()){
            orgMedicineProjectionList.add(new OrgMedicineProjection(
                    orgMedicine.getId(),
                    orgMedicine.getMedicine()
            ));
        }
        return new OrgMedicineFinalProjection((short) orgMedicines.getTotalElements(),orgMedicineProjectionList);
    }

    @Override
    public String delete(Long id) {
        if (orgMedicineRepository.existsById(id)) {
            orgMedicineRepository.deleteById(id);
            return "Org-medicine #" + id + " deleted";
        }
        else return "Org-medicine #" + id + " doesn't exist.";
    }

    @Override
    public Page<OrgMedicineProjection> search(Long orgId, String query, short page, String sortBy, byte size) {
        Pageable pageable = PageRequest.of(page,size, Sort.by(sortBy));
        return orgMedicineRepository.search(orgId, query, pageable);
    }
}
