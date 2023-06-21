package fiuba.tdd.tp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fiuba.tdd.tp.model.jugador.Jugador;
import fiuba.tdd.tp.repository.JugadoresRepository;
import fiuba.tdd.tp.service.JwtService;

@RestController
@RequestMapping("/api/jugadores")
public class JugadorController {
    
    private final JugadoresRepository repositorio;
    private final JwtService jwtService;

    public JugadorController(JugadoresRepository repositorioJugadores, JwtService jwtService) {
        this.repositorio = repositorioJugadores;
        this.jwtService = jwtService;
    }

    @GetMapping("")
    public List<String> jugadoresDisponibles() {
        return repositorio.jugadoresRegistrados();
    }

    @GetMapping("/cartas")
    public HashMap<String, Integer> cartasDelJugador(@RequestHeader("Authorization") String authorizationHeader) {

        String jwt = authorizationHeader.substring(7);
        String username = jwtService.extractUsername(jwt);

        Optional<Jugador> unJugador = repositorio.buscarPorUsername(username);
        HashMap<String, Integer> cartasJugador = new HashMap<>();

        if (unJugador.isPresent()) {
            cartasJugador = unJugador.get().getCartas();
        }

        return cartasJugador;
    }
}
