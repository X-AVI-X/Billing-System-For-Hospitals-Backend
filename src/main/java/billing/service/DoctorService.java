package billing.service;

import billing.dto.DoctorDto;
import billing.entity.Doctor;
import billing.pageResponse.DoctorResponse;

import java.util.List;

public interface DoctorService {

    DoctorDto saveDoctorByadmin(Long appUserId, DoctorDto doctorDto);
    DoctorDto getDoctorById(Long id);
    DoctorResponse getAllDoctor(int pageNo, int pageSize, String sortBy);
    DoctorResponse getAllDoctorByAppUserId(int pageNo, int pageSize, String sortBy, Long appUserId);
    DoctorDto updateById(DoctorDto doctorDto, Long id);
    DoctorResponse searchDoctor(int pageNo, int pageSize, String sortBy, String query);
}
