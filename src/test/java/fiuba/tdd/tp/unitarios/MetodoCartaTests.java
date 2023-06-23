package fiuba.tdd.tp.unitarios;

import fiuba.tdd.tp.model.Excepciones.MazoInvalido;
import fiuba.tdd.tp.model.Excepciones.MovimientoInvalido;
import fiuba.tdd.tp.model.carta.Atributo;
import fiuba.tdd.tp.model.carta.Carta;
import fiuba.tdd.tp.model.carta.CartasDisponibles;
import fiuba.tdd.tp.model.carta.Energia;
import fiuba.tdd.tp.model.carta.Tipo;
import fiuba.tdd.tp.model.carta.Metodos.*;
import fiuba.tdd.tp.model.jugador.Mazo;
import fiuba.tdd.tp.model.jugador.Tablero;
import fiuba.tdd.tp.model.modo.Modo;
import fiuba.tdd.tp.model.modo.Modo1;
import fiuba.tdd.tp.model.modo.Modo2;
import fiuba.tdd.tp.model.partida.Ejecucion;
import fiuba.tdd.tp.model.turno.EtapaDeAtaque;
import fiuba.tdd.tp.model.turno.EtapaPrincipal;
import fiuba.tdd.tp.model.zona.ZonaCombate;
import fiuba.tdd.tp.model.zona.ZonaMano;

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
    private Modo modoUno = new Modo1();
    private Modo modoDos = new Modo2();

    @BeforeEach
    public void setUp() throws MazoInvalido {
		HashMap<String, Integer> cartasModoUno = new HashMap<>();
        HashMap<String, Integer> cartasModoDos = new HashMap<>();

		cartasModoUno.put(CartasDisponibles.AGUA.nombre, 40);
        mazoModoUno = new Mazo(cartasModoUno);

		cartasModoDos.put(CartasDisponibles.AGUA.nombre, 60);
		mazoModoDos = new Mazo(cartasModoDos);
    }

    @Test
    public void atacarYTomarCarta() {
        MetodoCarta atacar = new Atacar(10);
        MetodoCarta tomarCarta = new TomarCarta(1, Tipo.Criatura);

        MetodoCarta efecto = new MetodoCartaCompuesto(atacar, tomarCarta);
       
        assertTrue(efecto.esAplicableA(new EtapaDeAtaque(), new ZonaCombate(), null, null, null));
    }

    @Test
    public void testTransferirEnergiaCriterioDeAplicacion(){
        MetodoCarta transferirEnergia =  new TransferirEnergia();

        assertTrue(transferirEnergia.esAplicableA(new EtapaPrincipal(), new ZonaCombate(),null, null, null));
    }

    @Test 
    public void testTransferenciaDeEnergia() throws MovimientoInvalido{
        MetodoCarta transferirEnergia =  new TransferirEnergia();

        Tablero enJuego = new Tablero("Jugador 1", mazoModoUno, modoUno);

        Tablero contrincante = new Tablero("Jugador 2", mazoModoUno, modoUno);

        Integer aguaInicialEnJuego = enJuego.energiaAgua();

        contrincante.aumentarEnergia(Energia.Agua, 2);

        Integer aguaInicialContrincante = contrincante.energiaAgua();
        
        transferirEnergia.ejecutar(enJuego, contrincante, null, null, null, null, Energia.Agua);
        
        assertEquals(aguaInicialEnJuego + 1, enJuego.energiaAgua());
        assertEquals(aguaInicialContrincante - 1, contrincante.energiaAgua());
    }

    @Test
    public void testTransferirCartaCriterioDeAplicacion(){
        MetodoCarta transferirCarta = new TransferirCarta();

        assertTrue(transferirCarta.esAplicableA(new EtapaPrincipal(), new ZonaMano(),null, null, null));
    }

    @Test 
    public void testTransferirCarta() throws MovimientoInvalido{
        Tablero enJuego = new Tablero("Jugador 1", mazoModoDos, modoDos);
        Tablero contrincante = new Tablero("Jugador 2", mazoModoDos, modoDos);

        enJuego.iniciarTablero();
        contrincante.iniciarTablero();

        MetodoCarta transfeririCarta = new TransferirCarta();

        Integer cantInicialEnJuego = enJuego.cartas.size();

        Integer cantInicialContrincante = contrincante.cartas.size();

        Random random = new Random();
        Carta carta = contrincante.cartas.get(random.nextInt(contrincante.cartas.size()));
        ArrayList<Carta> cartas = new ArrayList<Carta>();
        cartas.add(carta);

        transfeririCarta.ejecutar(enJuego, contrincante, null, null, cartas, null, null);       

        assertEquals(cantInicialEnJuego + 1, enJuego.cartas.size());
        assertEquals(cantInicialContrincante - 1, contrincante.cartas.size());
    }

    @Test
    public void testSacrificioCriterioDeAplicacion(){
        MetodoCarta sacrificio = new Sacrificio();

        assertTrue(sacrificio.esAplicableA(new EtapaPrincipal(), new ZonaMano(),null, null, null));
    }

    @Test 
    public void testSacrificio() throws MovimientoInvalido{
        MetodoCarta sacrificio = new Sacrificio();

        Carta carta = new Carta(CartasDisponibles.ALQUIMISTA);
        ArrayList<Carta> cartas = new ArrayList<Carta>();
        cartas.add(carta);

        Tablero enJuego = new Tablero("Jugador 1", mazoModoUno, modoUno);
        enJuego.iniciarTablero();

        sacrificio.ejecutar(enJuego, null, null, null, cartas, null, null);

        assertEquals(3, enJuego.energiaFuego());
        assertEquals(3, enJuego.energiaPlanta());
        assertEquals(0, enJuego.energiaAgua());
    }

    @Test
    public void testTomarCartaCriaturaCriterioDeAplicacion(){
        MetodoCarta tomarCarta = new TomarCarta(2,Tipo.Criatura);

        assertTrue(tomarCarta.esAplicableA(new EtapaDeAtaque(), new ZonaCombate(),null, null, null));
    }

    @Test
    public void testTomarCartaArtefactoCriterioDeAplicacion(){
        MetodoCarta tomarCarta = new TomarCarta(2,Tipo.Artefacto);

        assertTrue(tomarCarta.esAplicableA(new EtapaPrincipal(), new ZonaCombate(),null, null, null));
    }

    @Test 
    public void testTomarCarta() throws MovimientoInvalido{

        Integer cantidad = 4;

        MetodoCarta tomarCarta = new TomarCarta(cantidad, Tipo.Artefacto);

        Tablero enJuego = new Tablero("Jugador 1", mazoModoDos, modoDos);
        enJuego.iniciarTablero();

        ArrayList<Carta> cartas = enJuego.cartasEnZona(null);
        
        Integer cantCartasInicial = cartas.size();

        tomarCarta.ejecutar(enJuego, null, null, null, null, null, null);

        assertEquals(cantCartasInicial - cantidad, enJuego.cartasEnZona(null).size());
    }

    @Test
    public void testMetodoImpedirEsAplicableLuegoDeActivarUnaReaccion() {
        MetodoCarta impedir = new Impedir();

        Deque<Ejecucion> metodos = new ArrayDeque<Ejecucion>();
        MetodoCarta resonancia = new Resonancia(Tipo.Reaccion);
        Ejecucion ejecucion = new Ejecucion(resonancia, null, null, metodos, null, null, null, null);

        metodos.push(ejecucion);

        Boolean esAplicable = impedir.esAplicableA(new EtapaPrincipal(), new ZonaCombate(), metodos, null, null);

        assertEquals(metodos.size(), 1);
        assertEquals(esAplicable, true);
    }

    @Test
    public void testMetodoImpedirSeEjecutaCorrectamente() throws MovimientoInvalido {

        MetodoCarta impedir = new Impedir();

        Ejecucion unaEjecucion = new Ejecucion(impedir, null, null, null, null, null, null, null);

        Deque<Ejecucion> metodos = new ArrayDeque<Ejecucion>();

        metodos.push(unaEjecucion);
        metodos.push(unaEjecucion);
        metodos.push(unaEjecucion);
        metodos.push(unaEjecucion);
        metodos.push(unaEjecucion);

        impedir.ejecutar(null, null, metodos, null, null, null, null);

        assertEquals(metodos.size(), 4);
        assertEquals(metodos.pop(), unaEjecucion);
        assertEquals(metodos.pop(), unaEjecucion);
        assertEquals(metodos.pop(), unaEjecucion);
        assertEquals(metodos.pop(), unaEjecucion);
    }

    @Test
    public void testMetodoReduccionHPEsAplicable() {
        MetodoCarta impedir = new Reducir();

        Boolean esAplicable = impedir.esAplicableA(new EtapaPrincipal(), new ZonaCombate(), null, null, null);

        assertEquals(esAplicable, true);
    }

    @Test
    public void testMetodoReduccionDeHPFuncionaCorrectamente() throws MovimientoInvalido {
        
        Carta barreraMagica = new Carta(CartasDisponibles.BARRERAMAGICA);

        MetodoCarta reduccion = new Reducir();

        reduccion.ejecutar(null, null, null, null, null, barreraMagica, null); 

        assertEquals(barreraMagica.hp, 11);
    }

    @Test
    public void replicaSeUsaEnArtefacto(){
        MetodoCarta energia = new AumentarEnergia(Energia.Fuego,1,Tipo.Artefacto);
        MetodoCarta replica = new Replica();

        Ejecucion ejecucion = new Ejecucion(energia, null, null, null, null, null, null, null);

        Deque<Ejecucion> stack = new ArrayDeque<>();
        stack.add(ejecucion);
        
        boolean result = replica.esAplicableA(new EtapaPrincipal(),new ZonaMano(), stack, null, null);
        assertTrue(result);

    }

    @Test
    public void damagePorAtributoSeUsaEnCriaturasConElAtributo() throws MazoInvalido, MovimientoInvalido {
        HashMap<String, Integer> cartas = new HashMap<>();
        
        cartas.put(CartasDisponibles.ESPADAMAGICA.nombre, 1);
        cartas.put(CartasDisponibles.AGUA.nombre, 50);
        Mazo mazo = new Mazo(cartas);

        Tablero tableroEnemigo = new Tablero("Jugador", mazo, modoUno);
        tableroEnemigo.iniciarTablero();

        Carta espadaMagica = null;
        for (Carta carta : tableroEnemigo.cartas){
            if (carta.nombreCarta() == "ESPADAMAGICA") {
                espadaMagica = carta;
            }
        }
        assertEquals(espadaMagica.hp,3);
        MetodoCarta damagePorAtributo = new DamagePorAtributo(1,false,Atributo.Metal,Tipo.Criatura);
        damagePorAtributo.ejecutar(null, tableroEnemigo,null, null, null, null, null);
        assertEquals(espadaMagica.hp,2);
    }
}
