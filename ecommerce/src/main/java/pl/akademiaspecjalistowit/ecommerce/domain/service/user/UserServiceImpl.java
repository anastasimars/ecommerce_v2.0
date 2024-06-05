package pl.akademiaspecjalistowit.ecommerce.domain.service.user;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.akademiaspecjalistowit.ecommerce.domain.model.ActiveUserEntity;
import pl.akademiaspecjalistowit.ecommerce.domain.repository.ActiveUserRepository;
import pl.akademiaspecjalistowit.ecommerce.email.service.EmailService;
import pl.akademiaspecjalistowit.ecommerce.security.authentication.entity.AuthorityEntity;
import pl.akademiaspecjalistowit.ecommerce.security.authentication.entity.UserEntity;
import pl.akademiaspecjalistowit.ecommerce.security.authentication.repository.UserRepository;
import pl.akademiaspecjalistowit.ecommerce.util.values.UserRole;
import pl.akademiaspecjalistowit.model.Currency;
import pl.akademiaspecjalistowit.model.LoginRequest;
import pl.akademiaspecjalistowit.model.RegistrationRequest;

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

    @Override
    public void loginUser(LoginRequest loginRequest) {

    }

    @Override
    public void registerUser(RegistrationRequest registrationRequest) {
        String username = registrationRequest.getEmail();
        if (userRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("Username is already taken");
        }
        String encodedPassword = passwordEncoder.encode(registrationRequest.getPassword());

        AuthorityEntity authority = new AuthorityEntity("ROLE_CLIENT");

        UserEntity newUser = new UserEntity(Set.of(authority), username, encodedPassword);
        userRepository.save(newUser);

        ActiveUserEntity activeUser = new ActiveUserEntity(
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
                newUser
        );
        activeUserRepository.save(activeUser);
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
        //todo
        return "706977";
    }
}
