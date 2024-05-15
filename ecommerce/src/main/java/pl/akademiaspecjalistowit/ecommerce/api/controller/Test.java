package pl.akademiaspecjalistowit.ecommerce.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class Test {
    @RequestMapping(value = "/test")
    String testSecurity(){
        return "It works";
    }
}
