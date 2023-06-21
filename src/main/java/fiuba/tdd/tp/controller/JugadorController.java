package fiuba.tdd.tp.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import fiuba.tdd.tp.repository.JugadoresRepository;
import fiuba.tdd.tp.service.CuentaJugador;

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
    public void registrarJugador(@RequestBody CuentaJugador unaCuenta) {
        if (repositorio.nombreDisponible(unaCuenta.username())) {
            repositorio.registrar(unaCuenta.username(), unaCuenta.password());
        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El nombre de usuario ya está en uso");
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{usuario}")
    public void actualizarNombreUsuario(@RequestBody CuentaJugador unaCuenta, @PathVariable String usuario) {
        if (repositorio.cuentaExistente(unaCuenta) && repositorio.nombreDisponible(usuario)) {
            repositorio.actualizarUsuario(unaCuenta.username(), usuario);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No es posible actualizar el usuario");
        }
    }
}
