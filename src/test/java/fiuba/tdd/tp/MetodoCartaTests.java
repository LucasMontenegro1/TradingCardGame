package fiuba.tdd.tp;

import fiuba.tdd.tp.carta.Metodos.*;
import fiuba.tdd.tp.Excepciones.MazoInvalido;
import fiuba.tdd.tp.carta.Carta;
import fiuba.tdd.tp.carta.CartasDisponibles;
import fiuba.tdd.tp.carta.Energia;
import fiuba.tdd.tp.carta.Tipo;
import fiuba.tdd.tp.carta.Atributo;
import fiuba.tdd.tp.etapa.EtapaDeAtaque;
import fiuba.tdd.tp.etapa.EtapaPrincipal;
import fiuba.tdd.tp.jugador.Mazo;
import fiuba.tdd.tp.modo.Modo;
import fiuba.tdd.tp.modo.Modo1;
import fiuba.tdd.tp.modo.Modo2;
import fiuba.tdd.tp.partida.Ejecucion;
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
    private Modo modoUno = new Modo1();
    private Modo modoDos = new Modo2();
    private ArrayList<Integer> costo;

    @BeforeEach
    public void setUp() throws MazoInvalido {
		HashMap<String, Integer> cartasModoUno = new HashMap<>();
        HashMap<String, Integer> cartasModoDos = new HashMap<>();

		cartasModoUno.put(CartasDisponibles.AGUA.nombre, 40);
        mazoModoUno = new Mazo(cartasModoUno);

		cartasModoDos.put(CartasDisponibles.AGUA.nombre, 60);
		mazoModoDos = new Mazo(cartasModoDos);

        costo = new ArrayList<>();
        costo.add(0);
        costo.add(0);
        costo.add(0);
    }

    @Test
    public void atacarYTomarCarta() {
        MetodoCarta atacar = new Atacar(10, costo);
        MetodoCarta tomarCarta = new TomarCarta(1, Tipo.Criatura, costo);

        MetodoCarta efecto = new MetodoCartaCompuesto(atacar, tomarCarta);
       
        assertTrue(efecto.esAplicableA(new EtapaDeAtaque(), new ZonaCombate(),null));
    }

    @Test
    public void testTransferirEnergiaCriterioDeAplicacion(){
        MetodoCarta transferirEnergia =  new TransferirEnergia(costo);

        assertTrue(transferirEnergia.esAplicableA(new EtapaPrincipal(), new ZonaCombate(),null));
    }

    @Test 
    public void testTransferenciaDeEnergia(){
        MetodoCarta transferirEnergia =  new TransferirEnergia(costo);

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
        MetodoCarta transferirCarta = new TransferirCarta(costo);

        assertTrue(transferirCarta.esAplicableA(new EtapaPrincipal(), new ZonaMano(),null));
    }

    @Test 
    public void testTransferirCarta(){
        Tablero enJuego = new Tablero("Jugador 1", mazoModoDos, modoDos);
        Tablero contrincante = new Tablero("Jugador 2", mazoModoDos, modoDos);

        enJuego.iniciarTablero();
        contrincante.iniciarTablero();

        MetodoCarta transfeririCarta = new TransferirCarta(costo);

        Integer cantInicialEnJuego = enJuego.cartas.size();

        Integer cantInicialContrincante = contrincante.cartas.size();

        Random random = new Random();
        Carta carta = contrincante.cartas.get(random.nextInt(contrincante.cartas.size()));

        transfeririCarta.ejecutar(enJuego, contrincante, null, null, carta, null, null);       

        assertEquals(cantInicialEnJuego + 1, enJuego.cartas.size());
        assertEquals(cantInicialContrincante - 1, contrincante.cartas.size());
    }

    @Test
    public void testSacrificioCriterioDeAplicacion(){
        MetodoCarta sacrificio = new Sacrificio(costo);

        assertTrue(sacrificio.esAplicableA(new EtapaPrincipal(), new ZonaMano(),null));
    }

    @Test 
    public void testSacrificio(){
        MetodoCarta sacrificio = new Sacrificio(costo);

        Carta carta = new Carta(CartasDisponibles.ALQUIMISTA);

        Tablero enJuego = new Tablero("Jugador 1", mazoModoUno, modoUno);
        enJuego.iniciarTablero();

        sacrificio.ejecutar(enJuego, null, null, null, carta, null, null);

        assertEquals(3, enJuego.energiaFuego());
        assertEquals(3, enJuego.energiaPlanta());
        assertEquals(0, enJuego.energiaAgua());
    }

    @Test
    public void testTomarCartaCriaturaCriterioDeAplicacion(){
        MetodoCarta tomarCarta = new TomarCarta(2,Tipo.Criatura, costo);

        assertTrue(tomarCarta.esAplicableA(new EtapaDeAtaque(), new ZonaCombate(),null));
    }

    @Test
    public void testTomarCartaArtefactoCriterioDeAplicacion(){
        MetodoCarta tomarCarta = new TomarCarta(2,Tipo.Artefacto, costo);

        assertTrue(tomarCarta.esAplicableA(new EtapaPrincipal(), new ZonaCombate(),null));
    }

    @Test 
    public void testTomarCarta(){

        Integer cantidad = 4;

        MetodoCarta tomarCarta = new TomarCarta(cantidad, Tipo.Artefacto, costo);

        Tablero enJuego = new Tablero("Jugador 1", mazoModoDos, modoDos);
        enJuego.iniciarTablero();

        ArrayList<Carta> cartas = enJuego.cartasEnZona(null);
        
        Integer cantCartasInicial = cartas.size();

        tomarCarta.ejecutar(enJuego, null, null, null, null, null, null);

        assertEquals(cantCartasInicial - cantidad, enJuego.cartasEnZona(null).size());
    }

    @Test
    public void testMetodoImpedirEsAplicableLuegoDeActivarUnaReaccion() {
        MetodoCarta impedir = new Impedir(costo);

        Deque<MetodoCarta> metodos = new ArrayDeque<MetodoCarta>();
        MetodoCarta resonancia = new Resonancia(Tipo.Reaccion, costo);

        metodos.push(resonancia);

        Boolean esAplicable = impedir.esAplicableA(new EtapaPrincipal(), new ZonaCombate(), metodos);

        assertEquals(metodos.size(), 1);
        assertEquals(esAplicable, true);
    }

    @Test
    public void testMetodoImpedirSeEjecutaCorrectamente() {

        MetodoCarta impedir = new Impedir(costo);

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
        MetodoCarta impedir = new Reducir(costo);

        Boolean esAplicable = impedir.esAplicableA(new EtapaPrincipal(), new ZonaCombate(), new ArrayDeque<MetodoCarta>());

        assertEquals(esAplicable, true);
    }

    @Test
    public void testMetodoReduccionDeHPFuncionaCorrectamente() {
        
        Carta barreraMagica = new Carta(CartasDisponibles.BARRERAMAGICA);

        MetodoCarta reduccion = new Reducir(costo);

        reduccion.ejecutar(null, null, null, null, null, barreraMagica, null); 

        assertEquals(barreraMagica.hp, 11);
    }

    @Test
    public void replicaSeUsaEnArtefacto(){
        MetodoCarta energia = new AumentarEnergia(Energia.Fuego,1,Tipo.Artefacto, costo);
        MetodoCarta replica = new Replica(costo);
        Deque<MetodoCarta> stack = new ArrayDeque<>();
        stack.add(energia);
        boolean result = replica.esAplicableA(new EtapaPrincipal(),new ZonaMano(),stack);
        assertTrue(result);

    }

    @Test
    public void damagePorAtributoSeUsaEnCriaturasConElAtributo() throws MazoInvalido {
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
        MetodoCarta damagePorAtributo = new DamagePorAtributo(1,false,Atributo.Metal,Tipo.Criatura, costo);
        damagePorAtributo.ejecutar(null, tableroEnemigo,null, null, null, null, null);
        assertEquals(espadaMagica.hp,2);
    }
}
