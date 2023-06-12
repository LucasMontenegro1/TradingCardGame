package fiuba.tdd.tp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import fiuba.tdd.tp.Excepciones.CartaNoEncontrada;
import fiuba.tdd.tp.Excepciones.DineroInsuficiente;
import fiuba.tdd.tp.carta.CartasDisponibles;
import fiuba.tdd.tp.jugador.Jugador;
import fiuba.tdd.tp.jugador.Mercado;

@SpringBootTest
public class MercadoTests {

    Mercado mercado;

    @BeforeEach
    public void setUp() {
        mercado = new Mercado();
    }

    @Test
    void testJugadorAgregaUnIntercambio() throws DineroInsuficiente, CartaNoEncontrada {
        Jugador jugador = new Jugador("Jugador", "1234");
        mercado.agregarIntercambiador(jugador);

        jugador.depositarDinero(100);
        jugador.comprarCarta(CartasDisponibles.ALQUIMISTA);
        jugador.comprarCarta(CartasDisponibles.ALQUIMISTA);

        mercado.realizarIntercambio(jugador, CartasDisponibles.ALQUIMISTA.nombre, CartasDisponibles.AGUA.nombre);
        mercado.realizarIntercambio(jugador, CartasDisponibles.ALQUIMISTA.nombre, CartasDisponibles.AGUA.nombre);

        HashMap<String, ArrayList<String>> intercambios = jugador.intercambiosAbiertos();
        ArrayList<String> intercambiosAlquimista = intercambios.get(CartasDisponibles.ALQUIMISTA.nombre);

        assertEquals(intercambios.size(), 1);
        assertEquals(intercambiosAlquimista.size(), 2);
    }

    @Test
    void testJugadorAgregaUnIntercambioConCartaNoDisponible() throws DineroInsuficiente {
        Jugador jugador = new Jugador("Jugador", "1234");
        mercado.agregarIntercambiador(jugador);

        assertThrows(CartaNoEncontrada.class, () -> {
            mercado.realizarIntercambio(jugador, CartasDisponibles.ALQUIMISTA.nombre, CartasDisponibles.AGUA.nombre);
        });
    }

    @Test
    void testJugadorEliminaUnIntercambio() throws DineroInsuficiente, CartaNoEncontrada {
        Jugador jugador = new Jugador("Jugador", "1234");
        mercado.agregarIntercambiador(jugador);

        jugador.depositarDinero(100);
        jugador.comprarCarta(CartasDisponibles.ALQUIMISTA);

        mercado.realizarIntercambio(jugador, CartasDisponibles.ALQUIMISTA.nombre, CartasDisponibles.AGUA.nombre);
        mercado.eliminarIntercambio(jugador, CartasDisponibles.ALQUIMISTA.nombre, CartasDisponibles.AGUA.nombre);

        HashMap<String, ArrayList<String>> intercambios = jugador.intercambiosAbiertos();

        assertEquals(intercambios.size(), 0);
    }

    @Test
    void testJugadorEliminaUnaCartaDeseadaDeUnIntercambio() throws DineroInsuficiente, CartaNoEncontrada {
        Jugador jugador = new Jugador("Jugador", "1234");
        mercado.agregarIntercambiador(jugador);

        jugador.depositarDinero(100);
        jugador.comprarCarta(CartasDisponibles.ALQUIMISTA);
        jugador.comprarCarta(CartasDisponibles.ALQUIMISTA);

        mercado.realizarIntercambio(jugador, CartasDisponibles.ALQUIMISTA.nombre, CartasDisponibles.AGUA.nombre);
        mercado.realizarIntercambio(jugador, CartasDisponibles.ALQUIMISTA.nombre, CartasDisponibles.AGUA.nombre);
        mercado.eliminarIntercambio(jugador, CartasDisponibles.ALQUIMISTA.nombre, CartasDisponibles.AGUA.nombre);

        HashMap<String, ArrayList<String>> intercambios = jugador.intercambiosAbiertos();
        ArrayList<String> intercambiosAlquimista = intercambios.get(CartasDisponibles.ALQUIMISTA.nombre);

        assertEquals(intercambios.size(), 1);
        assertEquals(intercambiosAlquimista.size(), 1);
    }

    @Test
    void testJugadorNoPuedeOfrecerLaMismaCartaEnDosVeces() throws DineroInsuficiente, CartaNoEncontrada {
        Jugador jugador = new Jugador("Jugador", "1234");
        mercado.agregarIntercambiador(jugador);

        jugador.depositarDinero(100);
        jugador.comprarCarta(CartasDisponibles.ALQUIMISTA);

        mercado.realizarIntercambio(jugador, CartasDisponibles.ALQUIMISTA.nombre, CartasDisponibles.AGUA.nombre);
        assertThrows(CartaNoEncontrada.class, () -> {
            mercado.realizarIntercambio(jugador, CartasDisponibles.ALQUIMISTA.nombre, CartasDisponibles.AGUA.nombre);
        });
    }
}
