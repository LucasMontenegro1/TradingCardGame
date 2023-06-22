package fiuba.tdd.tp.controller;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fiuba.tdd.tp.model.Excepciones.CartaNoEncontrada;
import fiuba.tdd.tp.model.Excepciones.MazoExistente;
import fiuba.tdd.tp.model.Excepciones.MazoInvalido;
import fiuba.tdd.tp.model.jugador.Jugador;
import fiuba.tdd.tp.model.jugador.Mazo;
import fiuba.tdd.tp.repository.JugadoresRepository;
import fiuba.tdd.tp.service.JwtService;

@RestController
@RequestMapping("/api/mazos")
public class MazoController {
 
    private final JugadoresRepository repositorio;

    public MazoController(JugadoresRepository repositorioJugadores) {
        this.repositorio = repositorioJugadores;
    }


    @GetMapping("")
    public HashMap<String, Mazo> listarMazos(@RequestHeader("Authorization") String authorizationHeader) {
        Optional<Jugador> jugador = repositorio.solicitador(authorizationHeader);

        if (jugador.isPresent()) {
            Jugador unJugador = jugador.get();
            return unJugador.getMazos();
        }

        return null;
    }

    @PostMapping("/agregar/{nombreMazo}")
    public ResponseEntity<String> agregarNuevoMazo(
            @RequestHeader("Authorization") String authorizationHeader,
            @PathVariable String nombreMazo,
            @RequestBody HashMap<String, Integer> cartas
    ) throws MazoExistente, CartaNoEncontrada {
    
        Optional<Jugador> jugador = repositorio.solicitador(authorizationHeader);

        if (jugador.isPresent()) {
            Jugador unJugador = jugador.get();
            Mazo nuevoMazo = new Mazo(cartas);
            try {
                unJugador.agregarMazo(nombreMazo, nuevoMazo);
            } catch (Exception e) {
                return ResponseEntity.ok(e.getMessage());        
            }
        } 
        
        return ResponseEntity.ok("Mazo agregado");
    }

    @PostMapping("/eliminar/{nombreMazo}")
    public ResponseEntity<String> eliminarMazo(
            @RequestHeader("Authorization") String authorizationHeader,
            @PathVariable String nombreMazo
    ) throws MazoExistente, CartaNoEncontrada {
    
        Optional<Jugador> jugador = repositorio.solicitador(authorizationHeader);

        if (jugador.isPresent()) {
            Jugador unJugador = jugador.get();
            unJugador.eliminarMazo(nombreMazo);
        } 
        
        return ResponseEntity.ok("Mazo eliminado");
    }

    @PostMapping("/agregarCartas/{nombreMazo}")
    public ResponseEntity<String> agregarCartas(
            @RequestHeader("Authorization") String authorizationHeader,
            @PathVariable String nombreMazo,
            @RequestBody HashMap<String, Integer> cartas
    ) throws MazoExistente, CartaNoEncontrada, MazoInvalido {
    
        Optional<Jugador> jugador = repositorio.solicitador(authorizationHeader);

        if (jugador.isPresent()) {
            Jugador unJugador = jugador.get();
            try {
                unJugador.agregarCartasAMazo(nombreMazo, cartas);
            } catch (Exception e) {
                return ResponseEntity.ok(e.getMessage());        
            }
        } 
        
        return ResponseEntity.ok("Mazo actualizado");
    }

    @PostMapping("/eliminarCartas/{nombreMazo}")
    public ResponseEntity<String> eliminarCartas(
            @RequestHeader("Authorization") String authorizationHeader,
            @PathVariable String nombreMazo,
            @RequestBody HashMap<String, Integer> cartas
    ) throws MazoInvalido {

        Optional<Jugador> jugador = repositorio.solicitador(authorizationHeader);

        if (jugador.isPresent()) {
            Jugador unJugador = jugador.get();
            try {
                unJugador.removerCartasDeMazo(nombreMazo, cartas);
            } catch (Exception e) {
                return ResponseEntity.ok(e.getMessage());
            }
        }
        
        return ResponseEntity.ok("Mazo actualizado");
    }

}