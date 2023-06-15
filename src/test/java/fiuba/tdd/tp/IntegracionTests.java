package fiuba.tdd.tp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import fiuba.tdd.tp.Excepciones.CartaNoActivable;
import fiuba.tdd.tp.Excepciones.CartaNoEncontrada;
import fiuba.tdd.tp.Excepciones.EnergiaInsuficiente;
import fiuba.tdd.tp.Excepciones.MazoInvalido;
import fiuba.tdd.tp.Excepciones.MovimientoInvalido;
import fiuba.tdd.tp.Excepciones.PartidaInvalida;
import fiuba.tdd.tp.Excepciones.ZonaLlena;
import fiuba.tdd.tp.carta.CartasDisponibles;
import fiuba.tdd.tp.carta.Energia;
import fiuba.tdd.tp.jugador.Mazo;
import fiuba.tdd.tp.jugador.Tablero;
import fiuba.tdd.tp.modo.Modo;
import fiuba.tdd.tp.modo.Modo1;
import fiuba.tdd.tp.modo.Modo2;
import fiuba.tdd.tp.partida.Partida;
import fiuba.tdd.tp.zona.ZonaArtefacto;

@SpringBootTest
public class IntegracionTests {
    
    Modo modoUno = new Modo1();
	Modo modoDos = new Modo2();
    Mazo mazoModoUno;
    Mazo mazoModoDos;

    @BeforeEach
    public void setUp() throws MazoInvalido {
		HashMap<String, Integer> cartasModoUno = new HashMap<>();
        HashMap<String, Integer> cartasModoDos = new HashMap<>();
        
        mazoModoUno = new Mazo(cartasModoUno);
		mazoModoDos = new Mazo(cartasModoDos);
    }

    private void moverAEtapa(Partida partida, String jugador, String etapa) throws MovimientoInvalido {
        partida.terminarEtapa();
        while (!partida.jugadorEnTurno(jugador) || !(partida.turnoEnProceso().etapaActual().getClass().getSimpleName().equals(etapa))) {
            partida.terminarEtapa();
        }
    }

    @Test 
    public void testMetodoDrenar() throws MovimientoInvalido, PartidaInvalida, CartaNoEncontrada, EnergiaInsuficiente, ZonaLlena, CartaNoActivable {

        mazoModoUno.agregarCarta(CartasDisponibles.AGUA.nombre, 40);
        mazoModoUno.agregarCarta(CartasDisponibles.DRENAR.nombre, 3);

        Partida partida = new Partida(modoUno, "jugador1", "jugador2", mazoModoUno, mazoModoUno);

        ArrayList<String> listaOrdenada = new ArrayList<>();
        listaOrdenada.add(CartasDisponibles.AGUA.nombre);
        listaOrdenada.add(CartasDisponibles.AGUA.nombre);
        listaOrdenada.add(CartasDisponibles.DRENAR.nombre);
        listaOrdenada.add(CartasDisponibles.DRENAR.nombre);
        listaOrdenada.add(CartasDisponibles.DRENAR.nombre);
        for (int i = 0; i < 38; i++) {
            listaOrdenada.add(CartasDisponibles.AGUA.nombre);
        }
        
        partida.tablero1.reordenarMazo(listaOrdenada);
        partida.iniciarPartida();
        
        Tablero tablero = partida.tableroEnTurno();
        moverAEtapa(partida, "jugador1", "EtapaPrincipal");
        
        partida.invocarCarta("jugador1", CartasDisponibles.AGUA.nombre, ZonaArtefacto.class.getSimpleName());
        partida.invocarCarta("jugador1", CartasDisponibles.AGUA.nombre, ZonaArtefacto.class.getSimpleName());
        
        moverAEtapa(partida, "jugador1", "EtapaPrincipal");

        partida.activarCarta(tablero.cartas.get(0), 0, "jugador1", null, null);
        partida.activarCarta(tablero.cartas.get(1), 0, "jugador1", null, null);
        assertEquals(2, partida.tablero1.energiaAgua());

        partida.tablero2.aumentarEnergia(Energia.Fuego, 15);
        partida.activarCarta(tablero.cartas.get(2), 0, "jugador2", null, null);

        moverAEtapa(partida, "jugador1", "EtapaPrincipal");
        moverAEtapa(partida, "jugador1", "EtapaPrincipal");
        moverAEtapa(partida, "jugador1", "EtapaPrincipal");

        assertEquals(12, partida.tablero2.energiaFuego());
    }
}
