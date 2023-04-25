package billing.repository;

import billing.entity.DiagnosticBill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DiagnosticBillRepository extends JpaRepository<DiagnosticBill, Long> {
    Page<?> findByOrganizationId(Long orgId, Pageable pageable);

    @Query(value = "SELECT d from DiagnosticBill d where d.organization.id = :orgId " +
            "and (d.patient.phone like concat('%',:query,'%') " +
            "or d.patient.email like concat('%',:query,'%')" +
            "or d.patient.name like concat('%',:query,'%')" +
            "or cast(d.id AS string) like concat('%',:query,'%')" +
            "or cast(d.patient.id AS string) like concat('%',:query,'%')) ")
    Page<DiagnosticBill> search(Long orgId, String query, Pageable pageable);
}
