package billing.repository;

import billing.entity.Diagnostic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DiagnosticRepository extends JpaRepository<Diagnostic, Long> {
    @Query(
            value = "select count(id) from diagnostic where id = :id",
            nativeQuery = true)
    int isDiagnosticPresent (@Param("id") Long id);

    Page<Diagnostic> findByServiceNameContaining(String query, Pageable paging);

    boolean existsByServiceName(String serviceName);

    @Query(value = "SELECT * FROM diagnostic d WHERE d.service_name LIKE CONCAT('%',:query, '%') and " +
            "NOT d.id IN (SELECT d.id FROM diagnostic d, org_diagnostic od " +
            "WHERE d.id = od.diagnostic_id " +
            "and od.organization_id=:orgId)",
            nativeQuery = true)
    Page<Diagnostic> findDiagnosticNotInOrganization (Long orgId, String query, Pageable pageable);
}
