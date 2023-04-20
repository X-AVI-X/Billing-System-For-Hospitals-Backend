package billing.repository;

import billing.entity.Doctor;
import billing.entity.OrgDoctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrgDoctorRepository extends JpaRepository<OrgDoctor,Long> {

    @Query(value = "SELECT * FROM org_doctor o join doctor d on (d.id = o.doctor_id) WHERE o.org_id = :orgId" +
            " or d.name LIKE CONCAT('%',:query, '%')" +
            "Or d.bmdc LIKE CONCAT('%', :query, '%')",nativeQuery = true)
    Page<OrgDoctor> searchOrgDoctor(Pageable pageable, Long orgId, String query);
    Page<OrgDoctor> findAllOrgDoctorByOrganizationIdAndAppUserId(Pageable pageable, Long appUserId, Long orgId);

    Page<OrgDoctor> findAllOrgDoctorByOrganizationId(Pageable pageable, Long orgId);

    Long countOrgDoctorByOrganizationId(Long orgId);
}
