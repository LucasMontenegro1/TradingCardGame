package fiuba.tdd.tp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fiuba.tdd.tp.model.Excepciones.CartaNoEncontrada;
import fiuba.tdd.tp.model.Excepciones.DineroInsuficiente;
import fiuba.tdd.tp.model.carta.CartasDisponibles;
import fiuba.tdd.tp.model.jugador.Jugador;
import fiuba.tdd.tp.model.jugador.Mercado;
import fiuba.tdd.tp.repository.JugadoresRepository;
import fiuba.tdd.tp.service.IntercambioDeCartas;
import fiuba.tdd.tp.service.JwtService;

@RestController
@RequestMapping("/api/jugadores")
public class JugadorController {
    
    private final JugadoresRepository repositorio;
    private final JwtService jwtService;
    private final Mercado mercadoDeCartas = new Mercado();

    public JugadorController(JugadoresRepository repositorioJugadores, JwtService jwtService) {
        this.repositorio = repositorioJugadores;
        this.jwtService = jwtService;
    }

    private Optional<Jugador> solicitador(String authorizationHeader) {
        String jwt = authorizationHeader.substring(7);
        String username = jwtService.extractUsername(jwt);

        Optional<Jugador> unJugador = repositorio.buscarPorUsername(username);
        return unJugador;
    }

    @GetMapping("")
    public List<String> jugadoresDisponibles() {
        return repositorio.jugadoresRegistrados();
    }

    @GetMapping("/cartas")
    public HashMap<String, Integer> cartasDelJugador(@RequestHeader("Authorization") String authorizationHeader) {

        Optional<Jugador> unJugador = solicitador(authorizationHeader);
        HashMap<String, Integer> cartasJugador = new HashMap<>();

        if (unJugador.isPresent()) {
            cartasJugador = unJugador.get().getCartas();
        }

        return cartasJugador;
    }

    @GetMapping("/dinero")
    public Integer getDinero(@RequestHeader("Authorization") String authorizationHeader) {

        Optional<Jugador> unJugador = solicitador(authorizationHeader);
       
        if (unJugador.isPresent()) {
            return unJugador.get().getCantdDinero();
        }

        return 0;
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/depositar/{cantidad}")
    public void depositarDinero(@RequestHeader("Authorization") String authorizationHeader, @PathVariable Integer cantidad) {

        Optional<Jugador> unJugador = solicitador(authorizationHeader);
       
        if (unJugador.isPresent()) {
            unJugador.get().depositarDinero(cantidad);
        }
    }

    @PostMapping("/comprar/{carta}")
    public ResponseEntity<String> comprarCarta(@RequestHeader("Authorization") String authorizationHeader, @PathVariable String carta) throws DineroInsuficiente {

        Optional<Jugador> unJugador = solicitador(authorizationHeader);
       
        if (unJugador.isPresent()) {
            try {
                unJugador.get().comprarCarta(CartasDisponibles.valueOf(carta));
            } catch (Exception e) {
                return ResponseEntity.ok("Dinero insuficiente");
            }
        }

        return ResponseEntity.ok("Compra realizada");
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/mercado")
    public void unirseAlMercado(@RequestHeader("Authorization") String authorizationHeader) {
        Optional<Jugador> unJugador = solicitador(authorizationHeader);
       
        if (unJugador.isPresent()) {
            mercadoDeCartas.agregarIntercambiador(unJugador.get());
        }
    }

    @PostMapping("/intercambiar")
    public ResponseEntity<String> intercambiar(@RequestHeader("Authorization") String authorizationHeader, @RequestBody IntercambioDeCartas intercambioDeCartas) throws CartaNoEncontrada {

        Optional<Jugador> unJugador = solicitador(authorizationHeader);

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

        Optional<Jugador> unJugador = solicitador(authorizationHeader);

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
        Optional<Jugador> unJugador = solicitador(authorizationHeader);
        if (unJugador.isPresent()) {
            return ResponseEntity.ok(mercadoDeCartas.intercambiosJugador(unJugador.get().getUsername()));
        }
        
        return null;
    }
}