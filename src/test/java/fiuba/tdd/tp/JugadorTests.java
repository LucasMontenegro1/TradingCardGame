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

    @Test 
    void testJugadorDepositaDinero() {
        Jugador jugador = new Jugador("Juan", "1234");
        jugador.depositarDinero(100);
        assertEquals(jugador.cantdDinero, 100);
    }

    @Test 
    void testJugadorRealizaDosDepositos() {
        Jugador jugador = new Jugador("Juan", "1234");
        jugador.depositarDinero(100);
        jugador.depositarDinero(300);
        assertEquals(jugador.cantdDinero, 400);
    }

    @Test
    void testJugadorRealizaExtraccion() {
        Jugador jugador = new Jugador("Juan", "1234");
        jugador.depositarDinero(100);
        
        jugador.extraerDinero(50);
        assertEquals(jugador.cantdDinero, 50);
    }

    @Test
    void testJugadorRealizaDosExtracciones() {
        Jugador jugador = new Jugador("Juan", "1234");
        jugador.depositarDinero(100);
        
        jugador.extraerDinero(50);
        jugador.extraerDinero(20);
        assertEquals(jugador.cantdDinero, 30);
    }

    @Test
    void testJugadorNoPuedeExtraerDeMas() {
        Jugador jugador = new Jugador("Juan", "1234");
        jugador.depositarDinero(100);
        
        jugador.extraerDinero(150);

        assertEquals(jugador.cantdDinero, 100);
    }
}