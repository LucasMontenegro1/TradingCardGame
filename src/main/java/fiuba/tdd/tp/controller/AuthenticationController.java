package fiuba.tdd.tp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fiuba.tdd.tp.service.AuthenticationResponse;
import fiuba.tdd.tp.service.AuthenticationService;
import fiuba.tdd.tp.service.CuentaJugador;

@RestController
public class AuthenticationController {

    private final AuthenticationService service;

    public AuthenticationController(AuthenticationService service) {
        this.service = service;
    }
    
    @PostMapping("/registrarse")
    public ResponseEntity<AuthenticationResponse> registrarse(
        @RequestBody CuentaJugador request
    ) {
        return ResponseEntity.ok(service.registrar(request));
    }

    @PostMapping("/loguearse")
    public ResponseEntity<AuthenticationResponse> loguerse(
        @RequestBody CuentaJugador request
    ) {
        return ResponseEntity.ok(service.loguear(request));
    }

}
