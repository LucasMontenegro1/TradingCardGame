package fiuba.tdd.tp;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fiuba.tdd.tp.Excepciones.MazoInvalido;
import fiuba.tdd.tp.carta.CartasDisponibles;
import fiuba.tdd.tp.etapa.EtapaDeAtaque;
import fiuba.tdd.tp.etapa.EtapaFinal;
import fiuba.tdd.tp.etapa.EtapaInicial;
import fiuba.tdd.tp.etapa.EtapaPrincipal;
import fiuba.tdd.tp.mazo.Mazo;
import fiuba.tdd.tp.modo.Modo;
import fiuba.tdd.tp.modo.Modo1;
import fiuba.tdd.tp.modo.Modo2;
import fiuba.tdd.tp.turno.Turno;

public class TurnoTests {
	
	private Mazo mazoModoUno;
	private Mazo mazoModoDos;

    @BeforeEach
    public void setUp() throws MazoInvalido {
		HashMap<String, Integer> cartas = new HashMap<>();
        
        Modo modoUno = new Modo1();
		Modo modoDos = new Modo2();

		cartas.put(CartasDisponibles.ENERGIA.nombreCarta(), 40);
        mazoModoUno = new Mazo(cartas, modoUno);

		cartas.put(CartasDisponibles.ENERGIA.nombreCarta(), 60);
		mazoModoDos = new Mazo(cartas, modoDos);
    }
	
	@Test
	void testCreacionTurnoDeUnaPartidaModoUno() {
		
		Integer puntos = 15;
	
        Turno nuevoTurno = new Turno(this.mazoModoUno.getModo(), this.mazoModoUno, puntos);
        
        assert nuevoTurno instanceof Turno : "No es una instancia de Turno";
	}

	@Test
	void testIniciarTurnoDeUnaPartidaModoUno(){
			
		Integer puntos = 15;

        Turno nuevoTurno = new Turno(this.mazoModoUno.getModo(), this.mazoModoUno, puntos);
        
        assert nuevoTurno.etapaActual() instanceof EtapaInicial : "Error al iniciar el turno";
	}

	@Test
	void testPasarDeEtapaEnUnTurnoDeJugadorEnUnaPartidaModoUno(){
		
		Integer puntos = 15;
	
		Turno nuevoTurno = new Turno(this.mazoModoUno.getModo(), this.mazoModoUno, puntos);

		nuevoTurno.iniciarTurno();

		nuevoTurno.pasarDeEtapa();
        
        assert nuevoTurno.etapaActual() instanceof EtapaPrincipal;
	}

	@Test 
	void testInformarEtapaActualDeUnTurnoDeUnaPartidaModoUno(){
		
		Integer puntos = 15;
        
		Turno nuevoTurno = new Turno(this.mazoModoUno.getModo(), this.mazoModoUno, puntos);

		nuevoTurno.iniciarTurno();

		assert nuevoTurno.etapaActual() instanceof EtapaInicial;
	}

	@Test
	void testCreacionTurnoDeUnaPartidaModoDos() {
		
		Integer puntos = 15;
	
        Turno nuevoTurno = new Turno(this.mazoModoDos.getModo(), this.mazoModoDos, puntos);
        
        assert nuevoTurno instanceof Turno : "No es una instancia de Turno";
	}

	@Test
	void testIniciarTurnoDeUnaPartidaModoDos(){
			
		Integer puntos = 15;

        Turno nuevoTurno = new Turno(this.mazoModoDos.getModo(), this.mazoModoDos, puntos);
        
        assert nuevoTurno.etapaActual() instanceof EtapaInicial : "Error al iniciar el turno";
	}

	@Test
	void testPasarDeEtapaEnUnTurnoDeJugadorEnUnaPartidaModoDos(){
		
		Integer puntos = 4;
	
		Turno nuevoTurno = new Turno(this.mazoModoDos.getModo(), this.mazoModoDos, puntos);

		nuevoTurno.iniciarTurno();

		nuevoTurno.pasarDeEtapa();
        
        assert nuevoTurno.etapaActual() instanceof EtapaPrincipal;
	}

	@Test 
	void testInformarEtapaActualDeUnTurnoDeUnaPartidaModoDos(){
		
		Integer puntos = 15;
        
		Turno nuevoTurno = new Turno(this.mazoModoDos.getModo(), this.mazoModoDos, puntos);

		nuevoTurno.iniciarTurno();

		assert nuevoTurno.etapaActual() instanceof EtapaInicial;
	}

	@Test
	void testAdministracionDeEtapasEnUnTurnoModoUno(){
		
		Integer puntos = 4;

        Turno nuevoTurno = new Turno(this.mazoModoUno.getModo(), this.mazoModoUno, puntos);
		
		nuevoTurno.iniciarTurno();

		assert nuevoTurno.etapaActual() instanceof EtapaInicial;
		
		nuevoTurno.pasarDeEtapa();

        assert nuevoTurno.etapaActual() instanceof EtapaPrincipal;

		nuevoTurno.pasarDeEtapa();

		assert nuevoTurno.etapaActual() instanceof EtapaDeAtaque;

		nuevoTurno.pasarDeEtapa();

		assert nuevoTurno.etapaActual() instanceof EtapaFinal;
	}

    @Test 
	void testAdministracionDeEtapasEnUnTurnoModoDosConMenosDeSeisPuntosDeVictoria(){
		
		Integer puntos = 2;
		
        Turno nuevoTurno = new Turno(this.mazoModoDos.getModo(), this.mazoModoDos, puntos);

		nuevoTurno.iniciarTurno();
		
		assert nuevoTurno.etapaActual() instanceof EtapaInicial;
		
		nuevoTurno.pasarDeEtapa();

        assert nuevoTurno.etapaActual() instanceof EtapaPrincipal;

		nuevoTurno.pasarDeEtapa();

		assert nuevoTurno.etapaActual() instanceof EtapaDeAtaque;

		nuevoTurno.pasarDeEtapa();

		assert nuevoTurno.etapaActual() instanceof EtapaFinal;
	}

    @Test 
	void testAdministracionDeEtapasEnUnTurnoModoDosConMasDeSeisPuntosDeVictoria(){
    	
		Integer puntos = 10;
		
		Turno nuevoTurno = new Turno(this.mazoModoDos.getModo(), this.mazoModoDos, puntos);

		nuevoTurno.iniciarTurno();
		
		assert nuevoTurno.etapaActual() instanceof EtapaInicial;
		
		nuevoTurno.pasarDeEtapa();
        
        assert nuevoTurno.etapaActual() instanceof EtapaFinal;

    }
}
