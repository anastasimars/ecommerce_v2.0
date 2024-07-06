package pl.akademiaspecjalistowit.ecommerce.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.akademiaspecjalistowit.ecommerce.user.service.UserService;
@AllArgsConstructor
class UserController{
    private final UserService userService;

    public ResponseEntity<String> activateUser(String token){
        try{
            userService.activateUser(token);
            return new ResponseEntity<>("Account successfully activated", HttpStatus.OK);
        }catch (IllegalArgumentException e){
            return new ResponseEntity<>("Invalid activation token", HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
