package billing.service.impl;

import billing.dto.PharmacyBillDto;
import billing.entity.*;
import billing.exceptionHandling.ResourceNotFound;
import billing.pageResponse.PharmacyBillResponse;
import billing.repository.*;
import billing.service.PharmacyBillService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PharmacyBillServiceImpl implements PharmacyBillService {

    private final PharmacyBillRepository pharmacyBillRepository;
    private final MedicineRepository medicineRepository;
    private final AppUserRepository appUserRepository;
    private final PatientRepository patientRepository;
    private final OrganizationRepository organizationRepository;
    private ModelMapper mapper;
    @Override
    public PharmacyBillDto add(List<Long> drugIds,
                            Long patientId,
                            Long orgId,
                            Long appUserId,
                            PharmacyBillDto pharmacyBillDto) {

        PharmacyBill pharmacyBill = mapToEntity(pharmacyBillDto);

        List<Medicine> medicines = medicineRepository.findAllById(drugIds);

        Patient patient = patientRepository.findById(patientId).orElseThrow(()->
                new ResourceNotFound("Patient", "id", patientId));

        Organization organization = organizationRepository.findById(orgId).orElseThrow(()->
                new ResourceNotFound("Organization", "id",orgId));

        AppUser appUser = appUserRepository.findById(appUserId).orElseThrow(()->
                new ResourceNotFound("AppUser", "id",appUserId));

        pharmacyBill.setMedicines(medicines);
        pharmacyBill.setPatient(patient);
        pharmacyBill.setOrganization(organization);
        pharmacyBill.setCreatedBy(appUser);
        PharmacyBill bill = pharmacyBillRepository.save(pharmacyBill);

        return mapToDto(bill);
    }

    @Override
    public PharmacyBillDto getById(Long id) {

        PharmacyBill pharmacyBill = pharmacyBillRepository.findById(id).orElseThrow(()->
                new ResourceNotFound("Pharmacy bill", "id", id));
        return mapToDto(pharmacyBill);
    }

    @Override
    public PharmacyBillResponse getAll(int pageNo, int pageSize, String sortBy) {

        Pageable pageable = PageRequest.of(pageNo,pageSize, Sort.by(sortBy));
        Page<PharmacyBill> pharmacyBills = pharmacyBillRepository.findAll(pageable);
        List<PharmacyBill> bills = pharmacyBills.getContent();
        List<PharmacyBillDto> content = bills.stream().map(bill-> mapToDto(bill)).collect(Collectors.toList());

        PharmacyBillResponse pharmacyBillResponse = new PharmacyBillResponse();
        pharmacyBillResponse.setContent(content);
        pharmacyBillResponse.setPageNo(pharmacyBills.getNumber());
        pharmacyBillResponse.setPageSize(pharmacyBills.getSize());
        pharmacyBillResponse.setTotalElements(pharmacyBills.getTotalElements());
        pharmacyBillResponse.setTotalPages(pharmacyBills.getTotalPages());
        pharmacyBillResponse.setLast(pharmacyBills.isLast());

        return pharmacyBillResponse;
    }

    @Override
    public PharmacyBillResponse getAllByOrgId(int pageNo,
                                              int pageSize,
                                              String sortBy,
                                              Long orgId) {

        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<PharmacyBill> pharmacyBills = pharmacyBillRepository.findAllByOrganizationId(pageable,orgId);

        List<PharmacyBill> bills = pharmacyBills.getContent();
        List<PharmacyBillDto> content = bills.stream().map((bill)->mapToDto(bill)).collect(Collectors.toList());

        PharmacyBillResponse pharmacyBillResponse = new PharmacyBillResponse();
        pharmacyBillResponse.setContent(content);
        pharmacyBillResponse.setPageNo(pharmacyBills.getNumber());
        pharmacyBillResponse.setPageSize(pharmacyBills.getSize());
        pharmacyBillResponse.setTotalElements(pharmacyBills.getTotalElements());
        pharmacyBillResponse.setTotalPages(pharmacyBills.getTotalPages());
        pharmacyBillResponse.setLast(pharmacyBills.isLast());

        return pharmacyBillResponse;
    }

    @Override
    public PharmacyBillResponse getAllByOrgAndUserId(int pageNo,
                                                     int pageSize,
                                                     String sortBy,
                                                     Long orgId,
                                                     Long userId) {

        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<PharmacyBill> pharmacyBills = pharmacyBillRepository.findAllByOrganizationIdAndCreatedById(pageable,orgId,userId);

        List<PharmacyBill> bills = pharmacyBills.getContent();
        List<PharmacyBillDto> content = bills.stream().map((bill)->mapToDto(bill)).collect(Collectors.toList());

        PharmacyBillResponse pharmacyBillResponse = new PharmacyBillResponse();
        pharmacyBillResponse.setContent(content);
        pharmacyBillResponse.setPageNo(pharmacyBills.getNumber());
        pharmacyBillResponse.setPageSize(pharmacyBills.getSize());
        pharmacyBillResponse.setTotalElements(pharmacyBills.getTotalElements());
        pharmacyBillResponse.setTotalPages(pharmacyBills.getTotalPages());
        pharmacyBillResponse.setLast(pharmacyBills.isLast());

        return pharmacyBillResponse;
    }

    public PharmacyBill mapToEntity(PharmacyBillDto pharmacyBillDto) {

        PharmacyBill pharmacyBill = mapper.map(pharmacyBillDto,PharmacyBill.class);
        return pharmacyBill;
    }

    public PharmacyBillDto mapToDto(PharmacyBill pharmacyBill) {

        PharmacyBillDto pharmacyBillDto = mapper.map(pharmacyBill, PharmacyBillDto.class);
        return pharmacyBillDto;
    }
}
