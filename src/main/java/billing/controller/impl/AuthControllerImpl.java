package billing.controller.impl;

import billing.controller.AuthController;
import billing.dto.LoginDto;
import billing.entity.AppUser;
import billing.exceptionHandling.ResourceNotFound;
import billing.repository.AppUserRepository;
import billing.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/auth")
public class AuthControllerImpl implements AuthController {

    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final AppUserRepository appUserRepository;

    public AuthControllerImpl(JwtUtil jwtUtil,
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

    @PostMapping("/login")
    public String generateToken(@RequestBody LoginDto authRequest) throws BadCredentialsException {
        Optional<AppUser> appUser=appUserRepository.findByEmail(authRequest.getEmail());
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authRequest.getEmail(),
                    authRequest.getPassword())
            );
        }catch (BadCredentialsException ex) {
            throw new BadCredentialsException("Invalid Email/Password");
        }

        return jwtUtil.generateToken(authRequest.getEmail(),appUserRepository
                .findByEmail(authRequest.getEmail()).orElseThrow(()
                        -> new ResourceNotFound("User not found by email")).getRole().toString());
    }
}
