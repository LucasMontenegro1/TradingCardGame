package fiuba.tdd.tp;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import fiuba.tdd.tp.Excepciones.MazoInvalido;
import fiuba.tdd.tp.carta.Carta;
import fiuba.tdd.tp.carta.CartasDisponibles;
import fiuba.tdd.tp.etapa.Etapa;
import fiuba.tdd.tp.etapa.EtapaDeAtaque;
import fiuba.tdd.tp.etapa.EtapaInicial;
import fiuba.tdd.tp.etapa.EtapaPrincipal;
import fiuba.tdd.tp.modo.Modo;
import fiuba.tdd.tp.modo.Modo1;
import fiuba.tdd.tp.modo.Modo2;
import fiuba.tdd.tp.zona.ZonaArtefacto;
import fiuba.tdd.tp.zona.ZonaCombate;
import fiuba.tdd.tp.zona.ZonaMano;
import fiuba.tdd.tp.zona.ZonaReserva;

@SpringBootTest
public class EtapaTests {

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
    // Etapa inicial

    @Test
    void testCreacionDeUnaEtapaInicialPartidaModoUno(){
		
		Integer puntos = 15;

        Etapa etapaIncial = new EtapaInicial(this.modo1, puntos);

        assert etapaIncial instanceof EtapaInicial;
        
    }

    @Test
    void testAlFinalizarEtapaInicialConCartaAsignadaInicialLaEtapaPrincipalEnUnaPartidaModoUno(){
		
		Integer puntos = 15;
        
        Etapa etapaIncial = new EtapaInicial(this.modo1, puntos);

        etapaIncial.iniciar(this.cartas);

        Etapa siguienteEtapa = etapaIncial.finalizar();

        assert siguienteEtapa instanceof EtapaPrincipal;
    }

    @Test
    void testEnLaEtapaInicialNoSePuedenMoverCartasEnUnaPartidaModoUno(){
		
		Integer puntos = 15;
	
		Etapa etapaIncial = new EtapaInicial(this.modo1, puntos);

        Carta carta = new Carta(CartasDisponibles.ALQUIMISTA);
        carta.zona = new ZonaReserva();

        etapaIncial.iniciar(this.cartas);

        etapaIncial.moverCarta(carta);

        assert carta.zona instanceof ZonaReserva;
    }

    @Test
    void testEnLaEtapaInicialNoSePuedeInvocarUnaCriaturaAZonaDeCombateEnUnaPartidaModoUno(){
		
		Integer puntos = 15;
        
        Carta carta = new Carta(CartasDisponibles.ALQUIMISTA);

        carta.zona = new ZonaMano();

        Etapa etapaIncial = new EtapaInicial(this.modo1, puntos);

        etapaIncial.iniciar(this.cartas);

        etapaIncial.invocarAZonaDeCombate(carta);

        assert carta.zona instanceof ZonaMano;
    }

    @Test
    void testEnLaEtapaInicialNoSePuedeInvocarUnaCriaturaAZonaDeReservaEnUnaPartidaModoUno(){

        Integer puntos = 15;

        Carta carta = new Carta(CartasDisponibles.ALQUIMISTA);

        carta.zona = new ZonaMano();
		
		Etapa etapaIncial = new EtapaInicial(this.modo1, puntos);

        etapaIncial.iniciar(this.cartas);

        etapaIncial.invocarAZonaDeReserva(carta);

        assert carta.zona instanceof ZonaMano;
    }

    @Test
    void testEnLaEtapaInicialNoSePuedeInvocarUnArtefactoAZonaDeArtefactosEnUnaPartidaModoUno(){
		
		Integer puntos = 15;

        Carta carta = new Carta(CartasDisponibles.ANTIMAGIA);

        carta.zona = new ZonaMano();
		
		Etapa etapaIncial = new EtapaInicial(this.modo1, puntos);

        etapaIncial.iniciar(this.cartas);

        etapaIncial.invocarAZonaDeArtefacto(carta);

        assert carta.zona instanceof ZonaMano;
    }

    @Test
    void testCreacionDeUnaEtapaInicialPartidaModoDos(){
		
		Integer puntos = 15;

        Etapa etapaIncial = new EtapaInicial(this.modo2, puntos);

        assert etapaIncial instanceof EtapaInicial;
        
    }

    @Test
    void testAlFinalizarEtapaInicialConMasDeSeisPuntosDeVictoriaEnUnaPartidaModoDos(){
		
		Integer puntos = 15;
        
        Etapa etapaIncial = new EtapaInicial(this.modo2, puntos);

        etapaIncial.iniciar(this.cartas);

        Etapa siguienteEtapa = etapaIncial.finalizar();

        assert siguienteEtapa  == null;
    }

    @Test
    void testEnLaEtapaInicialNoSePuedenMoverCartasEnUnaPartidaModoDos(){
		
		Integer puntos = 4;
	
		Etapa etapaIncial = new EtapaInicial(this.modo2, puntos);

        Carta carta = new Carta(CartasDisponibles.ALQUIMISTA);
        
        carta.zona = new ZonaReserva();

        etapaIncial.iniciar(this.cartas);

        etapaIncial.moverCarta(carta);

        assert carta.zona instanceof ZonaReserva;
    }

