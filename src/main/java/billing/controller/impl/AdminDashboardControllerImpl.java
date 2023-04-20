package billing.controller.impl;

import billing.controller.AdminDashboardController;
import billing.projection.AdminDashboardProjection;
import billing.service.AdminDashboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminDashboardControllerImpl implements AdminDashboardController {
    private final AdminDashboardService adminDashboardService;

    public AdminDashboardControllerImpl(AdminDashboardService adminDashboardService) {
        this.adminDashboardService = adminDashboardService;
    }

    @GetMapping("/dashboard")
    public ResponseEntity<AdminDashboardProjection> getStats (){
        return ResponseEntity.ok(adminDashboardService.stats());
    }
}
