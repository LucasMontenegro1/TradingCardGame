package fiuba.tdd.tp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import fiuba.tdd.tp.Excepciones.MazoInvalido;
import fiuba.tdd.tp.Excepciones.MovimientoInvalido;
import fiuba.tdd.tp.carta.Carta;
import fiuba.tdd.tp.carta.CartasDisponibles;
import fiuba.tdd.tp.carta.Energia;
import fiuba.tdd.tp.carta.Metodos.MetodoCarta;
import fiuba.tdd.tp.etapa.EtapaDeAtaque;
import fiuba.tdd.tp.jugador.Mazo;
import fiuba.tdd.tp.modo.Modo;
import fiuba.tdd.tp.modo.Modo1;
import fiuba.tdd.tp.modo.Modo2;
import fiuba.tdd.tp.tablero.Tablero;

@SpringBootTest
public class TableroTests {

    Modo modoUno = new Modo1();
    Modo modoDos = new Modo2();
    private Mazo mazoModoUno;
	private Mazo mazoModoDos;

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
    void testCreacionDeTablero() {
        Tablero tablero = new Tablero("jugador", mazoModoUno, modoUno); 
        assert tablero instanceof Tablero;
    }

    @Test
    void testTableroSeCreaCorrectamenteEnUnaPartidaModoUno() {
        Tablero tablero = new Tablero("jugador", mazoModoUno, modoUno);
        tablero.iniciarTablero();
        
        assertEquals("jugador", tablero.usuario);
        assertEquals(40, tablero.cartas.size());        
    }

    @Test
    void testTableroSeCreaCorrectamenteEnUnaPartidaModoDos() {
        Tablero tablero = new Tablero("jugador", mazoModoDos, modoDos); 
        tablero.iniciarTablero();

        assertEquals("jugador", tablero.usuario);
        assertEquals(60, tablero.cartas.size());        
    }

    @Test
    void testTableroSeCreaCorrectamenteYTieneVeintePuntosDeVidaPorSerModoUno() {
        Tablero tablero = new Tablero("jugador", mazoModoUno, modoUno);
        assertEquals(20, tablero.puntos);   
    }

    @Test
    void testTableroSeCreaCorrectamenteYTieneCeroPuntosDeVidaPorSerModoDos() {
        Tablero tablero = new Tablero("jugador", mazoModoDos, modoDos); 
        assertEquals(0, tablero.puntos);        
    }

    @Test
    void testTableroDeJugadorDismuyePuntosDeVidaEnModoUno() {
        Tablero tablero = new Tablero("jugador", mazoModoUno, modoUno); 

        tablero.disminuirPuntos(5); 

        assertEquals(15, tablero.puntos);        
    }
    
    @Test
    void testTableroDeJugadorAumentaPuntosDeVidaEnModoUno(){
    
        Tablero tablero = new Tablero("jugador", mazoModoUno, modoUno); 

        tablero.aumentarPuntos(4);

        assertEquals(24, tablero.puntos);         
    }
    
    @Test
    void testTableroDeJugadorAumentaPuntosDeVidaEnModoDos() {
    
        Tablero tablero = new Tablero("jugador", mazoModoDos, modoDos); 

        tablero.aumentarPuntos(4);

        assertEquals(4, tablero.puntos);          
    }
    
    @Test
    void testAumentarEnergia() {
        Tablero tablero = new Tablero("jugador", mazoModoDos, modoDos); 

        tablero.aumentarEnergia(Energia.Fuego,1);

        assertEquals(1, tablero.energiaFuego());   
    }

    @Test
    void testAumentarTodasLasEnergiasEnDos() {
        Tablero tablero = new Tablero("jugador", mazoModoDos, modoDos); 

        tablero.aumentarEnergia(Energia.Agua,2);
        tablero.aumentarEnergia(Energia.Fuego,2);
        tablero.aumentarEnergia(Energia.Planta,2);

        assertEquals(2, tablero.energiaAgua());   
        assertEquals(2, tablero.energiaFuego());   
        assertEquals(2, tablero.energiaPlanta());   
    }

    @Test
    void testDisminuirEnergia() {
        Tablero tablero = new Tablero("jugador", mazoModoDos, modoDos); 

        tablero.aumentarEnergia(Energia.Fuego,3);
        tablero.disminuirEnergia(Energia.Fuego,1);

        assertEquals(2, tablero.energiaFuego());   
    }

