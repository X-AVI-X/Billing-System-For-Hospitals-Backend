package billing.controller.impl;

import billing.controller.AppUserController;
import billing.dto.AppUserDto;
import billing.pageResponse.AppUserResponse;
import billing.service.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/appUser")
public class AppUserControllerImpl implements AppUserController {

    private final AppUserService appUserService;

    @PostMapping("/org/{orgId}/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AppUserDto> add(@PathVariable Long orgId, @Valid @RequestBody AppUserDto appUserDto) {
        return new ResponseEntity<>(appUserService.add(orgId,appUserDto), HttpStatus.CREATED);
    }

    @GetMapping("/get/all")
    @Override
    public ResponseEntity<AppUserResponse> getAll(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy) {

        return ResponseEntity.ok(appUserService.getAllAppUser(pageNo,pageSize,sortBy));
    }

    @GetMapping("/get/{id}")
    @Override
    public ResponseEntity<AppUserDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(appUserService.getAppUserById(id));
    }

    @GetMapping("/organization/{orgId}/all")
    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public ResponseEntity<AppUserResponse> getAllByOrgId(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
            @PathVariable Long orgId) {

        return ResponseEntity.ok(appUserService.getAllByOrgId(pageNo, pageSize, sortBy, orgId));
    }

    @PutMapping("update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public ResponseEntity<AppUserDto> update(@Valid @RequestBody AppUserDto instructorDto, @PathVariable Long id) {
        AppUserDto responseInstructor = appUserService.updateAppUserById(instructorDto,id);

        return new  ResponseEntity<>(responseInstructor,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @Override
    public ResponseEntity<String> delete(@PathVariable Long id) {
        appUserService.deleteAppUserById(id);

        return new  ResponseEntity<>("App user deleted", HttpStatus.OK);
    }

    @GetMapping("/search")
    @Override
    public ResponseEntity<AppUserResponse> searchEmployee(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(value = "query", defaultValue = "") String query) {

        return ResponseEntity.ok(appUserService.searchEmployee(pageNo, pageSize, sortBy, query));
    }

    @GetMapping("/org/{orgId}/search")
    @Override
    public ResponseEntity<AppUserResponse> searchOrgEmployee(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
            @PathVariable Long orgId,
            @RequestParam(value = "query", defaultValue = "") String query) {

        return ResponseEntity.ok(appUserService.searchEmployee(pageNo, pageSize, sortBy, query));
    }
}
