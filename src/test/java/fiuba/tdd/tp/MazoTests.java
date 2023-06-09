package fiuba.tdd.tp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import fiuba.tdd.tp.Excepciones.MazoInvalido;
import fiuba.tdd.tp.carta.CartasDisponibles;
import fiuba.tdd.tp.mazo.Mazo;
import fiuba.tdd.tp.modo.Modo;
import fiuba.tdd.tp.modo.Modo1;
import fiuba.tdd.tp.modo.Modo2;

@SpringBootTest
public class MazoTests {
   
    @Test
    void testCreacionMazoModoUnoInvalido() throws MazoInvalido {

        HashMap<String, Integer> cartas = new HashMap<>();
        cartas.put(CartasDisponibles.ALQUMISTA.nombre, 2);
        
        Modo modoMazo = new Modo1();

        assertThrows(MazoInvalido.class, () -> {
            new Mazo(cartas, modoMazo);
        });
    }
      
    @Test
    void testCreacionMazoModoDosInvalido() throws MazoInvalido {

        HashMap<String, Integer> cartas = new HashMap<>();
        cartas.put(CartasDisponibles.ALQUMISTA.nombre, 2);
        
        Modo modoMazo = new Modo2();

        assertThrows(MazoInvalido.class, () -> {
            new Mazo(cartas, modoMazo);
        });
    }

    @Test
    void testCreacionMazoModoUnoValido() throws MazoInvalido {

        HashMap<String, Integer> cartas = new HashMap<>();
        cartas.put(CartasDisponibles.AGUA.nombre, 40);
        
        Modo modoMazo = new Modo1();

        Mazo mazo = new Mazo(cartas, modoMazo);

        assert mazo instanceof Mazo;
    }

    @Test
    void testCreacionMazoModoDosValido() throws MazoInvalido {

        HashMap<String, Integer> cartas = new HashMap<>();
        cartas.put(CartasDisponibles.AGUA.nombre, 60);
        
        Modo modoMazo = new Modo2();

        Mazo mazo = new Mazo(cartas, modoMazo);

        assert mazo instanceof Mazo;
    }

    @Test
    void testAgregarCartaAMazoModoUno() throws MazoInvalido {
        HashMap<String, Integer> cartas = new HashMap<>();
        cartas.put(CartasDisponibles.AGUA.nombre, 40);
        
        Modo modoMazo = new Modo1();

        Mazo mazo = new Mazo(cartas, modoMazo);

        mazo.agregarCarta(CartasDisponibles.ALQUMISTA.nombre); 

        assertEquals(mazo.cantdCartas(), 41);
    }

    @Test
    void testAgregarCartaAMazoModoUnoNoSePuede() throws MazoInvalido {
        HashMap<String, Integer> cartas = new HashMap<>();
        cartas.put(CartasDisponibles.AGUA.nombre, 60);
        
        Modo modoMazo = new Modo1();

        Mazo mazo = new Mazo(cartas, modoMazo);

        assertThrows(MazoInvalido.class, () -> {
            mazo.agregarCarta(CartasDisponibles.ALQUMISTA.nombre); 
        });

        assertEquals(mazo.cantdCartas(), 60);
    }

    @Test
    void testAgregarCartaAMazoModoDosNoEsPosible() throws MazoInvalido {
        HashMap<String, Integer> cartas = new HashMap<>();
        cartas.put(CartasDisponibles.AGUA.nombre, 60);
        
        Modo modoMazo = new Modo2();

        Mazo mazo = new Mazo(cartas, modoMazo);

        assertThrows(MazoInvalido.class, () -> {
            mazo.agregarCarta(CartasDisponibles.ALQUMISTA.nombre); 
        });

        assertEquals(mazo.cantdCartas(), 60);
    }

    @Test
    void testEliminarCartaAMazoModoUno() throws MazoInvalido {
        HashMap<String, Integer> cartas = new HashMap<>();
        cartas.put(CartasDisponibles.AGUA.nombre, 50);
        
        Modo modoMazo = new Modo1();

        Mazo mazo = new Mazo(cartas, modoMazo);

        mazo.eliminarCarta(CartasDisponibles.AGUA.nombre); 

        assertEquals(mazo.cantdCartas(), 49);
    }

    @Test
    void testEliminarCartaAMazoModoUnoNoSePuede() throws MazoInvalido {
        HashMap<String, Integer> cartas = new HashMap<>();
        cartas.put(CartasDisponibles.AGUA.nombre, 40);
        
        Modo modoMazo = new Modo1();

        Mazo mazo = new Mazo(cartas, modoMazo);

        assertThrows(MazoInvalido.class, () -> {
            mazo.eliminarCarta(CartasDisponibles.AGUA.nombre); 
        });

        assertEquals(mazo.cantdCartas(), 40);
    }

    @Test
    void testNoSePuedeEliminarUnaCartaQueNoExiste() throws MazoInvalido {
        HashMap<String, Integer> cartas = new HashMap<>();
        cartas.put(CartasDisponibles.AGUA.nombre, 50);
        
        Modo modoMazo = new Modo1();

        Mazo mazo = new Mazo(cartas, modoMazo);

        assertThrows(MazoInvalido.class, () -> {
            mazo.eliminarCarta(CartasDisponibles.ALQUMISTA.nombre); 
        });

        assertEquals(mazo.cantdCartas(), 50);
    }

    @Test
    void testEliminarCartaAMazoModoDos() throws MazoInvalido {
        HashMap<String, Integer> cartas = new HashMap<>();
        cartas.put(CartasDisponibles.AGUA.nombre, 60);
        
        Modo modoMazo = new Modo2();

        Mazo mazo = new Mazo(cartas, modoMazo);

        assertThrows(MazoInvalido.class, () -> {
            mazo.eliminarCarta(CartasDisponibles.AGUA.nombre); 
        }); 

        assertEquals(mazo.cantdCartas(), 60);
    }

}
