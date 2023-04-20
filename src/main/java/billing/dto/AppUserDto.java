package billing.dto;

import billing.entity.Gender;
import billing.entity.Role;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.*;
import java.util.Set;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AppUserDto {

    private Long id;

    @NotEmpty
    @Size(min = 8, message = "password should have at least 8 character")
    private String password;

//    @NotEmpty
//    private Set<UserRole> role;
    @NotEmpty
    private Set<Role> role;

    @NotBlank
    @Size(min = 1, message = "name should have at least 1 character")
    private String name;

    @NotNull
    @Min(value = 18,message = "Age should have at least 18 years ")
    @Max(value = 150,message = "I think you are already dead")
    private int age;
    @NotNull
    private Gender gender;

    @Email
    @NotBlank
    private String email;

    @Pattern(regexp = "(^([+]{1}[8]{2}|0088)?(01){1}[3-9]{1}\\d{8})$", message = "Phone number not valid")
    @NotBlank
    private String phone;
    @NotBlank
    private String address;
}
