package fiuba.tdd.tp.unitarios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import fiuba.tdd.tp.model.Excepciones.CartaNoEncontrada;
import fiuba.tdd.tp.model.Excepciones.DineroInsuficiente;
import fiuba.tdd.tp.model.Excepciones.MazoExistente;
import fiuba.tdd.tp.model.Excepciones.MazoInvalido;
import fiuba.tdd.tp.model.carta.CartasDisponibles;
import fiuba.tdd.tp.model.jugador.Jugador;
import fiuba.tdd.tp.model.jugador.Mazo;

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
        assertEquals(jugador.nombreJugador(), "Juan");
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
        
        jugador.comprarCarta(CartasDisponibles.ALQUIMISTA); 

        assertEquals(jugador.getCartas().size(), 1);
    }

    @Test
    void testJugadorCompraUnaMismaCartaDosVeces() throws DineroInsuficiente {        
        Jugador jugador = new Jugador("Juan", "1234");
        jugador.depositarDinero(50);
        
        jugador.comprarCarta(CartasDisponibles.ALQUIMISTA); 
        jugador.comprarCarta(CartasDisponibles.ALQUIMISTA); 

        assertEquals(jugador.getCartas().size(), 1);
        assertEquals(jugador.getCartas().get(CartasDisponibles.ALQUIMISTA.nombre), 2);
        assertEquals(jugador.getCantdDinero(), 10);
    }

    @Test
    void testJugadorCompraUnaCartaYSeDescuentaSuDinero() throws DineroInsuficiente {        
        Jugador jugador = new Jugador("Juan", "1234");
        jugador.depositarDinero(50);

        jugador.comprarCarta(CartasDisponibles.ALQUIMISTA); 

        assertEquals(jugador.getCantdDinero(), 30);
    }

    @Test
    void testJugadorNoPuedeCompraUnaCartaSinDineroSuficiente() throws DineroInsuficiente {        
        Jugador jugador = new Jugador("Juan", "1234");

        assertThrows(DineroInsuficiente.class, () -> {
            jugador.comprarCarta(CartasDisponibles.ALQUIMISTA); 
        });

        assertEquals(jugador.getCantdDinero(), 0);
        assertEquals(jugador.getCartas().size(), 0);
    }

    @Test
    void testJugadorDejaDeTenerUnaCarta() throws DineroInsuficiente, CartaNoEncontrada {
        Jugador jugador = new Jugador("Juan", "1234");
        jugador.depositarDinero(100);
        
        jugador.comprarCarta(CartasDisponibles.ALQUIMISTA); 
        jugador.comprarCarta(CartasDisponibles.ALQUIMISTA);
        jugador.comprarCarta(CartasDisponibles.ALQUIMISTA);  

        jugador.eliminarCarta(CartasDisponibles.ALQUIMISTA.nombre);

        assertEquals(jugador.getCartas().get(CartasDisponibles.ALQUIMISTA.nombre), 2);
        assertEquals(jugador.getCantdDinero(), 40);
    }

    @Test
    void testJugadorNoPuedeEliminarUnaCartaQueNoTiene() throws CartaNoEncontrada {
        Jugador jugador = new Jugador("Juan", "1234");

        assertThrows(CartaNoEncontrada.class, () -> {
            jugador.eliminarCarta(CartasDisponibles.ALQUIMISTA.nombre);
        });
    }

    @Test 
    void testJugadorAgregaUnMazo() throws MazoInvalido, MazoExistente, CartaNoEncontrada, DineroInsuficiente {
        Jugador jugador = new Jugador("Juan", "1234");

        jugador.depositarDinero(1000);

        for (int i = 0; i < 40; i++) {
            jugador.comprarCarta(CartasDisponibles.AGUA);
        }

        HashMap<String, Integer> cartas = new HashMap<>();
        cartas.put(CartasDisponibles.AGUA.nombre, 40);
        
        Mazo mazo = new Mazo(cartas);

        jugador.agregarMazo("Mazo modo uno", mazo);

        assertEquals(jugador.getMazos().size(), 1);
    }

    @Test
    void testJugadorAgregaUnMazoSinCartasSuficientes() throws MazoInvalido {
        Jugador jugador = new Jugador("Juan", "1234");

        HashMap<String, Integer> cartas = new HashMap<>();
        cartas.put(CartasDisponibles.AGUA.nombre, 40);

        Mazo mazo = new Mazo(cartas);

        assertThrows(CartaNoEncontrada.class, () -> {
            jugador.agregarMazo("Mazo modo uno", mazo);
        });

        assertEquals(jugador.getMazos().size(), 0);
    }

    @Test 
    void testJugadorIntentaAgregaUnMazoConMismoNombre() throws MazoInvalido, MazoExistente, CartaNoEncontrada, DineroInsuficiente {
        Jugador jugador = new Jugador("Juan", "1234");

        jugador.depositarDinero(1000);

        for (int i = 0; i < 40; i++) {
            jugador.comprarCarta(CartasDisponibles.AGUA);
        }

        HashMap<String, Integer> cartas = new HashMap<>();
        cartas.put(CartasDisponibles.AGUA.nombre, 40);

        Mazo mazo = new Mazo(cartas);

        jugador.agregarMazo("Mazo modo uno", mazo);

        assertThrows(MazoExistente.class, () -> {
            jugador.agregarMazo("Mazo modo uno", mazo);
        });

        assertEquals(jugador.getCartas().size(), 1);
        assertEquals(jugador.getMazos().size(), 1);
    }

    @Test 
    void testJugadorEliminaUnMazo() throws MazoInvalido, MazoExistente, CartaNoEncontrada, DineroInsuficiente {
        Jugador jugador = new Jugador("Juan", "1234");

        jugador.depositarDinero(1000);

        for (int i = 0; i < 40; i++) {
            jugador.comprarCarta(CartasDisponibles.AGUA);
        }

        HashMap<String, Integer> cartas = new HashMap<>();
        cartas.put(CartasDisponibles.AGUA.nombre, 40);

        Mazo mazo = new Mazo(cartas);

        jugador.agregarMazo("Mazo modo uno", mazo);
        jugador.eliminarMazo("Mazo modo uno");

        assertEquals(jugador.getMazos().size(), 0);
        assertEquals(jugador.getCartas().size(), 1);
    }
}