    @Test
    void testDismunirTodasLasEnergiasEnDos() {
        Tablero tablero = new Tablero("jugador", mazoModoDos, modoDos); 

        tablero.aumentarEnergia(Energia.Agua,1);
        tablero.aumentarEnergia(Energia.Fuego,1);
        tablero.aumentarEnergia(Energia.Planta,1);

        tablero.disminuirEnergia(Energia.Agua,1);
        tablero.disminuirEnergia(Energia.Fuego,1);
        tablero.disminuirEnergia(Energia.Planta,1);

        assertEquals(0, tablero.energiaAgua());   
        assertEquals(0, tablero.energiaFuego());   
        assertEquals(0, tablero.energiaPlanta());   
    }

    @Test
    void testLaEnergiaNoPuedeSerNegativa() {
        Tablero tablero = new Tablero("jugador", mazoModoDos, modoDos); 

        tablero.disminuirEnergia(Energia.Agua,1);
        tablero.disminuirEnergia(Energia.Fuego,1);
        tablero.disminuirEnergia(Energia.Planta,1);

        assertEquals(0, tablero.energiaAgua());   
        assertEquals(0, tablero.energiaFuego());   
        assertEquals(0, tablero.energiaPlanta());    
    }

    @Test
    void testAgregarCartaAlTablero(){

        Carta carta = new Carta(CartasDisponibles.ALQUIMISTA);

        Tablero tablero = new Tablero("Jugador", mazoModoDos, modoDos);

        Integer cantInicialDeCartas = tablero.cartas.size();

        tablero.agregarCarta(carta);

        assertEquals(cantInicialDeCartas + 1 ,tablero.cartas.size());
    }

    @Test
    void testEliminarCartaAlTablero(){

        Tablero tablero = new Tablero("Jugador", mazoModoDos, modoDos);
        tablero.iniciarTablero();

        Integer cantInicialDeCartas = tablero.cartas.size();

        Random random = new Random();
        Carta cartaAEliminar = tablero.cartas.get(random.nextInt(tablero.cartas.size()));

        tablero.eliminarCarta(cartaAEliminar);

        assertEquals(cantInicialDeCartas - 1 ,tablero.cartas.size());
    }

    @Test
    void testTableroDevuelveLasCartasUsablesEnEtapaDeAtaqueCorrectamente() throws MazoInvalido {
        HashMap<String, Integer> cartasModoUno = new HashMap<>();        
        Modo modoUno = new Modo1();
    
        cartasModoUno.put(CartasDisponibles.AGUA.nombre, 30);
        cartasModoUno.put(CartasDisponibles.ALQUIMISTA.nombre, 3);
        cartasModoUno.put(CartasDisponibles.ANTIMAGIA.nombre, 3);
        cartasModoUno.put(CartasDisponibles.DRENAR.nombre, 3);
        cartasModoUno.put(CartasDisponibles.SABOTEAR.nombre, 3);

        Mazo mazo = new Mazo(cartasModoUno);

        Tablero tablero = new Tablero("jugador", mazo, modoUno);

        HashMap<Carta, ArrayList<MetodoCarta>> resultadoObtenido = tablero.cartasUsables(new EtapaDeAtaque(), null);

        Set<Carta> cartasUsables = resultadoObtenido.keySet();

        for (Carta carta : cartasUsables) {
            assertEquals(carta.nombreCarta(), "ALQUIMISTA");
        }
    }

    @Test
    void testTableroDevuelveLasCartasAtacables() throws MazoInvalido, MovimientoInvalido {
        HashMap<String, Integer> cartasModoUno = new HashMap<>();        
        Modo modoUno = new Modo1();
    
        cartasModoUno.put(CartasDisponibles.AGUA.nombre, 40);
        cartasModoUno.put(CartasDisponibles.ALQUIMISTA.nombre, 3);
        cartasModoUno.put(CartasDisponibles.DRENAR.nombre, 3);
    
        Mazo mazo = new Mazo(cartasModoUno);

        Tablero tablero = new Tablero("jugador", mazo, modoUno);
        tablero.iniciarTablero();

        ArrayList<Carta> cartas = tablero.cartasEnZona(null);
        for (Carta carta : cartas) {
            carta.cambiarZona();
            carta.moverACombate();
        }

        ArrayList<Carta> resultadoObtenido = tablero.cartasAtacables();

        for (Carta carta : resultadoObtenido) {
            assertEquals(carta.nombreCarta(), "ALQUIMISTA");
        }
    }    
}
