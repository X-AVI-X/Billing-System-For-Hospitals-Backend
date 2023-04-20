package billing.repository;

import billing.entity.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DoctorRepository extends JpaRepository<Doctor,Long> {
    Page<Doctor> getAllDoctorByAppUserId(Pageable pageable, Long appUserId);

    @Query(value = "SELECT * FROM doctor d left join doctor_specialities s on (d.id = s.doctor_id) WHERE " +
            "d.name LIKE CONCAT('%',:query, '%')" +
            "Or d.bmdc LIKE CONCAT('%',:query, '%')" +
            "Or s.specialities LIKE CONCAT('%', :query, '%')",nativeQuery = true)
    Page<Doctor> searchDoctor(Pageable pageable, String query);
}
