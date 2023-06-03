package fiuba.tdd.tp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import fiuba.tdd.tp.Excepciones.CartaNoEncontrada;
import fiuba.tdd.tp.Excepciones.DineroInsuficiente;
import fiuba.tdd.tp.carta.CartasDisponibles;
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
        assertEquals(jugador.getNombre(), "Juan");
    }

    @Test
    void testJugadorSeRegistraSinDinero() {
        Jugador jugador = new Jugador("Juan", "1234");
        assertEquals(jugador.getCantdDinero(), 0);
    }

    @Test 
    void testJugadorDepositaDinero() {
        Jugador jugador = new Jugador("Juan", "1234");
        jugador.depositarDinero(100);
        assertEquals(jugador.getCantdDinero(), 100);
    }

    @Test 
    void testJugadorRealizaDosDepositos() {
        Jugador jugador = new Jugador("Juan", "1234");
        jugador.depositarDinero(100);
        jugador.depositarDinero(300);
        assertEquals(jugador.getCantdDinero(), 400);
    }

    @Test
    void testJugadorRealizaExtraccion() throws DineroInsuficiente {
        Jugador jugador = new Jugador("Juan", "1234");
        jugador.depositarDinero(100);
        
        jugador.extraerDinero(50);
        assertEquals(jugador.getCantdDinero(), 50);
    }

    @Test
    void testJugadorRealizaDosExtracciones() throws DineroInsuficiente {
        Jugador jugador = new Jugador("Juan", "1234");
        jugador.depositarDinero(100);
        
        jugador.extraerDinero(50);
        jugador.extraerDinero(20);
        assertEquals(jugador.getCantdDinero(), 30);
    }

    @Test
    void testJugadorNoPuedeExtraerDeMas() throws DineroInsuficiente {
        Jugador jugador = new Jugador("Juan", "1234");
        jugador.depositarDinero(100);
        
        assertThrows(DineroInsuficiente.class, () -> {
            jugador.extraerDinero(150);
        });

        assertEquals(jugador.getCantdDinero(), 100);
    }

    @Test
    void testJugadorIniciaSinCartas() {
        Jugador jugador = new Jugador("Juan", "1234");

        assertEquals(jugador.getCartas().size(), 0);
    }

    @Test
    void testJugadorCompraUnaCartaYSeAgregaASuListaDeCartas() throws DineroInsuficiente {        
        Jugador jugador = new Jugador("Juan", "1234");
        jugador.depositarDinero(50);
        
        jugador.comprarCarta(CartasDisponibles.ALQUMISTA); 

        assertEquals(jugador.getCartas().size(), 1);
    }

    @Test
    void testJugadorCompraUnaMismaCartaDosVeces() throws DineroInsuficiente {        
        Jugador jugador = new Jugador("Juan", "1234");
        jugador.depositarDinero(50);
        
        jugador.comprarCarta(CartasDisponibles.ALQUMISTA); 
        jugador.comprarCarta(CartasDisponibles.ALQUMISTA); 

        assertEquals(jugador.getCartas().size(), 1);
        assertEquals(jugador.getCartas().get(CartasDisponibles.ALQUMISTA.nombreCarta()), 2);
        assertEquals(jugador.getCantdDinero(), 10);
    }

    @Test
    void testJugadorCompraUnaCartaYSeDescuentaSuDinero() throws DineroInsuficiente {        
        Jugador jugador = new Jugador("Juan", "1234");
        jugador.depositarDinero(50);

        jugador.comprarCarta(CartasDisponibles.ALQUMISTA); 

        assertEquals(jugador.getCantdDinero(), 30);
    }

    @Test
    void testJugadorNoPuedeCompraUnaCartaSinDineroSuficiente() throws DineroInsuficiente {        
        Jugador jugador = new Jugador("Juan", "1234");

        assertThrows(DineroInsuficiente.class, () -> {
            jugador.comprarCarta(CartasDisponibles.ALQUMISTA); 
        });

        assertEquals(jugador.getCantdDinero(), 0);
        assertEquals(jugador.getCartas().size(), 0);
    }

    @Test
    void testJugadorDejaDeTenerUnaCarta() throws DineroInsuficiente, CartaNoEncontrada {
        Jugador jugador = new Jugador("Juan", "1234");
        jugador.depositarDinero(100);
        
        jugador.comprarCarta(CartasDisponibles.ALQUMISTA); 
        jugador.comprarCarta(CartasDisponibles.ALQUMISTA);
        jugador.comprarCarta(CartasDisponibles.ALQUMISTA);  

        jugador.eliminarCarta(CartasDisponibles.ALQUMISTA);

        assertEquals(jugador.getCartas().get(CartasDisponibles.ALQUMISTA.nombreCarta()), 2);
        assertEquals(jugador.getCantdDinero(), 40);
    }

    @Test
    void testJugadorNoPuedeEliminarUnaCartaQueNoTiene() throws CartaNoEncontrada {
        Jugador jugador = new Jugador("Juan", "1234");

        assertThrows(CartaNoEncontrada.class, () -> {
            jugador.eliminarCarta(CartasDisponibles.ALQUMISTA);
        });
    }

}