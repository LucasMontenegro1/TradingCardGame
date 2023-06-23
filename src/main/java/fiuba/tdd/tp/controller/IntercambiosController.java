package fiuba.tdd.tp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import fiuba.tdd.tp.model.Excepciones.CartaNoEncontrada;
import fiuba.tdd.tp.model.jugador.Jugador;
import fiuba.tdd.tp.model.jugador.Mercado;
import fiuba.tdd.tp.repository.JugadoresRepository;
import fiuba.tdd.tp.service.IntercambioDeCartas;

@RestController
@RequestMapping("/api/mercado")
public class IntercambiosController {

    private final JugadoresRepository repositorio;
    private final Mercado mercadoDeCartas = new Mercado();

     public IntercambiosController(JugadoresRepository repositorioJugadores) {
        this.repositorio = repositorioJugadores;
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("")
    public void unirseAlMercado(@RequestHeader("Authorization") String authorizationHeader) {
        Optional<Jugador> unJugador = repositorio.solicitador(authorizationHeader);
       
        if (unJugador.isPresent()) {
            mercadoDeCartas.agregarIntercambiador(unJugador.get());
        }
    }

    @PostMapping("/intercambiar")
    public ResponseEntity<String> intercambiar(@RequestHeader("Authorization") String authorizationHeader, @RequestBody IntercambioDeCartas intercambioDeCartas) throws CartaNoEncontrada {

        Optional<Jugador> unJugador = repositorio.solicitador(authorizationHeader);

        if (unJugador.isPresent()) {
            try {
                mercadoDeCartas.realizarIntercambio(unJugador.get(), intercambioDeCartas.cartaDispuesta(), intercambioDeCartas.cartaDeseada());
            } catch (Exception e) {
                return ResponseEntity.ok("Carta no encontrada");
            }
        }

        return ResponseEntity.ok("Intercambio realizado");
    }

    @PostMapping("/eliminarIntercambio")
    public ResponseEntity<String> eliminarIntercambio(@RequestHeader("Authorization") String authorizationHeader, @RequestBody IntercambioDeCartas intercambioDeCartas) throws CartaNoEncontrada {

        Optional<Jugador> unJugador = repositorio.solicitador(authorizationHeader);

        if (unJugador.isPresent()) {
            try {
                mercadoDeCartas.eliminarIntercambio(unJugador.get(), intercambioDeCartas.cartaDispuesta(), intercambioDeCartas.cartaDeseada());
            } catch (Exception e) {
                return ResponseEntity.ok("Intercambio no encontrado");
            }
        }

        return ResponseEntity.ok("Intercambio eliminado");
    }

    @GetMapping("/intercambiosMercado")
    public ResponseEntity<HashMap<String,HashMap<String, ArrayList<String>>>> verIntercambios(@RequestHeader("Authorization") String authorizationHeader) throws CartaNoEncontrada {
        return ResponseEntity.ok(mercadoDeCartas.intercambiosDisponibles());
    }

    @GetMapping("/intercambios")
    public ResponseEntity<HashMap<String, ArrayList<String>>> verIntercambiosJugador(@RequestHeader("Authorization") String authorizationHeader) throws CartaNoEncontrada {
        Optional<Jugador> unJugador = repositorio.solicitador(authorizationHeader);
        if (unJugador.isPresent()) {
            return ResponseEntity.ok(mercadoDeCartas.intercambiosJugador(unJugador.get().getUsername()));
        }

        return null;
    }
}