package billing.service;

import billing.dto.PatientDto;
import billing.entity.Patient;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PatientService {
    PatientDto add(PatientDto patientDto);
    PatientDto get(Long id);
    List<PatientDto> getAll ();
    String delete (Long id);
    PatientDto update(PatientDto patientDto);

    Page<Patient> search(String query, short page, String sortBy, byte size);
}
