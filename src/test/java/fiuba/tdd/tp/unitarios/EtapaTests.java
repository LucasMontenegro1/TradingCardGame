package fiuba.tdd.tp.unitarios;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import fiuba.tdd.tp.Excepciones.MazoInvalido;
import fiuba.tdd.tp.Excepciones.MovimientoInvalido;
import fiuba.tdd.tp.carta.Carta;
import fiuba.tdd.tp.carta.CartasDisponibles;
import fiuba.tdd.tp.jugador.Mazo;
import fiuba.tdd.tp.jugador.Tablero;
import fiuba.tdd.tp.modo.Modo;
import fiuba.tdd.tp.modo.Modo1;
import fiuba.tdd.tp.modo.Modo2;
import fiuba.tdd.tp.turno.Etapa;
import fiuba.tdd.tp.turno.EtapaDeAtaque;
import fiuba.tdd.tp.turno.EtapaInicial;
import fiuba.tdd.tp.turno.EtapaPrincipal;
import fiuba.tdd.tp.zona.ZonaArtefacto;
import fiuba.tdd.tp.zona.ZonaCombate;
import fiuba.tdd.tp.zona.ZonaMano;
import fiuba.tdd.tp.zona.ZonaReserva;

@SpringBootTest
public class EtapaTests {

    HashMap<String, Integer> cartas = new HashMap<>();
	Modo modo1 = new Modo1();
	Modo modo2 = new Modo2();
    Tablero tablero1;
    Tablero tablero2;

	@BeforeEach
    public void setUp() throws MazoInvalido {
        this.cartas.put(CartasDisponibles.ALQUIMISTA.nombre, 2);
        this.cartas.put(CartasDisponibles.ANTIMAGIA.nombre, 4);
        this.cartas.put(CartasDisponibles.BARRERAMAGICA.nombre, 2);
		this.cartas.put(CartasDisponibles.FUEGO.nombre, 3);
		
        Mazo mazo = new Mazo(cartas);

        tablero1 = new Tablero("jugador", mazo, modo1);
        tablero1.iniciarTablero();
        tablero2 = new Tablero("jugador", mazo, modo2);
        tablero2.iniciarTablero();
    }
    // Etapa inicial

    @Test
    void testCreacionDeUnaEtapaInicialPartidaModoUno(){
		
		Integer puntos = 15;
        tablero1.puntos = puntos;

        Etapa etapaIncial = new EtapaInicial(this.modo1);

        assert etapaIncial instanceof EtapaInicial;
        
    }

    @Test
    void testAlFinalizarEtapaInicialConCartaAsignadaInicialLaEtapaPrincipalEnUnaPartidaModoUno() throws MovimientoInvalido{
		
		Integer puntos = 15;
        tablero1.puntos = puntos;
        
        Etapa etapaIncial = new EtapaInicial(this.modo1);

        etapaIncial.iniciar(tablero1);

        Etapa siguienteEtapa = etapaIncial.finalizar();

        assert siguienteEtapa instanceof EtapaPrincipal;
    }

    @Test
    void testEnLaEtapaInicialNoSePuedenMoverCartasEnUnaPartidaModoUno() throws MovimientoInvalido{
		
		Integer puntos = 15;
        tablero1.puntos = puntos;
	
		Etapa etapaIncial = new EtapaInicial(this.modo1);

        Carta carta = new Carta(CartasDisponibles.ALQUIMISTA);
        carta.zona = new ZonaReserva();

        etapaIncial.iniciar(tablero1);

        assertThrows(MovimientoInvalido.class, () -> {
            etapaIncial.moverCarta(carta);
        });
    }

    @Test
    void testEnLaEtapaInicialNoSePuedeInvocarUnaCriaturaAZonaDeCombateEnUnaPartidaModoUno() throws MovimientoInvalido{
		
		Integer puntos = 15;
        tablero1.puntos = puntos;
        
        Carta carta = new Carta(CartasDisponibles.ALQUIMISTA);

        carta.zona = new ZonaMano();

        Etapa etapaIncial = new EtapaInicial(this.modo1);

        etapaIncial.iniciar(tablero1);

        
        assertThrows(MovimientoInvalido.class, () -> {
            etapaIncial.invocarAZonaDeCombate(carta);
        });
    }

    @Test
    void testEnLaEtapaInicialNoSePuedeInvocarUnaCriaturaAZonaDeReservaEnUnaPartidaModoUno() throws MovimientoInvalido {

        Integer puntos = 15;
        tablero1.puntos = puntos;

        Carta carta = new Carta(CartasDisponibles.ALQUIMISTA);

        carta.zona = new ZonaMano();
		
		Etapa etapaInicial = new EtapaInicial(this.modo1);

        etapaInicial.iniciar(tablero1);

        assertThrows(MovimientoInvalido.class, () -> {
            etapaInicial.invocarAZonaDeReserva(carta);
        });
    }

