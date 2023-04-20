package billing.service.impl;

import billing.projection.AdminDashboardProjection;
import billing.repository.*;
import billing.service.AdminDashboardService;
import org.springframework.stereotype.Service;

@Service
public class AdminDashboardServiceImpl implements AdminDashboardService {

    private final DoctorRepository doctorRepository;
    private final MedicineRepository medicineRepository;
    private final DiagnosticRepository diagnosticRepository;
    private final AppUserRepository appUserRepository;
    private final OrganizationRepository organizationRepository;

    public AdminDashboardServiceImpl(DoctorRepository doctorRepository,
                                     MedicineRepository medicineRepository,
                                     DiagnosticRepository diagnosticRepository,
                                     AppUserRepository appUserRepository, OrganizationRepository organizationRepository) {
        this.doctorRepository = doctorRepository;
        this.medicineRepository = medicineRepository;
        this.diagnosticRepository = diagnosticRepository;
        this.appUserRepository = appUserRepository;
        this.organizationRepository = organizationRepository;
    }

    @Override
    public AdminDashboardProjection stats() {

        return new AdminDashboardProjection(
                doctorRepository.count(),
                medicineRepository.count(),
                diagnosticRepository.count(),
                appUserRepository.count(),
                organizationRepository.count()
        );
    }
}
