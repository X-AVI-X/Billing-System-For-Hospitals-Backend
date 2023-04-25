package billing.service;

import billing.dto.DiagnosticBillDto;
import billing.entity.DiagnosticBill;
import billing.projection.DiagnosticBillProjection;
import org.springframework.data.domain.Page;

import javax.servlet.http.HttpServletRequest;

public interface DiagnosticBillService {
    DiagnosticBillProjection add(HttpServletRequest httpServletRequest, DiagnosticBillDto diagnosticBillDto);

    DiagnosticBillProjection viewInvoice (Long id);

    Page<DiagnosticBill> search(Long orgId, String query, short pageNo, byte size, String sortBy, String order);
}
