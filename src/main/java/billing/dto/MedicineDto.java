package billing.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class MedicineDto {
    private Long id;
    @NotBlank (message = "Name shouldn't be blank.")
    private String name;
    @Positive(message = "Price must be greater than 0.")
    private double price;
    @NotBlank(message = "Generic Name shouldn't be blank.")
    private String genericName;
    @NotBlank(message = "Formulation shouldn't be blank.")
    private String formulation;
    @NotBlank(message = "Strength shouldn't be blank.")
    private String strength;
    @NotBlank(message = "Vendor name shouldn't be blank.")
    private String vendor;
}