    @Test
    void testEnLaEtapaInicialNoSePuedeInvocarUnArtefactoAZonaDeArtefactosEnUnaPartidaModoUno() throws MovimientoInvalido{

        Carta carta = new Carta(CartasDisponibles.ANTIMAGIA);

        carta.zona = new ZonaMano();
		
		Etapa etapaIncial = new EtapaInicial(this.modo1);

        etapaIncial.iniciar(tablero1);
        
        assertThrows(MovimientoInvalido.class, () -> {
            etapaIncial.invocarAZonaDeArtefacto(carta);
        });
    }

    @Test
    void testCreacionDeUnaEtapaInicialPartidaModoDos(){
		
        Etapa etapaIncial = new EtapaInicial(this.modo2);

        assert etapaIncial instanceof EtapaInicial;
        
    }

    @Test
    void testAlFinalizarEtapaInicialConMasDeSeisPuntosDeVictoriaEnUnaPartidaModoDos() throws MovimientoInvalido{
		
		Integer puntos = 15;
        tablero2.puntos = puntos;
        
        Etapa etapaIncial = new EtapaInicial(this.modo2);

        etapaIncial.iniciar(tablero2);

        Etapa siguienteEtapa = etapaIncial.finalizar();

        assert siguienteEtapa == null;
    }

    @Test
    void testEnLaEtapaInicialNoSePuedenMoverCartasEnUnaPartidaModoDos() throws MovimientoInvalido{
		
		Integer puntos = 4;
        tablero2.puntos = puntos;
	
		Etapa etapaIncial = new EtapaInicial(this.modo2);

        Carta carta = new Carta(CartasDisponibles.ALQUIMISTA);
        
        carta.zona = new ZonaReserva();

        etapaIncial.iniciar(tablero2);

        assertThrows(MovimientoInvalido.class, () -> {
            etapaIncial.moverCarta(carta);
        });
    }

    @Test
    void testEnLaEtapaInicialNoSePuedeInvocarUnaCriaturaAZonaDeCombateEnUnaPartidaModoDos() throws MovimientoInvalido{
		
		Integer puntos = 4;
        tablero2.puntos = puntos;
        
        Carta carta = new Carta(CartasDisponibles.ALQUIMISTA);
        
        carta.zona = new ZonaMano();

        Etapa etapaIncial = new EtapaInicial(this.modo2);

        etapaIncial.iniciar(tablero2);
        
        assertThrows(MovimientoInvalido.class, () -> {
            etapaIncial.invocarAZonaDeCombate(carta);
        });
    }

    @Test
    void testEnLaEtapaInicialNoSePuedeInvocarUnaCriaturaAZonaDeReservaEnUnaPartidaModoDos() throws MovimientoInvalido {

        Integer puntos = 4;
        tablero2.puntos = puntos;

        Carta carta = new Carta(CartasDisponibles.ALQUIMISTA);
        
        carta.zona = new ZonaMano();
		
		Etapa etapaIncial = new EtapaInicial(this.modo2);

        etapaIncial.iniciar(tablero2);

        assertThrows(MovimientoInvalido.class, () -> {
            etapaIncial.invocarAZonaDeReserva(carta);
        });
    }

    @Test
    void testEnLaEtapaInicialNoSePuedeInvocarUnArtefactoAZonaDeArtefactosEnUnaPartidaModoDos() throws MovimientoInvalido {
		
		Integer puntos = 4;
        tablero2.puntos = puntos;

        Carta carta = new Carta(CartasDisponibles.ANTIMAGIA);
        
        carta.zona = new ZonaMano();
		
		Etapa etapaIncial = new EtapaInicial(this.modo2);

        etapaIncial.iniciar(tablero2);

        
        assertThrows(MovimientoInvalido.class, () -> {
            etapaIncial.invocarAZonaDeArtefacto(carta);
        });
    }

    // Etapa principal

    @Test
    void testCreacionDeUnaEtapaPrincipal(){
        Etapa etapaPrincipal = new EtapaPrincipal();

        assert etapaPrincipal instanceof EtapaPrincipal;
    }

    @Test
    void testPasarDeEtapaPrincipalAEtapaDeAtaque(){
        Etapa etapaPrincipal = new EtapaPrincipal();

        Etapa siguienteEtapa = etapaPrincipal.finalizar();

        assert siguienteEtapa instanceof EtapaDeAtaque;
    }

