package pl.akademiaspecjalistowit.ecommerce.domain.service.user;

import pl.akademiaspecjalistowit.ecommerce.security.authentication.entity.UserEntity;
import pl.akademiaspecjalistowit.model.LoginRequest;
import pl.akademiaspecjalistowit.model.RegistrationRequest;

public interface UserService {

    void loginUser(LoginRequest loginRequest);

    void registerUser(RegistrationRequest registrationRequest);

    void sendActivationEmail(UserEntity user);
}
