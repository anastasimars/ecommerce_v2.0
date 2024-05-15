package pl.akademiaspecjalistowit.ecommerce.domain.exception;

import javax.naming.AuthenticationException;

public class CustomAuthenticationException extends AuthenticationException {

    public CustomAuthenticationException(String explanation) {
        super(explanation);
    }
}
