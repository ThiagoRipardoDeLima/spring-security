package spring.security.application.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {
    @GetMapping
    public String welcome() {
        return "Welcome to my Spring Boot Web Api";
    }

    @GetMapping("/users")
    public String user(){
        return "Authorized user";
    }

    @GetMapping("/managers")
    public String managers(){
        return "Authorized manager";
    }

}
