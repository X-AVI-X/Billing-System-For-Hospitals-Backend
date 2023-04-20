package billing.controller;

import billing.projection.OrgAdminDashboardProjection;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/org-admin")
public interface OrgAdminDashboardController {
    @GetMapping("/dashboard")
    public ResponseEntity<OrgAdminDashboardProjection> getStats (Long orgId);
}
