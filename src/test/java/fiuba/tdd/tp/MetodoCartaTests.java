package fiuba.tdd.tp;

import fiuba.tdd.tp.carta.Metodos.*;
import fiuba.tdd.tp.Excepciones.MazoInvalido;
import fiuba.tdd.tp.carta.Carta;
import fiuba.tdd.tp.carta.CartasDisponibles;
import fiuba.tdd.tp.carta.Energia;
import fiuba.tdd.tp.carta.Tipo;
import fiuba.tdd.tp.etapa.EtapaDeAtaque;
import fiuba.tdd.tp.etapa.EtapaPrincipal;
import fiuba.tdd.tp.mazo.Mazo;
import fiuba.tdd.tp.modo.Modo;
import fiuba.tdd.tp.modo.Modo1;
import fiuba.tdd.tp.modo.Modo2;
import fiuba.tdd.tp.tablero.Tablero;
import fiuba.tdd.tp.zona.ZonaCombate;
import fiuba.tdd.tp.zona.ZonaMano;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Deque;

import org.junit.jupiter.api.BeforeEach;

public class MetodoCartaTests {

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
    public void atacarYTomarCarta() {
        MetodoCarta atacar = new Atacar(10);
        MetodoCarta tomarCarta = new TomarCarta(1, Tipo.Criatura);

        MetodoCarta efecto = new MetodoCartaCompuesto(atacar, tomarCarta);
       
        // efecto.ejecutar(null, null, null, null, null);
        assertTrue(efecto.esAplicableA(new EtapaDeAtaque(), new ZonaCombate(),null));
    }

    @Test
    public void testTransferirEnergiaCriterioDeAplicacion(){
        MetodoCarta transferirEnergia =  new TransferirEnergia();

        assertTrue(transferirEnergia.esAplicableA(new EtapaPrincipal(), new ZonaCombate(),null));
    }

    @Test 
    public void testTransferenciaDeEnergia(){
        MetodoCarta transferirEnergia =  new TransferirEnergia();

        Tablero enJuego = new Tablero("Jugador 1", mazoModoUno);

        Tablero contrincante = new Tablero("Jugador 2", mazoModoUno);

        Integer aguaInicialEnJuego = enJuego.energiaAgua();

        contrincante.aumentarEnergia(Energia.Agua, 2);

        Integer aguaInicialContrincante = contrincante.energiaAgua();
        
        transferirEnergia.ejecutar(enJuego, contrincante, null, null, null, Energia.Agua);
        
        assertEquals(aguaInicialEnJuego + 1, enJuego.energiaAgua());
        assertEquals(aguaInicialContrincante - 1, contrincante.energiaAgua());
    }

    @Test
    public void testTransferirCartaCriterioDeAplicacion(){
        MetodoCarta transferirCarta = new TransferirCarta();

        assertTrue(transferirCarta.esAplicableA(new EtapaPrincipal(), new ZonaMano(),null));
    }

    @Test 
    public void testTransferirCarta(){
        Tablero enJuego = new Tablero("Jugador 1", mazoModoDos);

        Tablero contrincante = new Tablero("Jugador 2", mazoModoDos);

        MetodoCarta transfeririCarta = new TransferirCarta();

        Integer cantInicialEnJuego = enJuego.cartas.size();

        Integer cantInicialContrincante = contrincante.cartas.size();

        Random random = new Random();
        Carta carta = contrincante.cartas.get(random.nextInt(contrincante.cartas.size()));

        transfeririCarta.ejecutar(enJuego, contrincante, null, null, carta, null);       

        assertEquals(cantInicialEnJuego + 1, enJuego.cartas.size());
        assertEquals(cantInicialContrincante - 1, contrincante.cartas.size());
    }

    @Test
    public void testSacrificioCriterioDeAplicacion(){
        MetodoCarta sacrificio = new Sacrificio();

        assertTrue(sacrificio.esAplicableA(new EtapaPrincipal(), new ZonaMano(),null));
    }

    @Test 
    public void testSacrificio(){
        

    }

    @Test
    public void testTomarCartaCriaturaCriterioDeAplicacion(){
        MetodoCarta tomarCarta = new TomarCarta(2,Tipo.Criatura);

        assertTrue(tomarCarta.esAplicableA(new EtapaDeAtaque(), new ZonaCombate(),null));
    }

    @Test
    public void testTomarCartaArtefactoCriterioDeAplicacion(){
        MetodoCarta tomarCarta = new TomarCarta(2,Tipo.Artefacto);

        assertTrue(tomarCarta.esAplicableA(new EtapaPrincipal(), new ZonaCombate(),null));
    }

    @Test 
    public void testTomarCarta(){

        Integer cantidad = 4;

        MetodoCarta tomarCarta = new TomarCarta(cantidad, Tipo.Artefacto);

        Tablero enJuego = new Tablero("Jugador 1", mazoModoDos);

        ArrayList<Carta> cartas = enJuego.cartasEnZona(null);
        
        Integer cantCartasInicial = cartas.size();

        tomarCarta.ejecutar(enJuego, null, null, null, null, null);

        assertEquals(cantCartasInicial - cantidad, enJuego.cartasEnZona(null).size());
    }

    @Test
    public void testMetodoImpedirEsAplicableLuegoDeActivarUnaReaccion() {
        MetodoCarta impedir = new Impedir();

        Deque<MetodoCarta> metodos = new ArrayDeque<MetodoCarta>();
        MetodoCarta resonancia = new Resonancia(Tipo.Reaccion);

        metodos.push(resonancia);

        Boolean esAplicable = impedir.esAplicableA(new EtapaPrincipal(), new ZonaCombate(), metodos);

        assertEquals(metodos.size(), 1);
        assertEquals(esAplicable, true);
    }

    @Test
    public void testMetodoImpedirSeEjecutaCorrectamente() {

        MetodoCarta impedir = new Impedir();

        Deque<MetodoCarta> metodos = new ArrayDeque<MetodoCarta>();
        MetodoCarta resonancia = new Resonancia(Tipo.Reaccion);

        metodos.push(resonancia);
        metodos.push(resonancia);
        metodos.push(resonancia);
        metodos.push(resonancia);
        metodos.push(resonancia);

        impedir.ejecutar(null, null, metodos, null, null, null);

        assertEquals(metodos.size(), 4);
        assertEquals(metodos.pop(), resonancia);
        assertEquals(metodos.pop(), resonancia);
        assertEquals(metodos.pop(), resonancia);
        assertEquals(metodos.pop(), resonancia);
    }
}
