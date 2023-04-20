package billing.pageResponse;

import billing.dto.AppUserDto;
import billing.dto.DrAppointmentBillDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentResponse {

    private List<DrAppointmentBillDto> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
}
