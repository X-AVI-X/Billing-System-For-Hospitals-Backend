package billing.controller;

import billing.projection.AdminDashboardProjection;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

public interface AdminDashboardController {
    @GetMapping("/dashboard")
    public ResponseEntity<AdminDashboardProjection> getStats ();
}
