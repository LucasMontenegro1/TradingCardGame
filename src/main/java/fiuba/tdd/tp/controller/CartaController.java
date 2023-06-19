package fiuba.tdd.tp.controller;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import fiuba.tdd.tp.repository.CartasRepository;

@RestController
@RequestMapping("/api/cartas")
public class CartaController {
 
    private final CartasRepository repositorio;

    public CartaController(CartasRepository repositorioDeCartas) {
        repositorio = repositorioDeCartas;
    }

    @GetMapping("")
    public HashMap<String, Integer> listadoDeCartas() {
        return repositorio.cartasDisponibles();
    }

    @GetMapping("/{nombreCarta}")
    public Integer precioDeCarta(@PathVariable String nombreCarta) {
        return repositorio.buscarPrecioPorNombre(nombreCarta).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Carta no encontrada"));
    }
}
