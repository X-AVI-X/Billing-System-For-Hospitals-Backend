package billing.repository;

import billing.entity.OrgDiagnostic;
import billing.projection.OrgDiagnosticProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrgDiagnosticRepository extends JpaRepository<OrgDiagnostic, Long> {
    List<OrgDiagnostic> findAllByOrganizationId(Long orgId);

    @Query(value =
            "select new billing.projection.OrgDiagnosticProjection (o.id, o.price, o.organization.id, d.serviceName) " +
            "from OrgDiagnostic o INNER JOIN Diagnostic d ON o.diagnostic.id = d.id " +
            "where o.organization.id = :orgId and d.serviceName like concat('%',:query,'%')")
    Page<OrgDiagnosticProjection> search(Long orgId, String query, Pageable pageable);

    boolean existsByDiagnosticIdAndOrganizationId(Long diagnosticId, Long organizationId);

    Long countOrgDiagnosticByOrganizationId(Long orgId);
}
