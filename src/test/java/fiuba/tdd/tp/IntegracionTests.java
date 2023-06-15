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
import fiuba.tdd.tp.carta.Carta;
import fiuba.tdd.tp.carta.CartasDisponibles;
import fiuba.tdd.tp.carta.Energia;
import fiuba.tdd.tp.jugador.Mazo;
import fiuba.tdd.tp.jugador.Tablero;
import fiuba.tdd.tp.modo.Modo;
import fiuba.tdd.tp.modo.Modo1;
import fiuba.tdd.tp.modo.Modo2;
import fiuba.tdd.tp.partida.Partida;
import fiuba.tdd.tp.zona.ZonaArtefacto;
import fiuba.tdd.tp.zona.ZonaCombate;

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

    @Test 
    public void testCartaResonanciaEnPilaDeEjecucion() throws MovimientoInvalido, PartidaInvalida, CartaNoEncontrada, EnergiaInsuficiente, ZonaLlena, CartaNoActivable {

        mazoModoUno.agregarCarta(CartasDisponibles.AGUA.nombre, 1);
        mazoModoUno.agregarCarta(CartasDisponibles.FUEGO.nombre, 1);
        mazoModoUno.agregarCarta(CartasDisponibles.PLANTA.nombre, 40);
        mazoModoUno.agregarCarta(CartasDisponibles.ORCO.nombre, 3);
        mazoModoUno.agregarCarta(CartasDisponibles.RESONANCIA.nombre, 3);

        Partida partida = new Partida(modoUno, "jugador1", "jugador2", mazoModoUno, mazoModoUno);

        ArrayList<String> listaOrdenada = new ArrayList<>();
        listaOrdenada.add(CartasDisponibles.PLANTA.nombre);
        listaOrdenada.add(CartasDisponibles.PLANTA.nombre);
        listaOrdenada.add(CartasDisponibles.PLANTA.nombre);    
        listaOrdenada.add(CartasDisponibles.ORCO.nombre);    
        listaOrdenada.add(CartasDisponibles.ORCO.nombre);    
        listaOrdenada.add(CartasDisponibles.ORCO.nombre);
        listaOrdenada.add(CartasDisponibles.FUEGO.nombre);
        listaOrdenada.add(CartasDisponibles.AGUA.nombre);   
        listaOrdenada.add(CartasDisponibles.RESONANCIA.nombre);
        listaOrdenada.add(CartasDisponibles.RESONANCIA.nombre);
        listaOrdenada.add(CartasDisponibles.RESONANCIA.nombre); 
        for (int i = 0; i < 37; i++) {
            listaOrdenada.add(CartasDisponibles.PLANTA.nombre);  
        }
                
        partida.tablero1.reordenarMazo(listaOrdenada);
        partida.tablero2.reordenarMazo(listaOrdenada);
        partida.iniciarPartida();
        
        moverAEtapa(partida, "jugador1", "EtapaPrincipal");
        
        partida.invocarCarta("jugador1", CartasDisponibles.PLANTA.nombre, ZonaArtefacto.class.getSimpleName());
        partida.invocarCarta("jugador1", CartasDisponibles.PLANTA.nombre, ZonaArtefacto.class.getSimpleName());
        partida.invocarCarta("jugador1", CartasDisponibles.PLANTA.nombre, ZonaArtefacto.class.getSimpleName());
        
        moverAEtapa(partida, "jugador2", "EtapaPrincipal");

        partida.invocarCarta("jugador2", CartasDisponibles.PLANTA.nombre, ZonaArtefacto.class.getSimpleName());
        partida.invocarCarta("jugador2", CartasDisponibles.PLANTA.nombre, ZonaArtefacto.class.getSimpleName());
        partida.invocarCarta("jugador2", CartasDisponibles.PLANTA.nombre, ZonaArtefacto.class.getSimpleName());

        moverAEtapa(partida, "jugador1", "EtapaPrincipal");
        
        partida.activarCarta(partida.tablero1.cartas.get(0), 0, "jugador1", null, null);
        partida.activarCarta(partida.tablero1.cartas.get(1), 0, "jugador1", null, null);
        partida.activarCarta(partida.tablero1.cartas.get(2), 0, "jugador1", null, null);

        partida.invocarCarta("jugador1", CartasDisponibles.ORCO.nombre, ZonaCombate.class.getSimpleName());
        partida.invocarCarta("jugador1", CartasDisponibles.ORCO.nombre, ZonaCombate.class.getSimpleName());
        partida.invocarCarta("jugador1", CartasDisponibles.ORCO.nombre, ZonaCombate.class.getSimpleName());

        moverAEtapa(partida, "jugador2", "EtapaPrincipal");
        
        partida.activarCarta(partida.tablero2.cartas.get(0), 0, "jugador2", null, null);
        partida.activarCarta(partida.tablero2.cartas.get(1), 0, "jugador2", null, null);
        partida.activarCarta(partida.tablero2.cartas.get(2), 0, "jugador2", null, null);

        partida.invocarCarta("jugador2", CartasDisponibles.ORCO.nombre, ZonaCombate.class.getSimpleName());
        partida.invocarCarta("jugador2", CartasDisponibles.ORCO.nombre, ZonaCombate.class.getSimpleName());
        partida.invocarCarta("jugador2", CartasDisponibles.ORCO.nombre, ZonaCombate.class.getSimpleName());

        // -------------------------------------
        
        moverAEtapa(partida, "jugador1", "EtapaPrincipal");
        moverAEtapa(partida, "jugador1", "EtapaPrincipal");
        moverAEtapa(partida, "jugador1", "EtapaPrincipal");
        partida.invocarCarta("jugador1", CartasDisponibles.FUEGO.nombre, ZonaArtefacto.class.getSimpleName());
        partida.invocarCarta("jugador1", CartasDisponibles.AGUA.nombre, ZonaArtefacto.class.getSimpleName());
        moverAEtapa(partida, "jugador1", "EtapaPrincipal");
        partida.activarCarta(partida.tablero1.cartas.get(6), 0, "jugador1", null, null);
        partida.activarCarta(partida.tablero1.cartas.get(7), 0, "jugador1", null, null);

        partida.iniciarPilaDeEjecucion();
        
        ArrayList<Carta> cartasAtacablesJugador2 = partida.tablero2.cartasAtacables();
        ArrayList<Carta> cartasObjetivosJugador2 = new ArrayList<Carta>();
        cartasObjetivosJugador2.add(cartasAtacablesJugador2.get(0));
        partida.activarCarta(partida.tablero1.cartas.get(8), 0, null, cartasObjetivosJugador2, null);

        ArrayList<Carta> cartasAtacablesJugador1 = partida.tablero1.cartasAtacables();
        ArrayList<Carta> cartasObjetivosJugador1 = new ArrayList<Carta>();
        cartasObjetivosJugador1.add(cartasAtacablesJugador1.get(0));
        cartasObjetivosJugador1.add(cartasAtacablesJugador1.get(1));
        partida.activarCarta(partida.tablero2.cartas.get(8), 0, null, cartasObjetivosJugador1, null);
        
        partida.ejecutarPila();
        
        assertEquals(partida.tablero1.cartasEnZona(ZonaCombate.class.getSimpleName()).size(),1);
        assertEquals(partida.tablero2.cartasEnZona(ZonaCombate.class.getSimpleName()).size(),3);
    }
}
