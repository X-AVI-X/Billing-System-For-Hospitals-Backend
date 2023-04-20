package billing.repository;

import billing.entity.DrAppointmentBill;
import billing.entity.PharmacyBill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PharmacyBillRepository extends JpaRepository<PharmacyBill,Long> {

    Page<PharmacyBill> findAllByOrganizationId(Pageable pageable, Long orgId);
    Page<PharmacyBill> findAllByOrganizationIdAndCreatedById(Pageable pageable, Long orgId, Long userId);
}
