package pl.akademiaspecjalistowit.ecommerce.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import pl.akademiaspecjalistowit.api.UsersApi;
import pl.akademiaspecjalistowit.ecommerce.user.service.UserService;
import pl.akademiaspecjalistowit.model.LoginRequest;
import pl.akademiaspecjalistowit.model.LoginResponse;
import pl.akademiaspecjalistowit.model.RegistrationRequest;
import pl.akademiaspecjalistowit.model.RegistrationResponse;
@AllArgsConstructor
public class UserController implements UsersApi {
    private final UserService userService;
    @Override
    public ResponseEntity<LoginResponse> loginUser(LoginRequest loginRequest) {
        userService.loginUser(loginRequest);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<RegistrationResponse> registerUser(RegistrationRequest registrationRequest) {
        userService.registerUser(registrationRequest);
        return ResponseEntity.ok().build();
       // return ResponseEntity.ok("Registration successful. Please check your email for verification instructions.");
    }
}
