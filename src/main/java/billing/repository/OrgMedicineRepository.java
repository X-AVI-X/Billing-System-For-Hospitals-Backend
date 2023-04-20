package billing.repository;

import billing.entity.OrgMedicine;
import billing.projection.OrgMedicineProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrgMedicineRepository extends JpaRepository<OrgMedicine, Long> {

    Page<OrgMedicine> findAllByOrganizationId(Long orgId, Pageable pageable);

    @Query(value =
                    "select new billing.projection.OrgMedicineProjection (o.id,  o.medicine) " +
                    "from OrgMedicine o INNER JOIN Medicine m ON o.medicine.id = m.id " +
                    "where o.organization.id = :orgId and (m.name like concat('%',:query,'%') " +
                            "or m.genericName like concat(:query,'%'))"
    )
    Page<OrgMedicineProjection> search(Long orgId, String query, Pageable pageable);

    boolean existsByMedicineIdAndOrganizationId(Long medicineId, Long organizationId);

    Long countOrgMedicineByOrganizationId(Long orgId);
}
