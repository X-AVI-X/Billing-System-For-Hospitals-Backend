package billing.service.impl;


import billing.dto.PatientDto;
import billing.entity.Patient;
import billing.repository.PatientRepository;
import billing.service.PatientService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;

@Service
public class PatientServiceImpl implements PatientService {
    private final ModelMapper modelMapper;
    private final PatientRepository patientRepository;

    public PatientServiceImpl(ModelMapper modelMapper, PatientRepository patientRepository) {
        this.modelMapper = modelMapper;
        this.patientRepository = patientRepository;
    }


    @Override
    public PatientDto add(PatientDto patientDto) {
        Patient patient = modelMapper.map(patientDto, Patient.class);
        return modelMapper.map(patientRepository.save(patient), PatientDto.class);
    }

    @Override
    public PatientDto get(Long id) {
        return modelMapper.map(patientRepository.findById(id), PatientDto.class);
    }

    @Override
    public List<PatientDto> getAll() {
        List <Patient> patientList = patientRepository.findAll();
        Type patientDtoListType = new TypeToken<List<PatientDto>>(){}.getType();
        return modelMapper.map(patientList,patientDtoListType);
    }

    @Override
    public String delete(Long id) {
        if (patientRepository.existsById(id)){
            patientRepository.delete(Objects.requireNonNull(patientRepository.findById(id).orElse(null)));
            return "Patient  #"+ id+ " is deleted";
        }
        else return "Patient  #"+ id+ " doesn't exist";
    }

    @Override
    public PatientDto update(PatientDto patientDto) {
        Patient patient = modelMapper.map(patientDto, Patient.class);
        return modelMapper.map(patientRepository.save(patient), PatientDto.class);
    }

    @Override
    public Page<Patient> search(String query, short page, String sortBy, byte size){
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return patientRepository.search(query, pageable);
    }
}
