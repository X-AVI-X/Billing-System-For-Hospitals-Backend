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

//    @Query (value = "select d from Diagnostic d where d.id = CAST(:query AS long)  or d.serviceName like %:query%")
    Page<Diagnostic> findByServiceNameContaining(String query, Pageable paging);
}
