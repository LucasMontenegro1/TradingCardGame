package fiuba.tdd.tp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import fiuba.tdd.tp.service.AuthenticationResponse;
import fiuba.tdd.tp.service.AuthenticationService;
import fiuba.tdd.tp.service.CuentaJugador;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationService service;

    public AuthenticationController(AuthenticationService service) {
        this.service = service;
    }
    
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/registrarse")
    public ResponseEntity<String> registrarse(
        @RequestBody CuentaJugador request
    ) {

        AuthenticationResponse response = service.registrar(request);
        if (response == null) {
            ResponseEntity.badRequest();
        }

        return ResponseEntity.ok(response.getToken());
    }

    @PostMapping("/loguearse")
    public ResponseEntity<String> loguerse(
        @RequestBody CuentaJugador request
    ) {

        AuthenticationResponse response = service.loguear(request);
        if (response == null) {
            ResponseEntity.badRequest();
        }

        return ResponseEntity.ok(response.getToken());
    }

}