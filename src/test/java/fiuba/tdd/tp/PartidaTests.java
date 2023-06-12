package fiuba.tdd.tp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import fiuba.tdd.tp.Excepciones.MazoInvalido;
import fiuba.tdd.tp.Excepciones.PartidaInvalida;
import fiuba.tdd.tp.carta.CartasDisponibles;
import fiuba.tdd.tp.mazo.Mazo;
import fiuba.tdd.tp.modo.Modo;
import fiuba.tdd.tp.modo.Modo1;
import fiuba.tdd.tp.modo.Modo2;
import fiuba.tdd.tp.partida.Partida;
import fiuba.tdd.tp.tablero.Tablero;
import fiuba.tdd.tp.zona.Zona;
import fiuba.tdd.tp.zona.ZonaMano;

@SpringBootTest
public class PartidaTests {

    Modo modoUno;
    Modo modoDos;
    Mazo mazoModoUno;
    Mazo mazoModoDos;

    @BeforeEach
    public void setUp() throws MazoInvalido {
		HashMap<String, Integer> cartasModoUno = new HashMap<>();
        HashMap<String, Integer> cartasModoDos = new HashMap<>();
        
        modoUno = new Modo1();
		modoDos = new Modo2();

		cartasModoUno.put(CartasDisponibles.AGUA.nombre, 40);
        mazoModoUno = new Mazo(cartasModoUno, modoUno);

		cartasModoDos.put(CartasDisponibles.AGUA.nombre, 56);
        cartasModoDos.put(CartasDisponibles.ALQUIMISTA.nombre, 4);
		mazoModoDos = new Mazo(cartasModoDos, modoDos);
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
    public void testPartidaIniciaUnTurno() throws PartidaInvalida {
        Partida partida = new Partida(modoUno, "jugador1", "jugador2", mazoModoUno, mazoModoUno);
        
        partida.iniciarPartida();

        Tablero tableroJugador1 = partida.tableroJugador("jugador1");

        Zona unaZona = new ZonaMano();
        
        assertEquals(tableroJugador1.cartasEnZona(unaZona.getClass().getSimpleName()).size(), 6);
    }
}