package billing.service.impl;

import billing.dto.MedicineDto;
import billing.entity.Medicine;
import billing.exceptionHandling.ResourceNotFound;
import billing.mapper.Mapper;
import billing.repository.MedicineRepository;
import billing.service.MedicineService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MedicineServiceImpl implements MedicineService {
    private final MedicineRepository medicineRepository;
    private final ModelMapper modelMapper;

    public MedicineServiceImpl(MedicineRepository medicineRepository, ModelMapper modelMapper) {
        this.medicineRepository = medicineRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public MedicineDto add(MedicineDto medicineDto) {
        if (!medicineRepository.existsByNameAndGenericNameAndFormulationAndStrengthAndVendor(
                                                                                            medicineDto.getName(),
                                                                                            medicineDto.getGenericName(),
                                                                                            medicineDto.getFormulation(),
                                                                                            medicineDto.getStrength(),
                                                                                            medicineDto.getVendor())) {
            Medicine medicine = Mapper.mapToMedicine(medicineDto);
            Medicine savedMedicine = medicineRepository.save(medicine);
            return Mapper.mapToMedicineDto(savedMedicine);
        }else {
            return null;
        }
    }

    @Override
    public MedicineDto get(Long id) {
        Optional<Medicine> optionalMedicine = medicineRepository.findById(id);
        Medicine medicine = optionalMedicine.get();
        return Mapper.mapToMedicineDto(medicine);
    }

    @Override
    public List<MedicineDto> getAll(short page, String sortBy, byte size) {
        Pageable pageable = PageRequest.of(page,size, Sort.by(sortBy));
        Page<Medicine> pageResult = medicineRepository.findAll(pageable);
        if (pageResult.hasContent()) {
            Type medicineDtoList = new TypeToken<List<MedicineDto>>(){}.getType();
            return modelMapper.map(pageResult.getContent(),medicineDtoList);
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public Page<Medicine> search(String query, short page, String sortBy, byte size) {
        Pageable paging = PageRequest.of(page,size, Sort.by(sortBy));
        return medicineRepository.findByNameOrGenericNameContaining(paging, query);
    }

    @Override
    public String delete(Long id) {
        if (medicineRepository.existsById(id)){
            medicineRepository.delete(medicineRepository.findById(id).orElseThrow(()
                    ->new ResourceNotFound("Medicine  #"+ id+ " doesn't exists.")));
            return "Medicine  #"+ id+ " is deleted";
        }
        else return "Medicine  #"+ id+ " doesn't exist";
    }

    @Override
    public MedicineDto update(MedicineDto medicineDto) {
        if (medicineRepository.existsById(medicineDto.getId())){
            Medicine updatedMedicine = Mapper.mapToMedicine(medicineDto);
            medicineRepository.save(updatedMedicine);
            return Mapper.mapToMedicineDto(updatedMedicine);
        }
        else return null;
    }

    @Override
    public Page<Medicine> findMedicineNotInOrganization(Long orgId, String query, short page, String sortBy, byte size){
        Pageable pageable = PageRequest.of(page,size, Sort.by(sortBy));
        return medicineRepository.findMedicineNotInOrganization(orgId, query, pageable);
    }
}
