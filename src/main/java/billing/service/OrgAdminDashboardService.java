package billing.service;

import billing.projection.OrgAdminDashboardProjection;

public interface OrgAdminDashboardService {
    OrgAdminDashboardProjection stats(Long orgId);
}
