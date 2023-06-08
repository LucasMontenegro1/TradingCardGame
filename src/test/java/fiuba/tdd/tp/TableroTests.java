package fiuba.tdd.tp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import fiuba.tdd.tp.Excepciones.MazoInvalido;
import fiuba.tdd.tp.carta.CartasDisponibles;
import fiuba.tdd.tp.mazo.Mazo;
import fiuba.tdd.tp.modo.Modo;
import fiuba.tdd.tp.modo.Modo1;
import fiuba.tdd.tp.modo.Modo2;
import fiuba.tdd.tp.tablero.Tablero;

@SpringBootTest
public class TableroTests {

    private Mazo mazoModoUno;
	private Mazo mazoModoDos;

    @BeforeEach
    public void setUp() throws MazoInvalido {
		HashMap<String, Integer> cartas = new HashMap<>();
        
        Modo modoUno = new Modo1();
		Modo modoDos = new Modo2();

		cartas.put(CartasDisponibles.ENERGIA.nombreCarta(), 40);
        mazoModoUno = new Mazo(cartas, modoUno);

		cartas.put(CartasDisponibles.ENERGIA.nombreCarta(), 60);
		mazoModoDos = new Mazo(cartas, modoDos);
    }
    
    @Test
    void testCreacionDeTablero() {
        Tablero tablero = new Tablero("jugador", mazoModoUno); 
        assert tablero instanceof Tablero;
    }

    @Test
    void testTableroSeCreaCorrectamenteEnUnaPartidaModoUno() {
        Tablero tablero = new Tablero("jugador", mazoModoUno); 
        assertEquals("jugador", tablero.usuario);
        assertEquals(40, tablero.cartas.size());        
    }

    
    @Test
    void testTableroSeCreaCorrectamenteEnUnaPartidaModoDos() {
        Tablero tablero = new Tablero("jugador", mazoModoDos); 
        assertEquals("jugador", tablero.usuario);
        assertEquals(60, tablero.cartas.size());        
    }

    @Test
    void testTableroSeCreaCorrectamenteYTieneVeintePuntosDeVidaPorSerModoUno() {
        Tablero tablero = new Tablero("jugador", mazoModoUno);
        assertEquals(20, tablero.puntos);   
    }

    @Test
    void testTableroSeCreaCorrectamenteYTieneCeroPuntosDeVidaPorSerModoDos() {
        Tablero tablero = new Tablero("jugador", mazoModoDos); 
        assertEquals(0, tablero.puntos);        
    }

}
