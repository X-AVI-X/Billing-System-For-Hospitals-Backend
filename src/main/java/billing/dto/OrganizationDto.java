package billing.dto;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class OrganizationDto {

    private Long id;

    @NotBlank
    @Size(min = 1, message = "name should have at least 1 character")
    private String name;

    @NotBlank
    private String address;

    @Pattern(regexp = "(^([+]{1}[8]{2}|0088)?(01){1}[3-9]{1}\\d{8})$", message = "Phone number not valid")
    @NotBlank
    private String phone;

    @Email
    @NotBlank
    private String email;

    @NotEmpty
    @Pattern(regexp="^(https?:\\/\\/(?:www\\.|(?!www))[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\\.[^\\s]{2,}|www\\.[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\\.[^\\s]{2,}|https?:\\/\\/(?:www\\.|(?!www))[a-zA-Z0-9]\\.[^\\s]{2,}|www\\.[a-zA-Z0-9]\\.[^\\s]{2,})$",message = "website URL is invalid")
    private String website;
}
