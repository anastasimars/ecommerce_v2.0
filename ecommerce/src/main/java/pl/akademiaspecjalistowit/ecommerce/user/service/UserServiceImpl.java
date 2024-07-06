package pl.akademiaspecjalistowit.ecommerce.user.service;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.akademiaspecjalistowit.ecommerce.domain.exception.ActivationTokenException;
import pl.akademiaspecjalistowit.ecommerce.domain.exception.UserNotFoundException;
import pl.akademiaspecjalistowit.ecommerce.domain.model.ActiveUserEntity;
import pl.akademiaspecjalistowit.ecommerce.domain.repository.ActiveUserRepository;
import pl.akademiaspecjalistowit.ecommerce.email.service.EmailService;
import pl.akademiaspecjalistowit.ecommerce.user.entity.AuthorityEntity;
import pl.akademiaspecjalistowit.ecommerce.user.entity.UserEntity;
import pl.akademiaspecjalistowit.ecommerce.user.repository.UserRepository;
import pl.akademiaspecjalistowit.ecommerce.user.token.ActivationToken;
import pl.akademiaspecjalistowit.ecommerce.user.token.ActivationTokenRepository;
import pl.akademiaspecjalistowit.ecommerce.util.values.UserRole;
import pl.akademiaspecjalistowit.ecommerce.util.values.UserStatus;
import pl.akademiaspecjalistowit.model.*;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ActiveUserRepository activeUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final ActivationTokenRepository activationTokenRepository;

    @Override
    public LoginResponse loginUser(LoginRequest loginRequest) {
       //todo
        return new LoginResponse("logicShouldBeImplemented");
    }

    @Override
    public RegistrationResponse registerUser(RegistrationRequest registrationRequest) {
        String email = registrationRequest.getEmail();
        if (userRepository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("Email is already exist");
        }
        String encodedPassword = passwordEncoder.encode(registrationRequest.getPassword());

        AuthorityEntity authority = new AuthorityEntity("ROLE_CLIENT");

        UserEntity user = new UserEntity(Set.of(authority), email, encodedPassword, "test");
        userRepository.save(user);

        ActiveUserEntity newUser = new ActiveUserEntity(
                UUID.randomUUID(),
                registrationRequest.getFirstName(),
                registrationRequest.getLastName(),
                null,
                null,
                registrationRequest.getEmail(),
                encodedPassword,
                registrationRequest.getAddress(),
                Currency.PLN,
                BigDecimal.ZERO,
                UserRole.CLIENT,
                user);
        activeUserRepository.save(newUser);

        sendActivationEmail(user);

        return new RegistrationResponse(newUser.getTechnicalId(), newUser.getStatus().toString());
    }



    @Override
    public void sendActivationEmail(UserEntity user) {
        String activationLink = "http://localhost:8080/activate?token=" + generateActivationToken(user);
        String username = user.getUsername();
        String email = user.getEmail();
        String message = String.format("Hello %s,\n\nPlease click the link below to activate your account:\n%s",
                username, activationLink);
        emailService.sendEmail(email, "Account Activation", message);
    }

    private String generateActivationToken(UserEntity user) {
        String token = UUID.randomUUID().toString();
        ActivationToken activationToken = new ActivationToken(token, user);
        activationTokenRepository.save(activationToken);
        return token;
    }
    @Override
    @Transactional
    public void activateUser(String token) {
        ActivationToken activationToken = activationTokenRepository.findByToken(token).orElseThrow(() ->
                new ActivationTokenException("Invalid activation token"));
        ActiveUserEntity activeUser = activeUserRepository.findByUserEntity(activationToken.getUser())
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        activeUser.changeStatus(UserStatus.ACTIVE);
        activeUserRepository.save(activeUser);
        activationTokenRepository.delete(activationToken);
    }
}
