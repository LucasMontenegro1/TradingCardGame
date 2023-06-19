package fiuba.tdd.tp.controller;

import java.util.HashMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fiuba.tdd.tp.repository.CartasRepository;

@RestController
@RequestMapping("/api/cartas")
public class CartaController {
 
    private final CartasRepository repositorio;

    public CartaController(CartasRepository repositorioDeCartas) {
        repositorio = repositorioDeCartas;
    }

    @GetMapping("")
    public HashMap<String, Integer> findAll() {
        return repositorio.cartasDisponibles();
    }

}
