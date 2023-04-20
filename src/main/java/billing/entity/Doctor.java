package billing.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
//@Table(uniqueConstraints = {
//        @UniqueConstraint(columnNames = {"email","phone","bmdc"})
//})
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;

    @NonNull
    @Column(unique = true)
    private String phone;

    @NonNull
    @Column(unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @NotNull
    @Column(unique = true)
    private Long bmdc;

    @NotEmpty
    @ElementCollection
    private List<String> specialities = new ArrayList<>();

    @ElementCollection
    @NotEmpty
    private List<String> degrees = new ArrayList<>();

    @ManyToOne
    @JoinColumn
    private AppUser appUser;



}
