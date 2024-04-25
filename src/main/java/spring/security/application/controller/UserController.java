package spring.security.application.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.security.application.model.User;
import spring.security.application.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService service;
    @PostMapping
    @ApiOperation(value = "Cadastra um novo usuário",
                    notes = "Metodo usado para adicionar novos usuários")
    public void postUser(@RequestBody User user){
        service.createUser(user);
    }
}
