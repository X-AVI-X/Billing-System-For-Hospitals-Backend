package billing.controller;

import billing.dto.LoginDto;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthController {
//    ResponseEntity<?> authenticateUser(LoginDto login);
    String generateToken(@RequestBody LoginDto authRequest) throws Exception;
}
