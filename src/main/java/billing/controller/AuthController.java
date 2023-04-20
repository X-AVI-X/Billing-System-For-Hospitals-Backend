package billing.controller;

import billing.dto.LoginDto;
import billing.dto.SignUpDto;
import org.springframework.http.ResponseEntity;

public interface AuthController {
    ResponseEntity<?> authenticateUser(LoginDto login);
}
