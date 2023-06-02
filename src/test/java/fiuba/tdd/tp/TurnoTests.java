package fiuba.tdd.tp;

import org.junit.jupiter.api.Test;

import fiuba.tdd.tp.carta.Carta;
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
    @Test
	void testCreacionTurno() {
		Modo modo = new Modo1();
		
		Integer puntos = 15;
	
		Mazo mazo = new Mazo();

        Turno nuevoTurno = new Turno(modo, mazo, puntos);
        
        assert nuevoTurno instanceof Turno : "No es una instancia de Turno";
	}

    @Test
	void testIniciarTurno(){
		Modo modo = new Modo1();
		
		Integer puntos = 15;
	
		Mazo mazo = new Mazo();

        Turno nuevoTurno = new Turno(modo, mazo, puntos);
        
        assert nuevoTurno.etapaActual() instanceof EtapaInicial : "Error al iniciar el turno";
	}

    @Test 
	void testPasarDeEtapaEnTurnoModoUno(){
		Modo modo = new Modo1();
		
		Integer puntos = 4;
		
        Mazo mazo = new Mazo();

        Tipo tipo = Tipo.Criatura;
        String nombre = "Carta1";
        String descripcion = "Descripción de la carta";
        Zona zona = new ZonaMano();

        Carta carta = new Carta(tipo, nombre, descripcion, zona);
		
		mazo.agregarCarta(carta);

        Turno nuevoTurno = new Turno(modo, mazo, puntos);
        
		nuevoTurno.iniciarTurno();
		
		nuevoTurno.pasarDeEtapa();

        assert nuevoTurno.etapaActual() instanceof EtapaPrincipal : "Error al pasar de etapa en el turno";
	}


    @Test 
	void testPasarDeEtapaEnTurnoModoDosConMenosDeSeisPuntosDeVictoria(){
		Modo modo = new Modo2();
		
		Integer puntos = 2;
		
        Mazo mazo = new Mazo();

        Tipo tipo = Tipo.Criatura;
        String nombre = "Carta1";
        String descripcion = "Descripción de la carta";
        Zona zona = new ZonaMano();

        Carta carta = new Carta(tipo, nombre, descripcion, zona);
		
		mazo.agregarCarta(carta);
		
        Turno nuevoTurno = new Turno(modo, mazo, puntos);
        
		nuevoTurno.iniciarTurno();
		
		nuevoTurno.pasarDeEtapa();

        assert nuevoTurno.etapaActual() instanceof EtapaPrincipal : "Error al pasar de etapa en el turno";
	}

    @Test 
	void testPasarDeEtapaEnTurnoModoDosConMasDeSeisPuntosDeVictoria(){
        Modo modo = new Modo2();
		
		Integer puntos = 10;
		
        Mazo mazo = new Mazo();

        Tipo tipo = Tipo.Criatura;
        String nombre = "Carta1";
        String descripcion = "Descripción de la carta";
        Zona zona = new ZonaMano();

        Carta carta = new Carta(tipo, nombre, descripcion, zona);
		
		mazo.agregarCarta(carta);
		
        Turno nuevoTurno = new Turno(modo, mazo, puntos);
        
		nuevoTurno.iniciarTurno();
		
		nuevoTurno.pasarDeEtapa();
        
        assert nuevoTurno.etapaActual() instanceof EtapaFinal : "Error al pasar de etapa en el turno";

    }

    @Test
	void testMoverUnaCriaturaEnEtapaPrincipal(){
		Modo modo = new Modo1();
		
		Integer puntos = 3;
	
		Mazo mazo = new Mazo();
		Tipo tipo = Tipo.Criatura;
        String nombre = "Carta1";
        String descripcion = "Descripción de la carta";
        Zona zona = new ZonaReserva();

        Carta carta = new Carta(tipo, nombre, descripcion, zona);
		
		mazo.agregarCarta(carta);
		
        Turno nuevoTurno = new Turno(modo, mazo, puntos);
        
		nuevoTurno.iniciarTurno();
		
		nuevoTurno.pasarDeEtapa();
		
		Etapa etapaActual = nuevoTurno.etapaActual();
		
		etapaActual.moverCarta(carta);

		assert carta.zona instanceof ZonaCombate : "Error al pasar de zona la carta";
        
	}

    @Test
	void testNoSePuedeMoverUnaCriaturaEnEtapaInicial(){
		Modo modo = new Modo1();
		
		Integer puntos = 3;
		
		Mazo mazo = new Mazo();
		Tipo tipo = Tipo.Criatura;
        String nombre = "Carta1";
        String descripcion = "Descripción de la carta";
        Zona zona = new ZonaReserva();

        Carta carta = new Carta(tipo, nombre, descripcion, zona);
		
		mazo.agregarCarta(carta);
		
        Turno nuevoTurno = new Turno(modo, mazo, puntos);
        
		nuevoTurno.iniciarTurno();
		
		Etapa etapaActual = nuevoTurno.etapaActual();
		
		etapaActual.moverCarta(carta);

		assert carta.zona instanceof ZonaReserva : "Error al pasar de zona la carta";

	}

    @Test
	void testInvocarDesdeManoEnEtapaPrincipalUnaCriaturaAZonaReserva(){
		Modo modo = new Modo1();
		
		Integer puntos = 3;
	
		Mazo mazo = new Mazo();
		Tipo tipo = Tipo.Criatura;
        String nombre = "Carta1";
        String descripcion = "Descripción de la carta";
        Zona zona = new ZonaMano();

        Carta carta = new Carta(tipo, nombre, descripcion, zona);
		
		mazo.agregarCarta(carta);
		
        Turno nuevoTurno = new Turno(modo, mazo, puntos);
        
		nuevoTurno.iniciarTurno();
		
		nuevoTurno.pasarDeEtapa();
		
		Etapa etapaActual = nuevoTurno.etapaActual();
		
		etapaActual.invocarAZonaDeReserva(carta);

		assert carta.zona instanceof ZonaReserva : "Error al pasar de zona la carta";
	}

    @Test
	void testInvocarDesdeManoEnEtapaPrincipalUnaCriaturaAZonaDeCombate(){
		Modo modo = new Modo1();
		
		Integer puntos = 3;
	
		Mazo mazo = new Mazo();
		Tipo tipo = Tipo.Criatura;
        String nombre = "Carta1";
        String descripcion = "Descripción de la carta";
        Zona zona = new ZonaMano();

        Carta carta = new Carta(tipo, nombre, descripcion, zona);
		
		mazo.agregarCarta(carta);
		
        Turno nuevoTurno = new Turno(modo, mazo, puntos);
        
		nuevoTurno.iniciarTurno();
		
		nuevoTurno.pasarDeEtapa();
		
		Etapa etapaActual = nuevoTurno.etapaActual();
		
		etapaActual.invocarAZonaDeCombate(carta);

		assert carta.zona instanceof ZonaCombate : "Error al pasar de zona la carta";
	}

    @Test
	void testInvocarDesdeManoEnEtapaPrincipalUnArtefacto(){
		Modo modo = new Modo1();
		
		Integer puntos = 3;
	
		Mazo mazo = new Mazo();
		Tipo tipo = Tipo.Artefacto;
        String nombre = "Carta1";
        String descripcion = "Descripción de la carta";
        Zona zona = new ZonaMano();

        Carta carta = new Carta(tipo, nombre, descripcion, zona);
		
		mazo.agregarCarta(carta);
		
        Turno nuevoTurno = new Turno(modo, mazo, puntos);
        
		nuevoTurno.iniciarTurno();
		
		nuevoTurno.pasarDeEtapa();
		
		Etapa etapaActual = nuevoTurno.etapaActual();
		
		etapaActual.invocarAZonaDeArtefacto(carta);

		assert carta.zona instanceof ZonaArtefecto : "Error al pasar de zona la carta";
	}

    @Test
	void testInvocarDesdeManoEnEtapaInicialUnArtefacto(){
		Modo modo = new Modo1();
		
		Integer puntos = 3;
	
		Mazo mazo = new Mazo();
		Tipo tipo = Tipo.Artefacto;
        String nombre = "Carta1";
        String descripcion = "Descripción de la carta";
        Zona zona = new ZonaMano();

        Carta carta = new Carta(tipo, nombre, descripcion, zona);
		
		mazo.agregarCarta(carta);
		
        Turno nuevoTurno = new Turno(modo, mazo, puntos);
        
		nuevoTurno.iniciarTurno();
		
		Etapa etapaActual = nuevoTurno.etapaActual();
		
		etapaActual.invocarAZonaDeArtefacto(carta);

		assert carta.zona instanceof ZonaMano : "Error al pasar de zona la carta";
	}

    @Test
	void testInvocarDesdeZonaDeReservaEnEtapaPrincipalUnArtefacto(){
		Modo modo = new Modo1();
		
		Integer puntos = 3;
	
		Mazo mazo = new Mazo();
		Tipo tipo = Tipo.Artefacto;
        String nombre = "Carta1";
        String descripcion = "Descripción de la carta";
        Zona zona = new ZonaReserva();

        Carta carta = new Carta(tipo, nombre, descripcion, zona);
		
		mazo.agregarCarta(carta);
		
        Turno nuevoTurno = new Turno(modo, mazo, puntos);
        
		nuevoTurno.iniciarTurno();
		
		nuevoTurno.pasarDeEtapa();
		
		Etapa etapaActual = nuevoTurno.etapaActual();
		
		etapaActual.invocarAZonaDeArtefacto(carta);

		assert carta.zona instanceof ZonaReserva : "Error al pasar de zona la carta";
	}
}
