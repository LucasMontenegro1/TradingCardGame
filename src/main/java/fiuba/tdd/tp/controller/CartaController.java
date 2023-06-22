package fiuba.tdd.tp.controller;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import fiuba.tdd.tp.model.Excepciones.DineroInsuficiente;
import fiuba.tdd.tp.model.carta.CartasDisponibles;
import fiuba.tdd.tp.model.jugador.Jugador;
import fiuba.tdd.tp.repository.CartasRepository;
import fiuba.tdd.tp.repository.JugadoresRepository;

@RestController
@RequestMapping("/api/cartas")
public class CartaController {
 
    private final CartasRepository repositorioCartas;
    private final JugadoresRepository repositorioJugadores;

    public CartaController(CartasRepository repositorioDeCartas, JugadoresRepository repositorioJugadores) {
        this.repositorioCartas = repositorioDeCartas;
        this.repositorioJugadores = repositorioJugadores;
    }

    @GetMapping("")
    public HashMap<String, Integer> listadoDeCartas() {
        return repositorioCartas.cartasDisponibles();
    }

    @GetMapping("/{nombreCarta}")
    public Integer precioDeCarta(@PathVariable String nombreCarta) {
        return repositorioCartas.buscarPrecioPorNombre(nombreCarta).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Carta no encontrada"));
    }

    @PostMapping("/comprar/{carta}/{cantidad}")
    public ResponseEntity<String> comprarCarta(
            @RequestHeader("Authorization") String authorizationHeader, 
            @PathVariable String carta,
            @PathVariable Integer cantidad
        ) throws DineroInsuficiente {

        Optional<Jugador> unJugador = repositorioJugadores.solicitador(authorizationHeader);

        if (unJugador.isPresent()) {
            for (int i = 0; i < cantidad; i++){
                try {
                    unJugador.get().comprarCarta(CartasDisponibles.valueOf(carta));
                } catch (Exception e) {
                    return ResponseEntity.ok("Dinero insuficiente");
                }
            }
        }

        return ResponseEntity.ok("Compra realizada");
    }
}
