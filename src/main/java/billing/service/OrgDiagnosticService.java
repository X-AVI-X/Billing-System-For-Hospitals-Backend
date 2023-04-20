package billing.service;

import billing.dto.OrgDiagnosticDto;
import billing.projection.OrgDiagnosticProjection;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OrgDiagnosticService {
    OrgDiagnosticDto add(Long orgId, Long diagnosticId);
    List<OrgDiagnosticProjection> addAll (List <OrgDiagnosticDto> orgDiagnosticDtoList) throws Exception;
    OrgDiagnosticDto get(Long id);
    List<OrgDiagnosticProjection> getAll (Long orgId);
    String delete (Long id);;
    OrgDiagnosticProjection update(OrgDiagnosticDto orgDiagnosticDto);
    Page<OrgDiagnosticProjection> search(Long orgId, String query, short page, String sortBy, byte size);
}
