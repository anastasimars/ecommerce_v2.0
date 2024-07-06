package pl.akademiaspecjalistowit.ecommerce.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.akademiaspecjalistowit.api.RegisterApi;
import pl.akademiaspecjalistowit.ecommerce.user.service.UserService;
import pl.akademiaspecjalistowit.model.RegistrationRequest;
import pl.akademiaspecjalistowit.model.RegistrationResponse;

@RestController
@RequestMapping
@AllArgsConstructor
class RegistrationController implements RegisterApi {
    private UserService userService;
    @RequestMapping(value = "/test")
    String testSecurity(){
        return "It works";
    }

    @RequestMapping(value = "/register")
    public ResponseEntity<RegistrationResponse> registerUser (@RequestBody RegistrationRequest registrationRequest) {
        RegistrationResponse response = userService.registerUser(registrationRequest);
        try {
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
