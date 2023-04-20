package billing.service.impl;

import billing.dto.DoctorDto;
import billing.dto.OrgDoctorDto;
import billing.entity.AppUser;
import billing.entity.Doctor;
import billing.entity.OrgDoctor;
import billing.entity.Organization;
import billing.exceptionHandling.ResourceNotFound;
import billing.pageResponse.OrgDoctorResponse;
import billing.repository.AppUserRepository;
import billing.repository.DoctorRepository;
import billing.repository.OrgDoctorRepository;
import billing.repository.OrganizationRepository;
import billing.service.OrgDoctorService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrgDoctorServiceImpl implements OrgDoctorService {

    private final OrgDoctorRepository orgDoctorRepository;
    private final AppUserRepository appUserRepository;
    private final OrganizationRepository organizationRepository;
    private final DoctorRepository doctorRepository;
    private ModelMapper mapper;

    public OrgDoctorServiceImpl(OrgDoctorRepository orgDoctorRepository, AppUserRepository appUserRepository, OrganizationRepository organizationRepository, DoctorRepository doctorRepository, ModelMapper mapper) {
        this.orgDoctorRepository = orgDoctorRepository;
        this.appUserRepository = appUserRepository;
        this.organizationRepository = organizationRepository;
        this.doctorRepository = doctorRepository;
        this.mapper = mapper;
    }

    @Override
    public OrgDoctorDto add(Long appUserId,
                            Long orgId,
                            Long doctorId,
                            OrgDoctorDto orgDoctorDto) {

        OrgDoctor orgDoctor = mapToEntity(orgDoctorDto);

        Organization organization = organizationRepository.findById(orgId).orElseThrow(()->
                new ResourceNotFound("Organization","id",orgId));
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(()->
                new ResourceNotFound("Doctor","id",doctorId));
        AppUser appUser = appUserRepository.findById(appUserId).orElseThrow(()->
                new ResourceNotFound("App user","id",appUserId));
        orgDoctor.setAppUser(appUser);
        orgDoctor.setOrganization(organization);
        orgDoctor.setDoctor(doctor);
        OrgDoctor newOrgDoctor = orgDoctorRepository.save(orgDoctor);

        return mapToDto(newOrgDoctor);
    }

    @Override
    public OrgDoctorDto getById(Long id) {
        OrgDoctor orgDoctor = orgDoctorRepository.findById(id).orElseThrow(()->
                new ResourceNotFound("organization id not found"));
        return mapToDto(orgDoctor);
    }

    @Override
    public OrgDoctorResponse getAll(int pageNo, int pageSize, String sortBy) {

        Pageable pageable = PageRequest.of(pageNo,pageSize, Sort.by(sortBy));
        Page<OrgDoctor> orgDoctors = orgDoctorRepository.findAll(pageable);

        List<OrgDoctor> listOfOrgDoctors = orgDoctors.getContent();
        List<OrgDoctorDto> content = listOfOrgDoctors.stream().map(orgDoctor -> mapToDto(orgDoctor)).collect(Collectors.toList());

        OrgDoctorResponse orgDoctorResponse = new OrgDoctorResponse();
        orgDoctorResponse.setContent(content);
        orgDoctorResponse.setPageNo(orgDoctors.getNumber());
        orgDoctorResponse.setPageSize(orgDoctors.getSize());
        orgDoctorResponse.setTotalElements(orgDoctors.getTotalElements());
        orgDoctorResponse.setTotalPages(orgDoctors.getTotalPages());
        orgDoctorResponse.setLast(orgDoctors.isLast());

        return orgDoctorResponse;
    }

    @Override
    public OrgDoctorResponse getAllByAppUserOrgId(int pageNo,
                                                   int pageSize,
                                                   String sortBy,
                                                   Long appUserId,
                                                   Long orgId) {

        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<OrgDoctor> orgDoctors = orgDoctorRepository.findAllOrgDoctorByOrganizationIdAndAppUserId(pageable, appUserId, orgId);
        List<OrgDoctor> orgDoctorList = orgDoctors.getContent();
        List<OrgDoctorDto> content = orgDoctorList.stream().map(doctor-> mapToDto(doctor)).collect(Collectors.toList());

        OrgDoctorResponse orgDoctorResponse = new OrgDoctorResponse();
        orgDoctorResponse.setContent(content);
        orgDoctorResponse.setPageNo(orgDoctors.getNumber());
        orgDoctorResponse.setPageSize(orgDoctors.getSize());
        orgDoctorResponse.setTotalElements(orgDoctors.getTotalElements());
        orgDoctorResponse.setTotalPages(orgDoctors.getTotalPages());
        orgDoctorResponse.setLast(orgDoctors.isLast());

        return orgDoctorResponse;
    }

    @Override
    public OrgDoctorResponse getAllByOrgId(int pageNo,
                                         int pageSize,
                                         String sortBy, Long orgId) {

        Pageable pageable = PageRequest.of(pageNo, pageSize , Sort.by(sortBy));
        Page<OrgDoctor> orgDoctors = orgDoctorRepository.findAllOrgDoctorByOrganizationId(pageable, orgId);

        List<OrgDoctor> orgDoctorList = orgDoctors.getContent();
        List<OrgDoctorDto> content = orgDoctorList.stream().map(doctor-> mapToDto(doctor)).collect(Collectors.toList());

        OrgDoctorResponse orgDoctorResponse = new OrgDoctorResponse();
        orgDoctorResponse.setContent(content);
        orgDoctorResponse.setPageNo(orgDoctors.getNumber());
        orgDoctorResponse.setPageSize(orgDoctors.getSize());
        orgDoctorResponse.setTotalElements(orgDoctors.getTotalElements());
        orgDoctorResponse.setTotalPages(orgDoctors.getTotalPages());
        orgDoctorResponse.setLast(orgDoctors.isLast());

        return orgDoctorResponse;
    }

    @Override
    public OrgDoctorDto updateById(OrgDoctorDto orgDoctorDto, Long id) {
        OrgDoctor orgDoctor = orgDoctorRepository.findById(id).orElseThrow(()-> new ResourceNotFound("OrgDoctor","id",id));

        orgDoctor.setConsultationFee(orgDoctorDto.getConsultationFee());
        orgDoctor.setFollowupFee(orgDoctorDto.getFollowupFee());
        orgDoctor.setReportFee(orgDoctorDto.getReportFee());
        orgDoctor.setAvailableTimes(orgDoctorDto.getAvailableTimes());

        OrgDoctor updateOrgDoctor = orgDoctorRepository.save(orgDoctor);

        OrgDoctorDto responseDto = mapToDto(updateOrgDoctor);

        return responseDto;
    }

    @Override
    public OrgDoctorResponse searchOrgDoctor(int pageNo,
                                        int pageSize,
                                        String sortBy,
                                        Long orgId,
                                        String query) {

        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<OrgDoctor> doctors = orgDoctorRepository.searchOrgDoctor(pageable,orgId, query);

        List<OrgDoctor> doctorList = doctors.getContent();

        List<OrgDoctorDto> content = doctorList.stream().map(dr-> mapToDto(dr)).collect(Collectors.toList());

        OrgDoctorResponse orgDoctorResponse = new OrgDoctorResponse();
        orgDoctorResponse.setContent(content);
        orgDoctorResponse.setPageNo(doctors.getNumber());
        orgDoctorResponse.setPageSize(doctors.getSize());
        orgDoctorResponse.setTotalElements(doctors.getTotalElements());
        orgDoctorResponse.setTotalPages(doctors.getTotalPages());
        orgDoctorResponse.setLast(doctors.isLast());

        return orgDoctorResponse;
    }

    public OrgDoctor mapToEntity(OrgDoctorDto orgDoctorDto){
        OrgDoctor orgDoctor = mapper.map(orgDoctorDto,OrgDoctor.class);
        return orgDoctor;
    }
    public OrgDoctorDto mapToDto(OrgDoctor orgDoctor){
        OrgDoctorDto orgDoctorDto = mapper.map(orgDoctor,OrgDoctorDto.class);
        return orgDoctorDto;
    }
}
