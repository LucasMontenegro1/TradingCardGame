package fiuba.tdd.tp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.HashMap;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import fiuba.tdd.tp.carta.CartasDisponibles;
import fiuba.tdd.tp.jugador.Mazo;

@SpringBootTest
public class MazoTests {

    @Test
    void testCreacionMazoValido() {

        HashMap<String, Integer> cartas = new HashMap<>();
        cartas.put(CartasDisponibles.AGUA.nombre, 40);

        Mazo mazo = new Mazo(cartas);

        assert mazo instanceof Mazo;
        assertEquals(mazo.cantdCartas(), 40);
    }

    @Test
    void testAgregarCartaAMazo() {
        HashMap<String, Integer> cartas = new HashMap<>();
        cartas.put(CartasDisponibles.AGUA.nombre, 1);

        Mazo mazo = new Mazo(cartas);

        assertEquals(mazo.cantdCartas(), 1);
    }

    @Test
    void testEliminarCartaAMazo() {
        HashMap<String, Integer> cartas = new HashMap<>();
        cartas.put(CartasDisponibles.AGUA.nombre, 50);

        Mazo mazo = new Mazo(cartas);

        mazo.eliminarCarta(CartasDisponibles.AGUA.nombre); 

        assertEquals(mazo.cantdCartas(), 49);
    }

    @Test
    void testEliminarCartaAMazoNoSePuede() {
        Mazo mazo = new Mazo();

        mazo.eliminarCarta(CartasDisponibles.AGUA.nombre); 

        assertEquals(mazo.cantdCartas(), 0);
    }

    @Test
    void testNoSePuedeEliminarUnaCartaQueNoExiste() {
        HashMap<String, Integer> cartas = new HashMap<>();
        cartas.put(CartasDisponibles.AGUA.nombre, 50);

        Mazo mazo = new Mazo(cartas);

        mazo.eliminarCarta(CartasDisponibles.ALQUIMISTA.nombre); 

        assertEquals(mazo.cantdCartas(), 50);
    }

}
