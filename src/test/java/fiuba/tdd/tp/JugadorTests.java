package fiuba.tdd.tp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import fiuba.tdd.tp.jugador.Jugador;

@SpringBootTest
public class JugadorTests {
    
    @Test 
    void testRegistroDeJugador() {
        Jugador jugador = new Jugador("Juan", "1234");
        assert jugador instanceof Jugador : "No es una instancia de Jugador";
    }

    @Test
    void testNombreDelJugador() {
        Jugador jugador = new Jugador("Juan", "1234");
        assertEquals(jugador.nombre, "Juan");
    }

    @Test
    void testJugadorSeRegistraSinDinero() {
        Jugador jugador = new Jugador("Juan", "1234");
        assertEquals(jugador.cantdDinero, 0);
    }
}