    @Test
    void testEnLaEtapaPrincipalSePuedenMoverCartas() throws MovimientoInvalido {
    
        Carta carta = new Carta(CartasDisponibles.ALQUIMISTA);
        
        carta.zona = new ZonaReserva();

        Etapa etapaPrincipal = new EtapaPrincipal();

        etapaPrincipal.iniciar(tablero1);

        etapaPrincipal.moverCarta(carta);

        assert carta.zona instanceof ZonaCombate;
    }

    @Test
    void testEnLaEtapaPrincipalSePuedeInvocarUnaCriaturaAZonaDeCombate() throws MovimientoInvalido {
        
        Carta carta = new Carta(CartasDisponibles.ALQUIMISTA);
        
        carta.zona = new ZonaMano();

        Etapa etapaPrincipal = new EtapaPrincipal();

        etapaPrincipal.iniciar(tablero1);

        etapaPrincipal.invocarAZonaDeCombate(carta);

        assert carta.zona instanceof ZonaCombate;
    }

    @Test
    void testEnLaEtapaPrincipalSePuedeInvocarUnaCriaturaAZonaDeReserva() throws MovimientoInvalido {
        
        Carta carta = new Carta(CartasDisponibles.ALQUIMISTA);
        
        carta.zona = new ZonaMano();

        Etapa etapaPrincipal = new EtapaPrincipal();

        etapaPrincipal.iniciar(tablero1);

        etapaPrincipal.invocarAZonaDeReserva(carta);

        assert carta.zona instanceof ZonaReserva;
    }

    @Test
    void testEnLaEtapaPrincipalSePuedeInvocarUnArtefactoAZonaDeArtefactos() throws MovimientoInvalido {

        Carta carta = new Carta(CartasDisponibles.ANTIMAGIA);
        
        carta.zona = new ZonaMano();

        Etapa etapaPrincipal = new EtapaPrincipal();

        etapaPrincipal.iniciar(tablero1);

        etapaPrincipal.invocarAZonaDeArtefacto(carta);

        assert carta.zona instanceof ZonaArtefacto;
    }

    // Etapa de ataque

    @Test
    void testCreacionDeUnaEtapaDeAtaque(){
        Etapa etapaDeAtaque = new EtapaDeAtaque();

        assert etapaDeAtaque instanceof EtapaDeAtaque;
    }
    
    @Test
    void testPasarDeEtapaDeAtaqueAEtapaFinal(){
        Etapa etapaDeAtaque = new EtapaDeAtaque();

        Etapa siguienteEtapa = etapaDeAtaque.finalizar();

        assert siguienteEtapa == null;
    }

    @Test
    void testEnLaEtapaDeAtaqueNoSePuedenMoverCartas() throws MovimientoInvalido {

        Carta carta = new Carta(CartasDisponibles.ALQUIMISTA);
        
        carta.zona = new ZonaReserva();

        Etapa etapaDeAtaque = new EtapaDeAtaque();

        etapaDeAtaque.iniciar(tablero1);
        
        assertThrows(MovimientoInvalido.class, () -> {
            etapaDeAtaque.moverCarta(carta);
        });
    }

    @Test
    void testEnLaEtapaDeAtaqueNoSePuedeInvocarUnaCriaturaAZonaDeCombate() throws MovimientoInvalido {
        
        Carta carta = new Carta(CartasDisponibles.ALQUIMISTA);
        
        carta.zona = new ZonaMano();

        Etapa etapaDeAtaque = new EtapaDeAtaque();

        etapaDeAtaque.iniciar(tablero1);

        assertThrows(MovimientoInvalido.class, () -> {
            etapaDeAtaque.invocarAZonaDeCombate(carta);
        });
    }

    @Test
    void testEnLaEtapaDeAtaqueNoSePuedeInvocarUnaCriaturaAZonaDeReserva() throws MovimientoInvalido {
    

        Carta carta = new Carta(CartasDisponibles.ALQUIMISTA);
        
        carta.zona = new ZonaMano();

        Etapa etapaDeAtaque = new EtapaDeAtaque();

        etapaDeAtaque.iniciar(tablero1);

        assertThrows(MovimientoInvalido.class, () -> {
            etapaDeAtaque.invocarAZonaDeReserva(carta);
        });
    }

    @Test
    void testEnLaEtapaDeAtaqueNoSePuedeInvocarUnArtefactoAZonaDeArtefactos() throws MovimientoInvalido {
        
        Carta carta = new Carta(CartasDisponibles.ANTIMAGIA);
        
        carta.zona = new ZonaMano();

        Etapa etapaDeAtaque = new EtapaDeAtaque();

        etapaDeAtaque.iniciar(tablero1);

        assertThrows(MovimientoInvalido.class, () -> {
            etapaDeAtaque.invocarAZonaDeArtefacto(carta);
        });
    }

    
}
