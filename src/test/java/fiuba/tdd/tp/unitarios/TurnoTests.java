package fiuba.tdd.tp.unitarios;

import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import fiuba.tdd.tp.model.Excepciones.MazoInvalido;
import fiuba.tdd.tp.model.Excepciones.MovimientoInvalido;
import fiuba.tdd.tp.model.carta.CartasDisponibles;
import fiuba.tdd.tp.model.jugador.Mazo;
import fiuba.tdd.tp.model.jugador.Tablero;
import fiuba.tdd.tp.model.modo.Modo;
import fiuba.tdd.tp.model.modo.Modo1;
import fiuba.tdd.tp.model.modo.Modo2;
import fiuba.tdd.tp.model.turno.EtapaDeAtaque;
import fiuba.tdd.tp.model.turno.EtapaInicial;
import fiuba.tdd.tp.model.turno.EtapaPrincipal;
import fiuba.tdd.tp.model.turno.Turno;

@SpringBootTest
public class TurnoTests {
	
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
	
	@Test
	void testCreacionTurnoDeUnaPartidaModoUno() {

        Turno nuevoTurno = new Turno(this.modo1);
        
        assert nuevoTurno instanceof Turno : "No es una instancia de Turno";
	}

	@Test
	void testIniciarTurnoDeUnaPartidaModoUno(){

        Turno nuevoTurno = new Turno(this.modo1);
        
        assert nuevoTurno.etapaActual() instanceof EtapaInicial : "Error al iniciar el turno";
	}

	@Test
	void testPasarDeEtapaEnUnTurnoDeJugadorEnUnaPartidaModoUno() throws MovimientoInvalido {
	
		Turno nuevoTurno = new Turno(this.modo1);

		nuevoTurno.iniciarTurno(tablero1);

		nuevoTurno.pasarDeEtapa();
        
        assert nuevoTurno.etapaActual() instanceof EtapaPrincipal;
	}

	@Test 
	void testInformarEtapaActualDeUnTurnoDeUnaPartidaModoUno() throws MovimientoInvalido {
        
		Turno nuevoTurno = new Turno(this.modo1);

		nuevoTurno.iniciarTurno(tablero1);

		assert nuevoTurno.etapaActual() instanceof EtapaInicial;
	}

	@Test
	void testCreacionTurnoDeUnaPartidaModoDos() {
	
        Turno nuevoTurno = new Turno(this.modo2);
        
        assert nuevoTurno instanceof Turno : "No es una instancia de Turno";
	}

	@Test
	void testIniciarTurnoDeUnaPartidaModoDos(){

        Turno nuevoTurno = new Turno(this.modo2);
        
        assert nuevoTurno.etapaActual() instanceof EtapaInicial : "Error al iniciar el turno";
	}

	@Test
	void testPasarDeEtapaEnUnTurnoDeJugadorEnUnaPartidaModoDos() throws MovimientoInvalido {
	
		Turno nuevoTurno = new Turno(this.modo2);

		nuevoTurno.iniciarTurno(tablero2);

		nuevoTurno.pasarDeEtapa();
        
        assert nuevoTurno.etapaActual() instanceof EtapaPrincipal;
	}

	@Test 
	void testInformarEtapaActualDeUnTurnoDeUnaPartidaModoDos() throws MovimientoInvalido {
        
		Turno nuevoTurno = new Turno(this.modo2);

		nuevoTurno.iniciarTurno(tablero2);

		assert nuevoTurno.etapaActual() instanceof EtapaInicial;
	}

	@Test
	void testAdministracionDeEtapasEnUnTurnoModoUno() throws MovimientoInvalido {

        Turno nuevoTurno = new Turno(this.modo2);
		
		nuevoTurno.iniciarTurno(tablero2);

		assert nuevoTurno.etapaActual() instanceof EtapaInicial;
		
		nuevoTurno.pasarDeEtapa();

        assert nuevoTurno.etapaActual() instanceof EtapaPrincipal;

		nuevoTurno.pasarDeEtapa();

		assert nuevoTurno.etapaActual() instanceof EtapaDeAtaque;

		nuevoTurno.pasarDeEtapa();

		assert nuevoTurno.etapaActual() == null;
	}

    @Test 
	void testAdministracionDeEtapasEnUnTurnoModoDosConMenosDeSeisPuntosDeVictoria() throws MovimientoInvalido {
		
        Turno nuevoTurno = new Turno(this.modo2);

		nuevoTurno.iniciarTurno(tablero2);
		
		assert nuevoTurno.etapaActual() instanceof EtapaInicial;
		
		nuevoTurno.pasarDeEtapa();

        assert nuevoTurno.etapaActual() instanceof EtapaPrincipal;

		nuevoTurno.pasarDeEtapa();

		assert nuevoTurno.etapaActual() instanceof EtapaDeAtaque;

		nuevoTurno.pasarDeEtapa();

		assert nuevoTurno.etapaActual() == null;
	}

    @Test 
	void testAdministracionDeEtapasEnUnTurnoModoDosConMasDeSeisPuntosDeVictoria() throws MovimientoInvalido {

		Integer puntos = 15;
		tablero2.puntos = puntos;
    			
		Turno nuevoTurno = new Turno(this.modo2);

		nuevoTurno.iniciarTurno(tablero2);
		
		assert nuevoTurno.etapaActual() instanceof EtapaInicial;
		
		nuevoTurno.pasarDeEtapa();
        
        assert nuevoTurno.etapaActual() == null;

    }
}
