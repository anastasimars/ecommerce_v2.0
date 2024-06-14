package pl.akademiaspecjalistowit.ecommerce.user.service;

import pl.akademiaspecjalistowit.ecommerce.user.entity.UserEntity;
import pl.akademiaspecjalistowit.model.LoginRequest;
import pl.akademiaspecjalistowit.model.RegistrationRequest;

public interface UserService {

    void loginUser(LoginRequest loginRequest);

    void registerUser(RegistrationRequest registrationRequest);

    void sendActivationEmail(UserEntity user);
}
