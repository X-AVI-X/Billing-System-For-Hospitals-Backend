package billing.controller.impl;

import billing.controller.DoctorController;
import billing.dto.AppUserDto;
import billing.dto.DoctorDto;
import billing.dto.OrganizationDto;
import billing.pageResponse.DoctorResponse;
import billing.repository.DoctorRepository;
import billing.service.DoctorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/doctor")
public class DoctorControllerImpl implements DoctorController {

    private DoctorService doctorService;

    @PostMapping("/admin/{appUserId}/add")
    @Override
    public ResponseEntity<DoctorDto> saveByAdmin(@PathVariable Long appUserId,
                                                 @Valid  @RequestBody DoctorDto doctorDto) {

        return new ResponseEntity<>(doctorService.saveDoctorByadmin(appUserId,doctorDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<DoctorDto> getById(@PathVariable Long id){
        return ResponseEntity.ok(doctorService.getDoctorById(id));
    }

    @GetMapping("/all")
    @Override
    public ResponseEntity<DoctorResponse> getAll(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy
    ) {
        return ResponseEntity.ok(doctorService.getAllDoctor(pageNo,pageSize,sortBy));
    }

    @GetMapping("/admin/{appUserId}/all")
    @Override
    public ResponseEntity<DoctorResponse> getAllByAppUserId(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
            @PathVariable Long appUserId) {

        return ResponseEntity.ok(doctorService.getAllDoctorByAppUserId(pageNo,pageSize,sortBy,appUserId));
    }

    @PutMapping("/update/{id}")
    @Override
    public ResponseEntity<DoctorDto> update(@Valid @RequestBody DoctorDto doctorDto, @PathVariable Long id) {
        DoctorDto responseDoctor = doctorService.updateById(doctorDto,id);

        return new  ResponseEntity<>(responseDoctor,HttpStatus.OK);
    }

    @GetMapping("/search")
    @Override
    public ResponseEntity<DoctorResponse> searchDoctor(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(value = "query", defaultValue = "") String query) {

        return ResponseEntity.ok(doctorService.searchDoctor(pageNo, pageSize,sortBy, query));
    }
}
