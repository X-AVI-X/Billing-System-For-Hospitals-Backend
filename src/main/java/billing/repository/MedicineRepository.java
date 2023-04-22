package billing.repository;

import billing.entity.Medicine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long> {
    @Query(
            value = "select count(id) from medicine where id = :id",
            nativeQuery = true)
    int isMedicinePresent (@Param("id") Long id);

    @Query (value = "select m from Medicine m where m.name like %:query% or m.genericName like %:query%")
    Page<Medicine> findByNameOrGenericNameContaining(Pageable paging, String query);

    boolean existsByNameAndGenericNameAndFormulationAndStrengthAndVendor(String name, String genericName,
                                                                         String formulation, String strength,
                                                                         String vendor);

    @Query(value = "SELECT * FROM medicine m WHERE m.name LIKE CONCAT('%',:query, '%') and " +
            "NOT m.id IN (SELECT m.id FROM medicine m, org_medicine om " +
            "WHERE m.id = om.medicine_id " +
            "and om.organization_id=:orgId)",
            nativeQuery = true)
    Page<Medicine> findMedicineNotInOrganization (Long orgId, String query, Pageable pageable);
}
