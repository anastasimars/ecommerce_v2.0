package pl.akademiaspecjalistowit.ecommerce.activeuser;

import org.springframework.http.ResponseEntity;
import pl.akademiaspecjalistowit.api.UsersApi;
import pl.akademiaspecjalistowit.model.LoginRequest;
import pl.akademiaspecjalistowit.model.LoginResponse;
import pl.akademiaspecjalistowit.model.RegistrationRequest;
import pl.akademiaspecjalistowit.model.RegistrationResponse;

class ActiveUserController implements UsersApi {
    @Override
    public ResponseEntity<LoginResponse> loginUser(LoginRequest loginRequest) {
        return UsersApi.super.loginUser(loginRequest);
    }

    @Override
    public ResponseEntity<RegistrationResponse> registerUser(RegistrationRequest registrationRequest) {
        return UsersApi.super.registerUser(registrationRequest);
    }
}
