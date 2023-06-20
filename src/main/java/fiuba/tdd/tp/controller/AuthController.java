package fiuba.tdd.tp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import fiuba.tdd.tp.service.TokenService;

@RestController
public class AuthController {
    
    private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);

    private final TokenService tokenService;

    public AuthController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("/token")
    public String token(Authentication autenticacion) {

        LOG.debug("Token solicitado para el usuario: '{}'", autenticacion.getName());
        String token = tokenService.generarToken(autenticacion);
        LOG.debug("Token {}", token);

        return token;
    }

}
