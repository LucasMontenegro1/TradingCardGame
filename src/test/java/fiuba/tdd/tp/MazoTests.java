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
        cartas.put(CartasDisponibles.ALQUMISTA.nombreCarta(), 2);
        
        Modo modoMazo = new Modo1();

        assertThrows(MazoInvalido.class, () -> {
            new Mazo(cartas, modoMazo);
        });
    }
      
    @Test
    void testCreacionMazoModoDosInvalido() throws MazoInvalido {

        HashMap<String, Integer> cartas = new HashMap<>();
        cartas.put(CartasDisponibles.ALQUMISTA.nombreCarta(), 2);
        
        Modo modoMazo = new Modo2();

        assertThrows(MazoInvalido.class, () -> {
            new Mazo(cartas, modoMazo);
        });
    }

    @Test
    void testCreacionMazoModoUnoValido() throws MazoInvalido {

        HashMap<String, Integer> cartas = new HashMap<>();
        cartas.put(CartasDisponibles.ENERGIA.nombreCarta(), 40);
        
        Modo modoMazo = new Modo1();

        Mazo mazo = new Mazo(cartas, modoMazo);

        assert mazo instanceof Mazo;
    }

    @Test
    void testCreacionMazoModoDosValido() throws MazoInvalido {

        HashMap<String, Integer> cartas = new HashMap<>();
        cartas.put(CartasDisponibles.ENERGIA.nombreCarta(), 60);
        
        Modo modoMazo = new Modo2();

        Mazo mazo = new Mazo(cartas, modoMazo);

        assert mazo instanceof Mazo;
    }

    /*
    @Test
    void testAgregarUnaCartaAUnMazo(){

        Mazo mazo = new Mazo();

        Tipo tipo = Tipo.Criatura;
        String nombre = "Carta1";
        Zona zona = new ZonaMano();

        Carta carta = new Carta(tipo, nombre, zona);
		
		mazo.agregarCarta(carta);

        assertEquals(1, mazo.cartas.size());
    }

    @Test
    void testTomarUnaCartaDeUnMazoVacio(){
        Mazo mazo = new Mazo();

        assert mazo.tomar_carta() == null;
    }

    @Test
    void testTomarUnaCartaDeUnMazoConCartas(){
        Mazo mazo = new Mazo();

        Tipo tipo = Tipo.Criatura;
        String nombre = "Carta1";
        Zona zona = new ZonaMano();

        Carta carta = new Carta(tipo, nombre, zona);
		
		mazo.agregarCarta(carta);

        assert mazo.tomar_carta() instanceof Carta;
    }
    */
}
