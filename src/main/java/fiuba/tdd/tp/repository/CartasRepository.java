package fiuba.tdd.tp.repository;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import fiuba.tdd.tp.model.carta.CartasDisponibles;

@Repository
public class CartasRepository {
    
    private final HashMap<String, Integer> cartasDisponibles = new HashMap<>();

    public CartasRepository() {
        for (CartasDisponibles carta : CartasDisponibles.values()) {
            cartasDisponibles.put(carta.nombre, carta.precio);
        }
    }

    public HashMap<String, Integer> cartasDisponibles() {
        return cartasDisponibles;
    }

    public Optional<Integer> buscarPrecioPorNombre(String nombreCarta) {

        if (cartasDisponibles.containsKey(nombreCarta)) {
            return Optional.of(cartasDisponibles.get(nombreCarta));
        }

        return Optional.empty();
    }

}
