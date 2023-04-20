package billing.service.impl;

import billing.dto.DrAppointmentBillDto;
import billing.entity.*;
import billing.exceptionHandling.ResourceNotFound;
import billing.pageResponse.AppointmentResponse;
import billing.repository.*;
import billing.service.DrAppointmentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DrAppointmentServiceImpl implements DrAppointmentService {

    private final DrAppointmentRepository drAppointmentRepository;
    private final OrgDoctorRepository orgDoctorRepository;
    private final AppUserRepository appUserRepository;
    private final PatientRepository patientRepository;
    private final OrganizationRepository organizationRepository;
    private ModelMapper mapper;
    @Override
    public DrAppointmentBillDto add(Long appUserId,
                                    Long orgDrId,
                                    Long patientId,
                                    Long orgId,
                                    DrAppointmentBillDto drAppointmentBillDto) {

        DrAppointmentBill appointmentBill = mapToEntity(drAppointmentBillDto);

        AppUser appUser = appUserRepository.findById(appUserId).orElseThrow(()->
                new ResourceNotFound("AppUser", "id",appUserId));

        OrgDoctor orgDoctor = orgDoctorRepository.findById(orgDrId).orElseThrow(()->
                new ResourceNotFound("OrgDoctor", "id", orgDrId));

        Patient patient = patientRepository.findById(patientId).orElseThrow(()->
                new ResourceNotFound("Patient", "id", patientId));

        Organization organization = organizationRepository.findById(orgId).orElseThrow(()->
                new ResourceNotFound("Organization", "id",orgId));

        appointmentBill.setCreatedBy(appUser);
        appointmentBill.setOrgDoctor(orgDoctor);
        appointmentBill.setPatient(patient);
        appointmentBill.setOrganization(organization);

        DrAppointmentBill bill = drAppointmentRepository.save(appointmentBill);

        return mapToDto(bill);
    }

    @Override
    public DrAppointmentBillDto getById(Long apptId) {

        DrAppointmentBill appointmentBill = drAppointmentRepository.findById(apptId).orElseThrow(()->
                new ResourceNotFound("appointment", "id", apptId));
        return mapToDto(appointmentBill);
    }

    @Override
    public AppointmentResponse getAll(int pageNo, int pageSize, String sortBy) {

        Pageable pageable = PageRequest.of(pageNo,pageSize, Sort.by(sortBy));
        Page<DrAppointmentBill> appointmentBills = drAppointmentRepository.findAll(pageable);
        List<DrAppointmentBill> bills = appointmentBills.getContent();
        List<DrAppointmentBillDto> content = bills.stream().map(bill-> mapToDto(bill)).collect(Collectors.toList());

        AppointmentResponse appointmentResponse = new AppointmentResponse();
        appointmentResponse.setContent(content);
        appointmentResponse.setPageNo(appointmentBills.getNumber());
        appointmentResponse.setPageSize(appointmentBills.getSize());
        appointmentResponse.setTotalElements(appointmentBills.getTotalElements());
        appointmentResponse.setTotalPages(appointmentBills.getTotalPages());
        appointmentResponse.setLast(appointmentBills.isLast());

        return appointmentResponse;
    }

    @Override
    public AppointmentResponse getAllByOrgId(int pageNo,
                                             int pageSize,
                                             String sortBy,
                                             Long orgId) {

        Pageable pageable = PageRequest.of(pageNo,pageSize, Sort.by(sortBy));
        Page<DrAppointmentBill> appointmentBills = drAppointmentRepository.findAllDrAppointmentBillByOrganizationId(pageable,orgId);
        List<DrAppointmentBill> bills = appointmentBills.getContent();
        List<DrAppointmentBillDto> content = bills.stream().map(bill-> mapToDto(bill)).collect(Collectors.toList());

        AppointmentResponse appointmentResponse = new AppointmentResponse();
        appointmentResponse.setContent(content);
        appointmentResponse.setPageNo(appointmentBills.getNumber());
        appointmentResponse.setPageSize(appointmentBills.getSize());
        appointmentResponse.setTotalElements(appointmentBills.getTotalElements());
        appointmentResponse.setTotalPages(appointmentBills.getTotalPages());
        appointmentResponse.setLast(appointmentBills.isLast());

        return appointmentResponse;
    }

    @Override
    public AppointmentResponse getAllByOrgAndUserId(int pageNo,
                                                           int pageSize,
                                                           String sortBy,
                                                           Long orgId,
                                                           Long userId) {

        Pageable pageable = PageRequest.of(pageNo,pageSize,Sort.by(sortBy));
        Page<DrAppointmentBill> appointmentBills = drAppointmentRepository.findAllDrAppointmentBillByOrganizationIdAndCreatedById(pageable,orgId,userId);
        List<DrAppointmentBill> bills = appointmentBills.getContent();
        List<DrAppointmentBillDto> content = bills.stream().map(bill-> mapToDto(bill)).collect(Collectors.toList());

        AppointmentResponse appointmentResponse = new AppointmentResponse();
        appointmentResponse.setContent(content);
        appointmentResponse.setPageNo(appointmentBills.getNumber());
        appointmentResponse.setPageSize(appointmentBills.getSize());
        appointmentResponse.setTotalElements(appointmentBills.getTotalElements());
        appointmentResponse.setTotalPages(appointmentBills.getTotalPages());
        appointmentResponse.setLast(appointmentBills.isLast());

        return appointmentResponse;
    }

    @Override
    public AppointmentResponse getAllByOrgAndOrgDr(int pageNo,
                                                          int pageSize,
                                                          String sortBy,
                                                          Long orgId,
                                                          Long orgDoctorId) {

        Pageable pageable = PageRequest.of(pageNo,pageSize,Sort.by(sortBy));
        Page<DrAppointmentBill> appointmentBills = drAppointmentRepository.findAllByOrganizationIdAndOrgDoctorId(pageable,orgId,orgDoctorId);
        List<DrAppointmentBill> bills = appointmentBills.getContent();
        List<DrAppointmentBillDto> content = bills.stream().map(bill-> mapToDto(bill)).collect(Collectors.toList());

        AppointmentResponse appointmentResponse = new AppointmentResponse();
        appointmentResponse.setContent(content);
        appointmentResponse.setPageNo(appointmentBills.getNumber());
        appointmentResponse.setPageSize(appointmentBills.getSize());
        appointmentResponse.setTotalElements(appointmentBills.getTotalElements());
        appointmentResponse.setTotalPages(appointmentBills.getTotalPages());
        appointmentResponse.setLast(appointmentBills.isLast());

        return appointmentResponse;
    }

    public DrAppointmentBill mapToEntity(DrAppointmentBillDto drAppointmentBillDto) {

        DrAppointmentBill appointmentBill = mapper.map(drAppointmentBillDto,DrAppointmentBill.class);
        return appointmentBill;
    }

    public DrAppointmentBillDto mapToDto(DrAppointmentBill drAppointmentBill) {

        DrAppointmentBillDto apointmentBillDto = mapper.map(drAppointmentBill, DrAppointmentBillDto.class);
        return apointmentBillDto;
    }
}
