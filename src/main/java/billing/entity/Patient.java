package billing.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@RequiredArgsConstructor
@ToString
//@Table(uniqueConstraints = {
//        @UniqueConstraint(columnNames = {"phone"})
//})
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private String phone;

    private String email;

    private byte age;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Patient patient = (Patient) o;
        return getId() != null && Objects.equals(getId(), patient.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }


}
