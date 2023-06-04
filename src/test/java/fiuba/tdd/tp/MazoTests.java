package fiuba.tdd.tp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import fiuba.tdd.tp.carta.Carta;
import fiuba.tdd.tp.carta.Tipo;
import fiuba.tdd.tp.mazo.Mazo;
import fiuba.tdd.tp.zona.Zona;
import fiuba.tdd.tp.zona.ZonaMano;

@SpringBootTest
public class MazoTests {
   
    @Test
    void testCreacionMazo(){

        Mazo mazo = new Mazo();

        assert mazo instanceof Mazo;
    }

    @Test
    void testAgregarUnaCartaAUnMazo(){

        Mazo mazo = new Mazo();

        Tipo tipo = Tipo.Criatura;
        String nombre = "Carta1";
        String descripcion = "Descripción de la carta";
        Zona zona = new ZonaMano();

        Carta carta = new Carta(tipo, nombre, descripcion, zona);
		
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
        String descripcion = "Descripción de la carta";
        Zona zona = new ZonaMano();

        Carta carta = new Carta(tipo, nombre, descripcion, zona);
		
		mazo.agregarCarta(carta);

        assert mazo.tomar_carta() instanceof Carta;
    }
    
}
