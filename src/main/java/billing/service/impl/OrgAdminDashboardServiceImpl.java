package billing.service.impl;

import billing.projection.OrgAdminDashboardProjection;
import billing.repository.*;
import billing.service.OrgAdminDashboardService;
import org.springframework.stereotype.Service;

@Service
public class OrgAdminDashboardServiceImpl implements OrgAdminDashboardService {

    private final OrgDoctorRepository orgDoctorRepository;
    private final OrgMedicineRepository orgMedicineRepository;
    private final OrgDiagnosticRepository orgDiagnosticRepository;
    private final AppUserRepository appUserRepository;
    private final OrganizationRepository organizationRepository;

    public OrgAdminDashboardServiceImpl(
                                        OrgDoctorRepository orgDoctorRepository,
                                        OrgMedicineRepository orgMedicineRepository,
                                        OrgDiagnosticRepository orgDiagnosticRepository,
                                        AppUserRepository appUserRepository,
                                        OrganizationRepository organizationRepository) {
        this.orgDoctorRepository = orgDoctorRepository;
        this.orgMedicineRepository = orgMedicineRepository;
        this.orgDiagnosticRepository = orgDiagnosticRepository;
        this.appUserRepository = appUserRepository;
        this.organizationRepository = organizationRepository;
    }

    @Override
    public OrgAdminDashboardProjection stats(Long orgId) {

        return new OrgAdminDashboardProjection(
                orgDoctorRepository.countOrgDoctorByOrganizationId(orgId),
                orgMedicineRepository.countOrgMedicineByOrganizationId(orgId),
                orgDiagnosticRepository.countOrgDiagnosticByOrganizationId(orgId),
                appUserRepository.countAppUserByOrganizationId(orgId)
        );
    }
}
