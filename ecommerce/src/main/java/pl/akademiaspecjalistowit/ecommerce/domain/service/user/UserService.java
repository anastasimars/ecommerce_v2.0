package pl.akademiaspecjalistowit.ecommerce.domain.service.user;

import pl.akademiaspecjalistowit.model.LoginRequest;
import pl.akademiaspecjalistowit.model.RegistrationRequest;

public interface UserService {

    void loginUser(LoginRequest loginRequest);

    void registerUser(RegistrationRequest registrationRequest);
}
