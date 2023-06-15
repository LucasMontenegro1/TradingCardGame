package fiuba.tdd.tp;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import fiuba.tdd.tp.Excepciones.MazoInvalido;
import fiuba.tdd.tp.Excepciones.MovimientoInvalido;
import fiuba.tdd.tp.carta.Carta;
import fiuba.tdd.tp.carta.CartasDisponibles;
import fiuba.tdd.tp.etapa.EtapaDeAtaque;
import fiuba.tdd.tp.etapa.EtapaInicial;
import fiuba.tdd.tp.etapa.EtapaPrincipal;
import fiuba.tdd.tp.modo.Modo;
import fiuba.tdd.tp.modo.Modo1;
import fiuba.tdd.tp.modo.Modo2;
import fiuba.tdd.tp.turno.Turno;

@SpringBootTest
public class TurnoTests {
	
	ArrayList<Carta> cartas = new ArrayList<>();
	Modo modo1 = new Modo1();
	Modo modo2 = new Modo2();

	@BeforeEach
    public void setUp() throws MazoInvalido {
		this.cartas.add(new Carta(CartasDisponibles.ALQUIMISTA));
		this.cartas.add(new Carta(CartasDisponibles.ALQUIMISTA));
		this.cartas.add(new Carta(CartasDisponibles.ANTIMAGIA));
		this.cartas.add(new Carta(CartasDisponibles.ANTIMAGIA));
		this.cartas.add(new Carta(CartasDisponibles.ANTIMAGIA));
		this.cartas.add(new Carta(CartasDisponibles.ANTIMAGIA));
		this.cartas.add(new Carta(CartasDisponibles.BARRERAMAGICA));
		this.cartas.add(new Carta(CartasDisponibles.BARRERAMAGICA));
		this.cartas.add(new Carta(CartasDisponibles.FUEGO));
		this.cartas.add(new Carta(CartasDisponibles.FUEGO));
		this.cartas.add(new Carta(CartasDisponibles.FUEGO));
    }

	
	@Test
	void testCreacionTurnoDeUnaPartidaModoUno() {
		
		Integer puntos = 15;

        Turno nuevoTurno = new Turno(this.modo1, puntos);
        
        assert nuevoTurno instanceof Turno : "No es una instancia de Turno";
	}

	@Test
	void testIniciarTurnoDeUnaPartidaModoUno(){
			
		Integer puntos = 15;

        Turno nuevoTurno = new Turno(this.modo1, puntos);
        
        assert nuevoTurno.etapaActual() instanceof EtapaInicial : "Error al iniciar el turno";
	}

	@Test
	void testPasarDeEtapaEnUnTurnoDeJugadorEnUnaPartidaModoUno() throws MovimientoInvalido {
		
		Integer puntos = 15;
	
		Turno nuevoTurno = new Turno(this.modo1, puntos);

		nuevoTurno.iniciarTurno(this.cartas);

		nuevoTurno.pasarDeEtapa();
        
        assert nuevoTurno.etapaActual() instanceof EtapaPrincipal;
	}

	@Test 
	void testInformarEtapaActualDeUnTurnoDeUnaPartidaModoUno() throws MovimientoInvalido {
		
		Integer puntos = 15;
        
		Turno nuevoTurno = new Turno(this.modo1, puntos);

		nuevoTurno.iniciarTurno(this.cartas);

		assert nuevoTurno.etapaActual() instanceof EtapaInicial;
	}

	@Test
	void testCreacionTurnoDeUnaPartidaModoDos() {
		
		Integer puntos = 15;
	
        Turno nuevoTurno = new Turno(this.modo2, puntos);
        
        assert nuevoTurno instanceof Turno : "No es una instancia de Turno";
	}

	@Test
	void testIniciarTurnoDeUnaPartidaModoDos(){
			
		Integer puntos = 15;

        Turno nuevoTurno = new Turno(this.modo2, puntos);
        
        assert nuevoTurno.etapaActual() instanceof EtapaInicial : "Error al iniciar el turno";
	}

	@Test
	void testPasarDeEtapaEnUnTurnoDeJugadorEnUnaPartidaModoDos() throws MovimientoInvalido {
		
		Integer puntos = 4;
	
		Turno nuevoTurno = new Turno(this.modo2, puntos);

		nuevoTurno.iniciarTurno(this.cartas);

		nuevoTurno.pasarDeEtapa();
        
        assert nuevoTurno.etapaActual() instanceof EtapaPrincipal;
	}

	@Test 
	void testInformarEtapaActualDeUnTurnoDeUnaPartidaModoDos() throws MovimientoInvalido {
		
		Integer puntos = 15;
        
		Turno nuevoTurno = new Turno(this.modo2, puntos);

		nuevoTurno.iniciarTurno(this.cartas);

		assert nuevoTurno.etapaActual() instanceof EtapaInicial;
	}

	@Test
	void testAdministracionDeEtapasEnUnTurnoModoUno() throws MovimientoInvalido {
		
		Integer puntos = 4;

        Turno nuevoTurno = new Turno(this.modo2, puntos);
		
		nuevoTurno.iniciarTurno(this.cartas);

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
		
		Integer puntos = 2;
		
        Turno nuevoTurno = new Turno(this.modo2, puntos);

		nuevoTurno.iniciarTurno(this.cartas);
		
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
    	
		Integer puntos = 10;
		
		Turno nuevoTurno = new Turno(this.modo2, puntos);

		nuevoTurno.iniciarTurno(this.cartas);
		
		assert nuevoTurno.etapaActual() instanceof EtapaInicial;
		
		nuevoTurno.pasarDeEtapa();
        
        assert nuevoTurno.etapaActual() == null;

    }
}
