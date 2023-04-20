package billing.controller.impl;

import billing.dto.LoginDto;
import billing.entity.AppUser;
import billing.repository.AppUserRepository;
import billing.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@Slf4j
public class WelcomeController {

    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final AppUserRepository appUserRepository;

    public WelcomeController(JwtUtil jwtUtil,
                             AuthenticationManager authenticationManager,
                             AppUserRepository appUserRepository) {
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.appUserRepository = appUserRepository;
    }

    @GetMapping("/")
    public String welcome() {
        return "Welcome to Billing System!";
    }

    @PostMapping("/authenticate")
    public String generateToken(@RequestBody LoginDto authRequest) throws Exception {
        Optional<AppUser> appUser=appUserRepository.findByEmail(authRequest.getEmail());
//        log.info("User : ",appUser.get().getEmail());
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
            );
        } catch (Exception ex) {
            throw new Exception("Invalid Email/Password");
        }

        return jwtUtil.generateToken(authRequest.getEmail(),appUserRepository
                .findByEmail(authRequest.getEmail()).get().getRole().toString());
    }
}
