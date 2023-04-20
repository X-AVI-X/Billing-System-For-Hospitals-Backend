package billing.controller.impl;

import billing.controller.AuthController;
import billing.dto.LoginDto;
import billing.repository.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthControllerImpl implements AuthController {

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final AppUserRepository appUserRepository;

    @Override
    @PostMapping("/login")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginDto login){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                login.getEmail(),login.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new ResponseEntity<>("Logged in successfully!", HttpStatus.OK);
    }
}
