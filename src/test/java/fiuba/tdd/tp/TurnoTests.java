package fiuba.tdd.tp;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fiuba.tdd.tp.Excepciones.MazoInvalido;
import fiuba.tdd.tp.carta.Carta;
import fiuba.tdd.tp.carta.CartasDisponibles;
import fiuba.tdd.tp.carta.Tipo;
import fiuba.tdd.tp.etapa.Etapa;
import fiuba.tdd.tp.etapa.EtapaFinal;
import fiuba.tdd.tp.etapa.EtapaInicial;
import fiuba.tdd.tp.etapa.EtapaPrincipal;
import fiuba.tdd.tp.mazo.Mazo;
import fiuba.tdd.tp.modo.Modo;
import fiuba.tdd.tp.modo.Modo1;
import fiuba.tdd.tp.modo.Modo2;
import fiuba.tdd.tp.turno.Turno;
import fiuba.tdd.tp.zona.Zona;
import fiuba.tdd.tp.zona.ZonaArtefecto;
import fiuba.tdd.tp.zona.ZonaCombate;
import fiuba.tdd.tp.zona.ZonaMano;
import fiuba.tdd.tp.zona.ZonaReserva;

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
	void testCreacionTurno() {
		
		Integer puntos = 15;

        Turno nuevoTurno = new Turno(mazoModoUno.getModo(), mazoModoUno, puntos);
        
        assert nuevoTurno instanceof Turno : "No es una instancia de Turno";
	}

    @Test
	void testIniciarTurno(){

		Integer puntos = 15;
	
		Turno nuevoTurno = new Turno(mazoModoUno.getModo(), mazoModoUno, puntos);
        
        assert nuevoTurno.etapaActual() instanceof EtapaInicial : "Error al iniciar el turno";
	}

    @Test 
	void testPasarDeEtapaEnTurnoModoUno(){
		
		Integer puntos = 4;
		
        Turno nuevoTurno = new Turno(mazoModoUno.getModo(), mazoModoUno, puntos);
        
		nuevoTurno.iniciarTurno();
		
		nuevoTurno.pasarDeEtapa();

        assert nuevoTurno.etapaActual() instanceof EtapaPrincipal : "Error al pasar de etapa en el turno";
	}


    @Test 
	void testPasarDeEtapaEnTurnoModoDosConMenosDeSeisPuntosDeVictoria(){		
		Integer puntos = 2;
		
        Turno nuevoTurno = new Turno(mazoModoDos.getModo(), mazoModoDos, puntos);
        
		nuevoTurno.iniciarTurno();
		
		nuevoTurno.pasarDeEtapa();

        assert nuevoTurno.etapaActual() instanceof EtapaPrincipal : "Error al pasar de etapa en el turno";
	}

    @Test 
	void testPasarDeEtapaEnTurnoModoDosConMasDeSeisPuntosDeVictoria(){
		
		Integer puntos = 10;
		
        Turno nuevoTurno = new Turno(mazoModoDos.getModo(), mazoModoDos, puntos);
        
		nuevoTurno.iniciarTurno();
		
		nuevoTurno.pasarDeEtapa();
        
        assert nuevoTurno.etapaActual() instanceof EtapaFinal : "Error al pasar de etapa en el turno";

    }

    @Test
	void testMoverUnaCriaturaEnEtapaPrincipal(){		
		Integer puntos = 3;
	
		Tipo tipo = Tipo.Criatura;
        String nombre = "Carta1";
        Zona zona = new ZonaReserva();

        Carta carta = new Carta(tipo, nombre, zona);
		
		Turno nuevoTurno = new Turno(mazoModoUno.getModo(), mazoModoUno, puntos);
        
		nuevoTurno.iniciarTurno();
		
		nuevoTurno.pasarDeEtapa();
		
		Etapa etapaActual = nuevoTurno.etapaActual();
		
		etapaActual.moverCarta(carta);

		assert carta.zona instanceof ZonaCombate : "Error al pasar de zona la carta";
        
	}

    @Test
	void testNoSePuedeMoverUnaCriaturaEnEtapaInicial(){		
		Integer puntos = 3;
		
		Tipo tipo = Tipo.Criatura;
        String nombre = "Carta1";
        Zona zona = new ZonaReserva();

        Carta carta = new Carta(tipo, nombre, zona);
		
		Turno nuevoTurno = new Turno(mazoModoUno.getModo(), mazoModoUno, puntos);
        
		nuevoTurno.iniciarTurno();
		
		Etapa etapaActual = nuevoTurno.etapaActual();
		
		etapaActual.moverCarta(carta);

		assert carta.zona instanceof ZonaReserva : "Error al pasar de zona la carta";

	}

    @Test
	void testInvocarDesdeManoEnEtapaPrincipalUnaCriaturaAZonaReserva(){		
		Integer puntos = 3;
	
		Tipo tipo = Tipo.Criatura;
        String nombre = "Carta1";
        Zona zona = new ZonaMano();

        Carta carta = new Carta(tipo, nombre, zona);
		
		Turno nuevoTurno = new Turno(mazoModoUno.getModo(), mazoModoUno, puntos);
        
		nuevoTurno.iniciarTurno();
		
		nuevoTurno.pasarDeEtapa();
		
		Etapa etapaActual = nuevoTurno.etapaActual();
		
		etapaActual.invocarAZonaDeReserva(carta);

		assert carta.zona instanceof ZonaReserva : "Error al pasar de zona la carta";
	}

    @Test
	void testInvocarDesdeManoEnEtapaPrincipalUnaCriaturaAZonaDeCombate(){		
		Integer puntos = 3;
	
		Tipo tipo = Tipo.Criatura;
        String nombre = "Carta1";
        Zona zona = new ZonaMano();

        Carta carta = new Carta(tipo, nombre, zona);
		
		Turno nuevoTurno = new Turno(mazoModoUno.getModo(), mazoModoUno, puntos);
        
		nuevoTurno.iniciarTurno();
		
		nuevoTurno.pasarDeEtapa();
		
		Etapa etapaActual = nuevoTurno.etapaActual();
		
		etapaActual.invocarAZonaDeCombate(carta);

		assert carta.zona instanceof ZonaCombate : "Error al pasar de zona la carta";
	}

    @Test
	void testInvocarDesdeManoEnEtapaPrincipalUnArtefacto(){		
		Integer puntos = 3;
	
		Tipo tipo = Tipo.Artefacto;
        String nombre = "Carta1";
        Zona zona = new ZonaMano();

        Carta carta = new Carta(tipo, nombre, zona);
		
		Turno nuevoTurno = new Turno(mazoModoUno.getModo(), mazoModoUno, puntos);
        
		nuevoTurno.iniciarTurno();
		
		nuevoTurno.pasarDeEtapa();
		
		Etapa etapaActual = nuevoTurno.etapaActual();
		
		etapaActual.invocarAZonaDeArtefacto(carta);

		assert carta.zona instanceof ZonaArtefecto : "Error al pasar de zona la carta";
	}

    @Test
	void testInvocarDesdeManoEnEtapaInicialUnArtefacto(){		
		Integer puntos = 3;
	
		Tipo tipo = Tipo.Artefacto;
        String nombre = "Carta1";
        Zona zona = new ZonaMano();

        Carta carta = new Carta(tipo, nombre, zona);
		
		Turno nuevoTurno = new Turno(mazoModoUno.getModo(), mazoModoUno, puntos);
        
		nuevoTurno.iniciarTurno();
		
		Etapa etapaActual = nuevoTurno.etapaActual();
		
		etapaActual.invocarAZonaDeArtefacto(carta);

		assert carta.zona instanceof ZonaMano : "Error al pasar de zona la carta";
	}

    @Test
	void testInvocarDesdeZonaDeReservaEnEtapaPrincipalUnArtefacto(){		
		Integer puntos = 3;
	
		Tipo tipo = Tipo.Artefacto;
        String nombre = "Carta1";
        Zona zona = new ZonaReserva();

        Carta carta = new Carta(tipo, nombre, zona);
		
		Turno nuevoTurno = new Turno(mazoModoUno.getModo(), mazoModoUno, puntos);
        
		nuevoTurno.iniciarTurno();
		
		nuevoTurno.pasarDeEtapa();
		
		Etapa etapaActual = nuevoTurno.etapaActual();
		
		etapaActual.invocarAZonaDeArtefacto(carta);

		assert carta.zona instanceof ZonaReserva : "Error al pasar de zona la carta";
	}
}
