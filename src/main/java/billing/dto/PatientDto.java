package billing.dto;

import billing.entity.Gender;
import lombok.*;

import javax.validation.constraints.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PatientDto {

    private Long id;

    @NotBlank(message = "Name shouldn't be blank.")
    private String name;

    @NotBlank(message = "Number shouldn't be blank.")
    @Pattern(regexp ="^(?:\\+?88)?01[13-9]\\d{8}$", message = "Invalid contact number.")
    private String phone;

    @NotBlank(message = "Email shouldn't be blank.")
    @Email(message = "Must use the appropriate format for email, ex: abc123@jotno.net")
    private String email;

    @Min(value = 0, message = "Unrealistic age. Must be a real one.")
    private byte age;

    @NotNull
    private Gender gender;
}
