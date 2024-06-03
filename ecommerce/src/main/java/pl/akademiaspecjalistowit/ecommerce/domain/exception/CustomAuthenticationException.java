package pl.akademiaspecjalistowit.ecommerce.domain.exception;


import org.springframework.security.core.AuthenticationException;

public class CustomAuthenticationException extends AuthenticationException {

    public CustomAuthenticationException(String explanation) {
        super(explanation);
    }
}
