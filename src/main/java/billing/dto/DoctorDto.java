package billing.dto;

import billing.entity.Gender;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Data
public class DoctorDto {

    private Long id;

    @NotBlank
    private String name;

    @Pattern(regexp = "(^([+]{1}[8]{2}|0088)?(01){1}[3-9]{1}\\d{8})$", message = "Phone number not valid")
    @NotBlank
    private String phone;

    @Email
    @NotBlank
    private String email;

    @NotNull
    private Gender gender;

    @NotNull(message = "must not be null")
    private List<String> specialities;

    @NotNull(message = "must not be null")
    private List<String> degrees;

    @NotNull
    private Long bmdc;
}
