package billing.service.impl;

import billing.dto.DiagnosticDto;
import billing.entity.Diagnostic;
import billing.exceptionHandling.ResourceNotFound;
import billing.repository.DiagnosticRepository;
import billing.repository.MedicineRepository;
import billing.service.DiagnosticService;
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
import java.util.Objects;

@Service
public class DiagnosticServiceImpl implements DiagnosticService {
    private final DiagnosticRepository diagnosticRepository;
    private final ModelMapper modelMapper;

    public DiagnosticServiceImpl(DiagnosticRepository diagnosticRepository,
                                 MedicineRepository medicineRepository, ModelMapper modelMapper) {
        this.diagnosticRepository = diagnosticRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public DiagnosticDto add(DiagnosticDto diagnosticDto) {
        if (!diagnosticRepository.existsByServiceName(diagnosticDto.getServiceName())) {
            Diagnostic diagnostic = modelMapper.map(diagnosticDto, Diagnostic.class);
            return modelMapper.map(diagnosticRepository.save(diagnostic), DiagnosticDto.class);
        }
        else return null;
    }

    @Override
    public List<Diagnostic> addAll (List <DiagnosticDto> diagnosticDtoList){
        Type diagnosticListType = new TypeToken<List<Diagnostic>>(){}.getType();
        return diagnosticRepository.saveAll(modelMapper.map(diagnosticDtoList,diagnosticListType));
    }

    @Override
    public DiagnosticDto get(Long id) {
        Diagnostic diagnostic = diagnosticRepository.findById(id).orElseThrow(()
                ->new ResourceNotFound("#"+ id+ " Diagnostic doesn't exist"));
        return modelMapper.map(diagnostic, DiagnosticDto.class);
    }

    @Override
    public List<Diagnostic> getAll(short page, String sortBy, byte size) {
        Pageable paging = PageRequest.of(page,size, Sort.by(sortBy));
        Page<Diagnostic> pageResult = diagnosticRepository.findAll(paging);
        if (pageResult.hasContent()) {
            return pageResult.getContent();
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public List<Diagnostic> getAll() {
        return diagnosticRepository.findAll();
    }

    @Override
    public String delete(Long id) {
        if (diagnosticRepository.isDiagnosticPresent(id)==1){
            diagnosticRepository.delete(Objects.requireNonNull(diagnosticRepository.findById(id).orElse(null)));
            return "Diagnostic service  #"+ id+ " deleted";
        }
        else return "Diagnostic service  #"+ id+ " doesn't exist";
    }

    @Override
    public DiagnosticDto update(DiagnosticDto diagnosticDto) {
        Diagnostic diagnostic = modelMapper.map(diagnosticDto, Diagnostic.class);
        if ((diagnosticRepository.isDiagnosticPresent(diagnosticDto.getId()))==1){
            return modelMapper.map(diagnosticRepository.save(diagnostic),DiagnosticDto.class);
        }
        else throw new ResourceNotFound("#"+ diagnosticDto.getId()+ " is invalid diagnostic id");
    }

    @Override
    public Page<Diagnostic> search(String query, short page, String sortBy, byte size) {
        Pageable pageable = PageRequest.of(page,size, Sort.by(sortBy));
        return diagnosticRepository.findByServiceNameContaining(query,pageable);
    }

    @Override
    public Page<Diagnostic> findDiagnosticNotInOrganization(Long orgId, String query, short page, String sortBy, byte size){
        Pageable pageable = PageRequest.of(page,size, Sort.by(sortBy));
        return diagnosticRepository.findDiagnosticNotInOrganization(orgId, query, pageable);
    }
}
