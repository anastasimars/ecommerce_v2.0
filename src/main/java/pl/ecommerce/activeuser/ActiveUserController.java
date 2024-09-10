package pl.ecommerce.activeuser;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.ecommerce.api.UsersApi;
import pl.ecommerce.model.LoginRequest;
import pl.ecommerce.model.LoginResponse;
import pl.ecommerce.model.RegistrationRequest;
import pl.ecommerce.model.RegistrationResponse;


@RestController
@AllArgsConstructor
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
