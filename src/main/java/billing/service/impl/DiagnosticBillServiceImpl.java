package billing.service.impl;

import billing.dto.DiagnosticBillDto;
import billing.dto.OrgDiagnosticAndDiscountWrapperDto;
import billing.entity.*;
import billing.exceptionHandling.ResourceNotFound;
import billing.projection.DiagnosticBillProjection;
import billing.repository.AppUserRepository;
import billing.repository.DiagnosticBillRepository;
import billing.repository.OrgDiagnosticRepository;
import billing.repository.PatientRepository;
import billing.service.DiagnosticBillService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DiagnosticBillServiceImpl implements DiagnosticBillService {
    private final OrgDiagnosticRepository orgDiagnosticRepository;
    private final PatientRepository patientRepository;
    private final DiagnosticBillRepository diagnosticBillRepository;
    private final AppUserRepository appUserRepository;
    private final ModelMapper modelMapper;


    public DiagnosticBillServiceImpl(OrgDiagnosticRepository orgDiagnosticRepository,
                                     PatientRepository patientRepository,
                                     DiagnosticBillRepository diagnosticBillRepository,
                                     AppUserRepository appUserRepository,
                                     ModelMapper modelMapper) {
        this.orgDiagnosticRepository = orgDiagnosticRepository;
        this.patientRepository = patientRepository;
        this.diagnosticBillRepository = diagnosticBillRepository;
        this.appUserRepository = appUserRepository;
        this.modelMapper = modelMapper;
    }
@Override
    public DiagnosticBillProjection add(DiagnosticBillDto diagnosticBillDto) {

        List<OrgDiagnosticAndDiscount> orgDiagnosticAndDiscounts = new ArrayList<>();

        for (OrgDiagnosticAndDiscountWrapperDto orgDiagnosticAndDiscountWrapperDto :
                diagnosticBillDto.getOrgDiagnosticAndDiscounts()) {
            OrgDiagnostic orgDiagnostic = (orgDiagnosticRepository.findById(
                    orgDiagnosticAndDiscountWrapperDto.getOrgDiagnosticId()).orElseThrow(()
                    -> new ResourceNotFound(
                        "Org-Diagnostic id #" + orgDiagnosticAndDiscountWrapperDto.getOrgDiagnosticId() + " invalid")));

            orgDiagnosticAndDiscounts.add(new OrgDiagnosticAndDiscount(null,
                                                                    orgDiagnostic,
                                                                    orgDiagnostic.getPrice(),
                                                                    orgDiagnosticAndDiscountWrapperDto.getDiscount()));
            System.out.println(orgDiagnostic);
        }
        AppUser appUser = appUserRepository.findById(diagnosticBillDto.getAppUserId()).orElseThrow(()
                -> new ResourceNotFound("AppUser Id #" + diagnosticBillDto.getAppUserId() + " invalid"));

        Patient patient = patientRepository.findById(diagnosticBillDto.getPatientId()).orElseThrow(()
                -> new ResourceNotFound("Patient Id #" + diagnosticBillDto.getPatientId() + " invalid"));

        DiagnosticBill diagnosticBill = modelMapper.map(diagnosticBillDto, DiagnosticBill.class);

            diagnosticBill.setId(null);
            diagnosticBill.setAppUser(appUser);
            diagnosticBill.setPatient(patient);
            diagnosticBill.setOrgDiagnosticAndDiscounts(orgDiagnosticAndDiscounts);
        DiagnosticBill savedDiagnosticBill = diagnosticBillRepository.save(diagnosticBill);

        DiagnosticBillProjection invoice= modelMapper.map(savedDiagnosticBill, DiagnosticBillProjection.class);

        for (OrgDiagnosticAndDiscount orgDiagnosticAndDiscount: invoice.getOrgDiagnosticAndDiscounts()){
            orgDiagnosticAndDiscount.getOrgDiagnostic().setOrganization(null);
        }
        invoice.getAppUser().setPassword(null);
        invoice.getAppUser().setOrganization(null);

        return invoice;
    }

    public DiagnosticBillProjection viewInvoice (Long id){
        DiagnosticBillProjection invoice= modelMapper.map(diagnosticBillRepository.findById(id),
                DiagnosticBillProjection.class);
        for (OrgDiagnosticAndDiscount orgDiagnosticAndDiscount: invoice.getOrgDiagnosticAndDiscounts()){
            orgDiagnosticAndDiscount.getOrgDiagnostic().setOrganization(null);
        }
        invoice.getAppUser().setPassword(null);
        invoice.getAppUser().setOrganization(null);
        return invoice;
    }

    @Override
    public Page<DiagnosticBill> search(Long orgId, String query, short pageNo, byte size, String sortBy, String order){
        Pageable pageable = PageRequest.of(pageNo, size, Sort.by(Sort.Direction.DESC,sortBy));
        Page<DiagnosticBill> invoices = diagnosticBillRepository.search(orgId, query, pageable);

        for (DiagnosticBill invoice : invoices.getContent()){
            for (OrgDiagnosticAndDiscount orgDiagnosticAndDiscount : invoice.getOrgDiagnosticAndDiscounts()){
                orgDiagnosticAndDiscount.getOrgDiagnostic().setOrganization(null);
            }
            invoice.getAppUser().setPassword(null);
            invoice.getAppUser().setOrganization(null);
        }
        return invoices;
    }
}
