package spring.security.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import spring.security.application.dto.Login;
import spring.security.application.dto.Sessao;
import spring.security.application.model.User;
import spring.security.application.repository.UserRepository;
import spring.security.application.security.JWTCreator;
import spring.security.application.security.JWTObject;
import spring.security.application.security.SecurityConfig;

import java.util.Date;

@RestController
public class LoginController {
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private SecurityConfig securityConfig;
    @Autowired
    private UserRepository repository;

    @PostMapping("/login")
    public Sessao logar(@RequestBody Login login){
        User user = repository.findByUsername(login.getUsername());
        if(user != null){
            boolean passwordOk = encoder.matches(login.getPassword(), user.getPassword());
            if(!passwordOk){
                throw new RuntimeException("Senha inválida para o login: " + login.getUsername());
            }
            Sessao sessao = new Sessao();
            sessao.setLogin(user.getUsername());

            JWTObject jwtObject = new JWTObject();
            jwtObject.setIssueAt(new Date(System.currentTimeMillis()));
            jwtObject.setExpiration((new Date(System.currentTimeMillis() + SecurityConfig.EXPIRATION)));
            jwtObject.setRoles(user.getRoles());
            sessao.setToken(JWTCreator.create(SecurityConfig.PREFIX, SecurityConfig.KEY, jwtObject));
            sessao.setExpiracao(jwtObject.getExpiration().toString());
            return sessao;

        } else {
            throw new RuntimeException("Erro ao tentar fazer login");
        }
    }
}
