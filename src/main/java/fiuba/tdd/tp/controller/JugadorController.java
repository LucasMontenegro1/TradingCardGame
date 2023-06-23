package fiuba.tdd.tp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import fiuba.tdd.tp.model.jugador.Jugador;
import fiuba.tdd.tp.repository.JugadoresRepository;

@RestController
@RequestMapping("/api/jugadores")
public class JugadorController {
    
    private final JugadoresRepository repositorio;
    public JugadorController(JugadoresRepository repositorioJugadores) {
        this.repositorio = repositorioJugadores;
    }

    @GetMapping("")
    public List<String> jugadoresDisponibles() {
        return repositorio.jugadoresRegistrados();
    }

    @GetMapping("/cartas")
    public HashMap<String, Integer> cartasDelJugador(@RequestHeader("Authorization") String authorizationHeader) {

        Optional<Jugador> unJugador = repositorio.solicitador(authorizationHeader);
        HashMap<String, Integer> cartasJugador = new HashMap<>();

        if (unJugador.isPresent()) {
            cartasJugador = unJugador.get().getCartas();
        }

        return cartasJugador;
    }

    @GetMapping("/dinero")
    public Integer getDinero(@RequestHeader("Authorization") String authorizationHeader) {

        Optional<Jugador> unJugador = repositorio.solicitador(authorizationHeader);
       
        if (unJugador.isPresent()) {
            return unJugador.get().getCantdDinero();
        }

        return 0;
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/depositar/{cantidad}")
    public void depositarDinero(@RequestHeader("Authorization") String authorizationHeader, @PathVariable Integer cantidad) {

        Optional<Jugador> unJugador = repositorio.solicitador(authorizationHeader);
       
        if (unJugador.isPresent()) {
            unJugador.get().depositarDinero(cantidad);
        }
    }

}