    @Test
    void testEnLaEtapaInicialNoSePuedeInvocarUnaCriaturaAZonaDeCombateEnUnaPartidaModoDos(){
		
		Integer puntos = 4;
        
        Carta carta = new Carta(CartasDisponibles.ALQUIMISTA);
        
        carta.zona = new ZonaMano();

        Etapa etapaIncial = new EtapaInicial(this.modo2, puntos);

        etapaIncial.iniciar(this.cartas);

        etapaIncial.invocarAZonaDeCombate(carta);

        assert carta.zona instanceof ZonaMano;
    }

    @Test
    void testEnLaEtapaInicialNoSePuedeInvocarUnaCriaturaAZonaDeReservaEnUnaPartidaModoDos(){

        Integer puntos = 4;

        Carta carta = new Carta(CartasDisponibles.ALQUIMISTA);
        
        carta.zona = new ZonaMano();
		
		Etapa etapaIncial = new EtapaInicial(this.modo2, puntos);

        etapaIncial.iniciar(this.cartas);

        etapaIncial.invocarAZonaDeReserva(carta);

        assert carta.zona instanceof ZonaMano;
    }

    @Test
    void testEnLaEtapaInicialNoSePuedeInvocarUnArtefactoAZonaDeArtefactosEnUnaPartidaModoDos(){
		
		Integer puntos = 4;

        Carta carta = new Carta(CartasDisponibles.ANTIMAGIA);
        
        carta.zona = new ZonaMano();
		
		Etapa etapaIncial = new EtapaInicial(this.modo2, puntos);

        etapaIncial.iniciar(this.cartas);

        etapaIncial.invocarAZonaDeArtefacto(carta);

        assert carta.zona instanceof ZonaMano;
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
    void testEnLaEtapaPrincipalSePuedenMoverCartas(){
    
        Carta carta = new Carta(CartasDisponibles.ALQUIMISTA);
        
        carta.zona = new ZonaReserva();

        Etapa etapaPrincipal = new EtapaPrincipal();

        etapaPrincipal.iniciar(this.cartas);

        etapaPrincipal.moverCarta(carta);

        assert carta.zona instanceof ZonaCombate;
    }

    @Test
    void testEnLaEtapaPrincipalSePuedeInvocarUnaCriaturaAZonaDeCombate(){
        
        Carta carta = new Carta(CartasDisponibles.ALQUIMISTA);
        
        carta.zona = new ZonaMano();

        Etapa etapaPrincipal = new EtapaPrincipal();

        etapaPrincipal.iniciar(this.cartas);

        etapaPrincipal.invocarAZonaDeCombate(carta);

        assert carta.zona instanceof ZonaCombate;
    }

    @Test
    void testEnLaEtapaPrincipalSePuedeInvocarUnaCriaturaAZonaDeReserva(){
        
        Carta carta = new Carta(CartasDisponibles.ALQUIMISTA);
        
        carta.zona = new ZonaMano();

        Etapa etapaPrincipal = new EtapaPrincipal();

        etapaPrincipal.iniciar(this.cartas);

        etapaPrincipal.invocarAZonaDeReserva(carta);

        assert carta.zona instanceof ZonaReserva;
    }

    @Test
    void testEnLaEtapaPrincipalSePuedeInvocarUnArtefactoAZonaDeArtefactos(){

        Carta carta = new Carta(CartasDisponibles.ANTIMAGIA);
        
        carta.zona = new ZonaMano();

        Etapa etapaPrincipal = new EtapaPrincipal();

        etapaPrincipal.iniciar(this.cartas);

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
    void testEnLaEtapaDeAtaqueNoSePuedenMoverCartas(){

        Carta carta = new Carta(CartasDisponibles.ALQUIMISTA);
        
        carta.zona = new ZonaReserva();

        Etapa etapaDeAtaque = new EtapaDeAtaque();

        etapaDeAtaque.iniciar(this.cartas);

        etapaDeAtaque.moverCarta(carta);

        assert carta.zona instanceof ZonaReserva;
    }

    @Test
    void testEnLaEtapaDeAtaqueNoSePuedeInvocarUnaCriaturaAZonaDeCombate(){
        
        Carta carta = new Carta(CartasDisponibles.ALQUIMISTA);
        
        carta.zona = new ZonaMano();

        Etapa etapaDeAtaque = new EtapaDeAtaque();

        etapaDeAtaque.iniciar(this.cartas);

        etapaDeAtaque.invocarAZonaDeCombate(carta);

        assert carta.zona instanceof ZonaMano;
    }

    @Test
    void testEnLaEtapaDeAtaqueNoSePuedeInvocarUnaCriaturaAZonaDeReserva(){
    

        Carta carta = new Carta(CartasDisponibles.ALQUIMISTA);
        
        carta.zona = new ZonaMano();

        Etapa etapaDeAtaque = new EtapaDeAtaque();

        etapaDeAtaque.iniciar(this.cartas);

        etapaDeAtaque.invocarAZonaDeReserva(carta);

        assert carta.zona instanceof ZonaMano;
    }

    @Test
    void testEnLaEtapaDeAtaqueNoSePuedeInvocarUnArtefactoAZonaDeArtefactos(){
        
        Carta carta = new Carta(CartasDisponibles.ANTIMAGIA);
        
        carta.zona = new ZonaMano();

        Etapa etapaDeAtaque = new EtapaDeAtaque();

        etapaDeAtaque.iniciar(this.cartas);

        etapaDeAtaque.invocarAZonaDeArtefacto(carta);

        assert carta.zona instanceof ZonaMano;
    }

    
}
