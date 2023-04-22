package billing.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
//@Table(uniqueConstraints = {
//        @UniqueConstraint(columnNames = {"email","phone"})
//})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 8, message = "password should have at least 8 character")
    private String password;

//    @ManyToMany(fetch = FetchType.EAGER)
//    private Set<UserRole> role;

    @NonNull
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Role> role = new HashSet<>();

    @NonNull
    private String name;

    private int age;

    @NonNull
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @NonNull
    @Column(unique = true)
    private String email;

    @NonNull
    @Column(unique = true)
    private String phone;

    @NotNull
    private String address;

    @ManyToOne
    private Organization organization;

}
