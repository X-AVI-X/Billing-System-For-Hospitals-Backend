package billing.controller.impl;

import billing.controller.OrgAdminDashboardController;
import billing.projection.OrgAdminDashboardProjection;
import billing.service.OrgAdminDashboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
@RequestMapping("/org-admin")
public class OrgAdminDashboardControllerImpl implements OrgAdminDashboardController {
    private final OrgAdminDashboardService orgAdminDashboardService;

    public OrgAdminDashboardControllerImpl(OrgAdminDashboardService orgAdminDashboardService) {
        this.orgAdminDashboardService = orgAdminDashboardService;
    }

    @GetMapping("/dashboard/{orgId}")
    @Override
    public ResponseEntity<OrgAdminDashboardProjection> getStats(@PathVariable Long orgId) {
        return ResponseEntity.ok(orgAdminDashboardService.stats(orgId));
    }
}
