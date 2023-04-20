package billing.service;

import billing.dto.DiagnosticBillDto;
import billing.entity.DiagnosticBill;
import billing.projection.DiagnosticBillProjection;
import org.springframework.data.domain.Page;

public interface DiagnosticBillService {
    DiagnosticBillProjection add(DiagnosticBillDto diagnosticBillDto);
    DiagnosticBillProjection viewInvoice (Long id);

    Page<DiagnosticBill> Search(Long orgId, String query, short pageNo, byte size, String sortBy, String order);
}
