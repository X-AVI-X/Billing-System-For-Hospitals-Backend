package billing.repository;

import billing.entity.Organization;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrganizationRepository extends JpaRepository<Organization,Long> {

    @Query(value = "SELECT * FROM organization o WHERE " +
            "o.name LIKE CONCAT('%',:query, '%')" ,nativeQuery = true)
    Page<Organization> searchOrganization(Pageable pageable, String query);


}
