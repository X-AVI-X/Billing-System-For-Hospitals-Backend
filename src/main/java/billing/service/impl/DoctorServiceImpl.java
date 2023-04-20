package billing.service.impl;

import billing.dto.AppUserDto;
import billing.dto.DoctorDto;
import billing.entity.AppUser;
import billing.entity.Doctor;
import billing.exceptionHandling.ResourceNotFound;
import billing.pageResponse.AppUserResponse;
import billing.pageResponse.DoctorResponse;
import billing.repository.AppUserRepository;
import billing.repository.DoctorRepository;
import billing.service.DoctorService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private DoctorRepository doctorRepository;
    private AppUserRepository appUserRepository;
    @Override
    public DoctorDto saveDoctorByadmin(Long appUserId,DoctorDto doctorDto) {

        Doctor doctor = mapToEntity(doctorDto);
        AppUser appUser = appUserRepository.findById(appUserId).orElseThrow(()->
                new ResourceNotFound("App user id not found"));
        doctor.setAppUser(appUser);
        Doctor newDoctor = doctorRepository.save(doctor);

        return mapToDto(newDoctor);
    }


    @Override
    public DoctorDto getDoctorById(Long id) {
        Doctor doctor = doctorRepository.findById(id).orElseThrow(()->
                new ResourceNotFound("doctor id not found"));
        return mapToDto(doctor);
    }

    @Override
    public DoctorResponse getAllDoctor(int pageNo,
                                        int pageSize,
                                        String sortBy) {

        Pageable pageable = PageRequest.of(pageNo,pageSize, Sort.by(sortBy));
        Page<Doctor> doctors = doctorRepository.findAll(pageable);

        List<Doctor> listOfDoctors = doctors.getContent();
        List<DoctorDto> content = listOfDoctors.stream().map(doctor -> mapToDto(doctor)).collect(Collectors.toList());

        DoctorResponse doctorResponse = new DoctorResponse();
        doctorResponse.setContent(content);
        doctorResponse.setPageNo(doctors.getNumber());
        doctorResponse.setPageSize(doctors.getSize());
        doctorResponse.setTotalElements(doctors.getTotalElements());
        doctorResponse.setTotalPages(doctors.getTotalPages());
        doctorResponse.setLast(doctors.isLast());

        return doctorResponse;
    }

    @Override
    public DoctorResponse getAllDoctorByAppUserId(int pageNo,
                                                   int pageSize,
                                                   String sortBy,
                                                   Long appUserId) {

        Pageable pageable = PageRequest.of(pageNo,pageSize, Sort.by(sortBy));
        Page<Doctor> doctors = doctorRepository.getAllDoctorByAppUserId(pageable,appUserId);

        List<Doctor> listOfDoctors = doctors.getContent();
        List<DoctorDto> content = listOfDoctors.stream().map(doctor-> mapToDto(doctor)).collect(Collectors.toList());

        DoctorResponse doctorResponse = new DoctorResponse();
        doctorResponse.setContent(content);
        doctorResponse.setPageNo(doctors.getNumber());
        doctorResponse.setPageSize(doctors.getSize());
        doctorResponse.setTotalElements(doctors.getTotalElements());
        doctorResponse.setTotalPages(doctors.getTotalPages());
        doctorResponse.setLast(doctors.isLast());

        return doctorResponse;
    }

    @Override
    public DoctorDto updateById(DoctorDto doctorDto, Long id) {
        Doctor doctor = doctorRepository.findById(id).orElseThrow(()->
                new ResourceNotFound("Doctor","id",id));

        doctor.setName(doctorDto.getName());
        doctor.setPhone(doctorDto.getPhone());
        doctor.setEmail(doctorDto.getEmail());
        doctor.setGender(doctorDto.getGender());
        doctor.setSpecialities(doctorDto.getSpecialities());
        doctor.setDegrees(doctorDto.getDegrees());
        doctor.setBmdc(doctorDto.getBmdc());

        Doctor updateDoctor = doctorRepository.save(doctor);

        DoctorDto responseDto = mapToDto(updateDoctor);


        return responseDto;
    }

    @Override
    public DoctorResponse searchDoctor(int pageNo,
                                        int pageSize,
                                        String sortBy,
                                        String query) {

        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Doctor> doctors = doctorRepository.searchDoctor(pageable, query);

        List<Doctor> doctorList = doctors.getContent();

        List<DoctorDto> content = doctorList.stream().map(dr-> mapToDto(dr)).collect(Collectors.toList());

        DoctorResponse doctorResponse = new DoctorResponse();
        doctorResponse.setContent(content);
        doctorResponse.setPageNo(doctors.getNumber());
        doctorResponse.setPageSize(doctors.getSize());
        doctorResponse.setTotalElements(doctors.getTotalElements());
        doctorResponse.setTotalPages(doctors.getTotalPages());
        doctorResponse.setLast(doctors.isLast());

        return doctorResponse;
    }

    public Doctor mapToEntity(DoctorDto doctorDto){

        Doctor doctor = new Doctor();
        doctor.setName(doctorDto.getName());
        doctor.setPhone(doctorDto.getPhone());
        doctor.setEmail(doctorDto.getEmail());
        doctor.setGender(doctorDto.getGender());
        doctor.setSpecialities(doctorDto.getSpecialities());
        doctor.setDegrees(doctorDto.getDegrees());
        doctor.setBmdc(doctorDto.getBmdc());

        return doctor;
    }

    public DoctorDto mapToDto(Doctor doctor){

        DoctorDto newDoctor = new DoctorDto();
        newDoctor.setId(doctor.getId());
        newDoctor.setName(doctor.getName());
        newDoctor.setPhone(doctor.getPhone());
        newDoctor.setEmail(doctor.getEmail());
        newDoctor.setGender(doctor.getGender());
        newDoctor.setSpecialities(doctor.getSpecialities());
        newDoctor.setDegrees(doctor.getDegrees());
        newDoctor.setBmdc(doctor.getBmdc());

        return newDoctor;
    }
}
