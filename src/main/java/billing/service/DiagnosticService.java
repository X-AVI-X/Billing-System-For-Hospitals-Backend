package billing.service;

import billing.dto.DiagnosticDto;
import billing.entity.Diagnostic;
import org.springframework.data.domain.Page;

import java.util.List;

public interface DiagnosticService {
    DiagnosticDto add(DiagnosticDto diagnosticDto);

    List<Diagnostic> addAll(List<DiagnosticDto> diagnosticDtoList);

    DiagnosticDto get(Long id);
    List<Diagnostic> getAll(short page, String sortBy, byte size);
    List<Diagnostic> getAll();
    String delete (Long id);
    DiagnosticDto update(DiagnosticDto diagnosticDto);
    Page<Diagnostic> search(String query, short page, String sortBy, byte size);
    Page<Diagnostic> findDiagnosticNotInOrganization(Long orgId, String query, short page, String sortBy, byte size);
}
