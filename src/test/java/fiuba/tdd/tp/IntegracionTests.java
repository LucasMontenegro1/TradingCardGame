package fiuba.tdd.tp;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import fiuba.tdd.tp.Excepciones.MazoInvalido;
import fiuba.tdd.tp.carta.Carta;
import fiuba.tdd.tp.carta.CartasDisponibles;
import fiuba.tdd.tp.mazo.Mazo;
import fiuba.tdd.tp.modo.Modo;
import fiuba.tdd.tp.modo.Modo1;
import fiuba.tdd.tp.modo.Modo2;
import fiuba.tdd.tp.tablero.Tablero;

@SpringBootTest
public class IntegracionTests {

    private Mazo mazoModoUno;
	private Mazo mazoModoDos;

    @BeforeEach
    public void setUp() throws MazoInvalido {
		HashMap<String, Integer> cartasModoUno = new HashMap<>();
        HashMap<String, Integer> cartasModoDos = new HashMap<>();
        
        Modo modoUno = new Modo1();
		Modo modoDos = new Modo2();

		cartasModoUno.put(CartasDisponibles.AGUA.nombre, 40);
        mazoModoUno = new Mazo(cartasModoUno, modoUno);

		cartasModoDos.put(CartasDisponibles.AGUA.nombre, 60);
		mazoModoDos = new Mazo(cartasModoDos, modoDos);
    }
    
    @Test
    void testCartaEnergiaAumentaEnergiaAgua() {
        Carta carta = new Carta(CartasDisponibles.AGUA); 
        Tablero tablero = new Tablero("jugador", mazoModoUno); 
        
        
    }
}
