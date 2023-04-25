package billing.repository;

import billing.entity.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    @Query(value = "select p from Patient p where p.name like concat('%',:query, '%')" +
            "or p.phone like concat(:query, '%')" +
            "or p.email like concat(:query, '%')" )
    Page<Patient> search(String query, Pageable pageable);
}