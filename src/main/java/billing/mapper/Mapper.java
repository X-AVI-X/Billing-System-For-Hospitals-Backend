package billing.mapper;


import billing.dto.MedicineDto;
import billing.entity.Medicine;

public interface Mapper {
    public static Medicine mapToMedicine (MedicineDto medicineDto){
        return new Medicine(
                medicineDto.getId(),
                medicineDto.getName(),
                medicineDto.getPrice(),
                medicineDto.getGenericName(),
                medicineDto.getFormulation(),
                medicineDto.getStrength(),
                medicineDto.getVendor()
        );
    }
    public static MedicineDto mapToMedicineDto(Medicine medicine){
        return new MedicineDto(
                medicine.getId(),
                medicine.getName(),
                medicine.getPrice(),
                medicine.getGenericName(),
                medicine.getFormulation(),
                medicine.getStrength(),
                medicine.getVendor()

        );
    }

}
