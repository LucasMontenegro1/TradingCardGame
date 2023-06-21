package fiuba.tdd.tp.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import fiuba.tdd.tp.service.TokenService;

@RestController
public class AuthController {
    
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;

    public AuthController(TokenService tokenService, AuthenticationManager authenticationManager) {
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/token")
    public String token(@RequestBody CuentaJugador loginJugador) {
        
        Authentication autenticacion = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginJugador.username(), 
                        loginJugador.password()
                )
        );
        return tokenService.generarToken(autenticacion);
    }

}
