package billing.repository;

import billing.entity.DrAppointmentBill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DrAppointmentRepository extends JpaRepository<DrAppointmentBill,Long> {

    Page<DrAppointmentBill> findAllDrAppointmentBillByOrganizationId(Pageable pageable, Long orgId);
    Page<DrAppointmentBill> findAllDrAppointmentBillByOrganizationIdAndCreatedById(Pageable pageable, Long orgId, Long createdById);
    Page<DrAppointmentBill> findAllByOrganizationIdAndOrgDoctorId(Pageable pageable, Long orgId, Long orgDoctorId);
}
