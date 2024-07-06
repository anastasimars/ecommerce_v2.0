package pl.akademiaspecjalistowit.ecommerce.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import pl.akademiaspecjalistowit.api.LoginApi;
import pl.akademiaspecjalistowit.ecommerce.user.service.UserService;
import pl.akademiaspecjalistowit.model.LoginRequest;
import pl.akademiaspecjalistowit.model.LoginResponse;
@AllArgsConstructor
class LoginController implements LoginApi {
    private final UserService userService;
    @Override
    public ResponseEntity<LoginResponse> loginUser(LoginRequest loginRequest) {
        try {
            LoginResponse response = userService.loginUser(loginRequest);
            return ResponseEntity.ok(response);
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
