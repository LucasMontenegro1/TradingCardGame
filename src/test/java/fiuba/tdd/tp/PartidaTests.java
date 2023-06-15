package fiuba.tdd.tp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import fiuba.tdd.tp.Excepciones.MazoInvalido;
import fiuba.tdd.tp.Excepciones.MovimientoInvalido;
import fiuba.tdd.tp.Excepciones.PartidaInvalida;
import fiuba.tdd.tp.carta.CartasDisponibles;
import fiuba.tdd.tp.jugador.Mazo;
import fiuba.tdd.tp.modo.Modo;
import fiuba.tdd.tp.modo.Modo1;
import fiuba.tdd.tp.modo.Modo2;
import fiuba.tdd.tp.partida.Partida;
import fiuba.tdd.tp.tablero.Tablero;
import fiuba.tdd.tp.zona.ZonaMano;

@SpringBootTest
public class PartidaTests {

    Modo modoUno = new Modo1();
	Modo modoDos = new Modo2();
    Mazo mazoModoUno;
    Mazo mazoModoDos;

    @BeforeEach
    public void setUp() throws MazoInvalido {
		HashMap<String, Integer> cartasModoUno = new HashMap<>();
        HashMap<String, Integer> cartasModoDos = new HashMap<>();
        
		cartasModoUno.put(CartasDisponibles.AGUA.nombre, 40);
        mazoModoUno = new Mazo(cartasModoUno);

		cartasModoDos.put(CartasDisponibles.AGUA.nombre, 56);
        cartasModoDos.put(CartasDisponibles.ALQUIMISTA.nombre, 4);
		mazoModoDos = new Mazo(cartasModoDos);
    }
    
    @Test
    public void testSeIniciaUnaNuevaPartida() throws PartidaInvalida {
        Partida partida = new Partida(modoUno, "jugador1", "jugador2", mazoModoUno, mazoModoUno);

        assert partida instanceof Partida;
    }

    @Test
    public void testSeIniciaUnaPartidaInvalida() throws PartidaInvalida {
        assertThrows(PartidaInvalida.class, () -> {
            new Partida(modoUno, "jugador1", "jugador2", mazoModoUno, mazoModoDos);
        });
    }

    @Test 
    public void testPartidaIniciaUnTurno() throws PartidaInvalida, MovimientoInvalido {
        Partida partida = new Partida(modoUno, "jugador1", "jugador2", mazoModoUno, mazoModoUno);
        
        partida.iniciarPartida();

        Tablero tableroJugador1 = partida.tableroJugador("jugador1");
        
        assertEquals(tableroJugador1.cartasEnZona(ZonaMano.class.getSimpleName()).size(), 6);
    }
}