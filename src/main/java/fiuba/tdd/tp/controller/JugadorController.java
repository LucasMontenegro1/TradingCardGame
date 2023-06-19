package fiuba.tdd.tp.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import fiuba.tdd.tp.repository.JugadoresRepository;

@RestController
@RequestMapping("/api/jugadores")
public class JugadorController {
    
    private final JugadoresRepository repositorio;

    public JugadorController(JugadoresRepository repositorioJugadores) {
        repositorio = repositorioJugadores;
    }

    @GetMapping("")
    public List<String> jugadoresDisponibles() {
        return repositorio.jugadoresRegistrados();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void registrarJugador(@RequestBody RegistroJugador unaCuenta) {
        if (repositorio.nombreDisponible(unaCuenta.usuario())) {
            repositorio.registrar(unaCuenta.usuario(), unaCuenta.password());
        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El nombre de usuario ya está en uso");
        }
    }
}
