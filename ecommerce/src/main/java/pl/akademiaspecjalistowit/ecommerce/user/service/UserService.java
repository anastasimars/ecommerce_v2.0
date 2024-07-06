package pl.akademiaspecjalistowit.ecommerce.user.service;

import pl.akademiaspecjalistowit.ecommerce.user.entity.UserEntity;
import pl.akademiaspecjalistowit.model.LoginRequest;
import pl.akademiaspecjalistowit.model.LoginResponse;
import pl.akademiaspecjalistowit.model.RegistrationRequest;
import pl.akademiaspecjalistowit.model.RegistrationResponse;

public interface UserService {

    LoginResponse loginUser(LoginRequest loginRequest);

    RegistrationResponse registerUser(RegistrationRequest registrationRequest);

    void sendActivationEmail(UserEntity user);

    void activateUser(String token);
}
