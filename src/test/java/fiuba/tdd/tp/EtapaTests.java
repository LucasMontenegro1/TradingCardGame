package fiuba.tdd.tp;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import fiuba.tdd.tp.Excepciones.MazoInvalido;
import fiuba.tdd.tp.carta.Carta;
import fiuba.tdd.tp.carta.CartasDisponibles;
import fiuba.tdd.tp.etapa.Etapa;
import fiuba.tdd.tp.etapa.EtapaDeAtaque;
import fiuba.tdd.tp.etapa.EtapaFinal;
import fiuba.tdd.tp.etapa.EtapaInicial;
import fiuba.tdd.tp.etapa.EtapaPrincipal;
import fiuba.tdd.tp.mazo.Mazo;
import fiuba.tdd.tp.modo.Modo;
import fiuba.tdd.tp.modo.Modo1;
import fiuba.tdd.tp.modo.Modo2;
import fiuba.tdd.tp.zona.ZonaArtefecto;
import fiuba.tdd.tp.zona.ZonaCombate;
import fiuba.tdd.tp.zona.ZonaMano;
import fiuba.tdd.tp.zona.ZonaReserva;


@SpringBootTest
public class EtapaTests {

    private Mazo mazoModoUno;
	private Mazo mazoModoDos;

    @BeforeEach
    public void setUp() throws MazoInvalido {
		HashMap<String, Integer> cartas = new HashMap<>();
        
        Modo modoUno = new Modo1();
		Modo modoDos = new Modo2();

		cartas.put(CartasDisponibles.ENERGIA.nombre, 40);
        mazoModoUno = new Mazo(cartas, modoUno);

		cartas.put(CartasDisponibles.ENERGIA.nombre, 60);
		mazoModoDos = new Mazo(cartas, modoDos);
    }
    
    // Etapa inicial

    @Test
    void testCreacionDeUnaEtapaInicialPartidaModoUno(){
		
		Integer puntos = 15;

        Etapa etapaIncial = new EtapaInicial(this.mazoModoUno.getModo(), this.mazoModoUno, puntos);

        assert etapaIncial instanceof EtapaInicial;
        
    }

    @Test
    void testAlFinalizarEtapaInicialConCartaAsignadaInicialLaEtapaPrincipalEnUnaPartidaModoUno(){
		
		Integer puntos = 15;
        
        Etapa etapaIncial = new EtapaInicial(this.mazoModoUno.getModo(), this.mazoModoUno, puntos);

        etapaIncial.iniciar();

        Etapa siguienteEtapa = etapaIncial.finalizar();

        assert siguienteEtapa instanceof EtapaPrincipal;
    }

    @Test
    void testEnLaEtapaInicialNoSePuedenMoverCartasEnUnaPartidaModoUno(){
		
		Integer puntos = 15;
	
		Etapa etapaIncial = new EtapaInicial(this.mazoModoUno.getModo(), this.mazoModoUno, puntos);

        Carta carta = new Carta(CartasDisponibles.ALQUMISTA);

        carta.zona = new ZonaReserva();

        etapaIncial.iniciar();

        etapaIncial.moverCarta(carta);

        assert carta.zona instanceof ZonaReserva;
    }

    @Test
    void testEnLaEtapaInicialNoSePuedeInvocarUnaCriaturaAZonaDeCombateEnUnaPartidaModoUno(){
		
		Integer puntos = 15;
        
        Carta carta = new Carta(CartasDisponibles.ALQUMISTA);

        carta.zona = new ZonaMano();

        Etapa etapaIncial = new EtapaInicial(this.mazoModoUno.getModo(), this.mazoModoUno, puntos);

        etapaIncial.iniciar();

        etapaIncial.invocarAZonaDeCombate(carta);

        assert carta.zona instanceof ZonaMano;
    }

    @Test
    void testEnLaEtapaInicialNoSePuedeInvocarUnaCriaturaAZonaDeReservaEnUnaPartidaModoUno(){

        Integer puntos = 15;

        Carta carta = new Carta(CartasDisponibles.ALQUMISTA);

        carta.zona = new ZonaMano();
		
		Etapa etapaIncial = new EtapaInicial(this.mazoModoUno.getModo(), this.mazoModoUno, puntos);

        etapaIncial.iniciar();

        etapaIncial.invocarAZonaDeReserva(carta);

        assert carta.zona instanceof ZonaMano;
    }

    @Test
    void testEnLaEtapaInicialNoSePuedeInvocarUnArtefactoAZonaDeArtefactosEnUnaPartidaModoUno(){
		
		Integer puntos = 15;

        Carta carta = new Carta(CartasDisponibles.ANTIMAGIA);

        carta.zona = new ZonaMano();
		
		Etapa etapaIncial = new EtapaInicial(this.mazoModoUno.getModo(), this.mazoModoUno, puntos);

        etapaIncial.iniciar();

        etapaIncial.invocarAZonaDeArtefacto(carta);

        assert carta.zona instanceof ZonaMano;
    }

    @Test
    void testCreacionDeUnaEtapaInicialPartidaModoDos(){
		
		Integer puntos = 15;

        Etapa etapaIncial = new EtapaInicial(this.mazoModoDos.getModo(), this.mazoModoDos, puntos);

        assert etapaIncial instanceof EtapaInicial;
        
    }

    @Test
    void testAlFinalizarEtapaInicialConMasDeSeisPuntosDeVictoriaEnUnaPartidaModoDos(){
		
		Integer puntos = 15;
        
        Etapa etapaIncial = new EtapaInicial(this.mazoModoDos.getModo(), this.mazoModoDos, puntos);

        etapaIncial.iniciar();

        Etapa siguienteEtapa = etapaIncial.finalizar();

        assert siguienteEtapa instanceof EtapaFinal;
    }

    @Test
    void testEnLaEtapaInicialNoSePuedenMoverCartasEnUnaPartidaModoDos(){
		
		Integer puntos = 4;
	
		Etapa etapaIncial = new EtapaInicial(this.mazoModoDos.getModo(), this.mazoModoDos, puntos);

        Carta carta = new Carta(CartasDisponibles.ALQUMISTA);
        
        carta.zona = new ZonaReserva();

        etapaIncial.iniciar();

        etapaIncial.moverCarta(carta);

        assert carta.zona instanceof ZonaReserva;
    }

    @Test
    void testEnLaEtapaInicialNoSePuedeInvocarUnaCriaturaAZonaDeCombateEnUnaPartidaModoDos(){
		
		Integer puntos = 4;
        
        Carta carta = new Carta(CartasDisponibles.ALQUMISTA);
        
        carta.zona = new ZonaMano();

        Etapa etapaIncial = new EtapaInicial(this.mazoModoDos.getModo(), this.mazoModoDos, puntos);

        etapaIncial.iniciar();

        etapaIncial.invocarAZonaDeCombate(carta);

        assert carta.zona instanceof ZonaMano;
    }

    @Test
    void testEnLaEtapaInicialNoSePuedeInvocarUnaCriaturaAZonaDeReservaEnUnaPartidaModoDos(){

        Integer puntos = 4;

        Carta carta = new Carta(CartasDisponibles.ALQUMISTA);
        
        carta.zona = new ZonaMano();
		
		Etapa etapaIncial = new EtapaInicial(this.mazoModoDos.getModo(), this.mazoModoDos, puntos);

        etapaIncial.iniciar();

        etapaIncial.invocarAZonaDeReserva(carta);

        assert carta.zona instanceof ZonaMano;
    }

    @Test
    void testEnLaEtapaInicialNoSePuedeInvocarUnArtefactoAZonaDeArtefactosEnUnaPartidaModoDos(){
		
		Integer puntos = 4;

        Carta carta = new Carta(CartasDisponibles.ANTIMAGIA);
        
        carta.zona = new ZonaMano();
		
		Etapa etapaIncial = new EtapaInicial(this.mazoModoDos.getModo(), this.mazoModoDos, puntos);

        etapaIncial.iniciar();

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
    
        Carta carta = new Carta(CartasDisponibles.ALQUMISTA);
        
        carta.zona = new ZonaReserva();

        Etapa etapaPrincipal = new EtapaPrincipal();

        etapaPrincipal.iniciar();

        etapaPrincipal.moverCarta(carta);

        assert carta.zona instanceof ZonaCombate;
    }

    @Test
    void testEnLaEtapaPrincipalSePuedeInvocarUnaCriaturaAZonaDeCombate(){
        
        Carta carta = new Carta(CartasDisponibles.ALQUMISTA);
        
        carta.zona = new ZonaMano();

        Etapa etapaPrincipal = new EtapaPrincipal();

        etapaPrincipal.iniciar();

        etapaPrincipal.invocarAZonaDeCombate(carta);

        assert carta.zona instanceof ZonaCombate;
    }

    @Test
    void testEnLaEtapaPrincipalSePuedeInvocarUnaCriaturaAZonaDeReserva(){
        
        Carta carta = new Carta(CartasDisponibles.ALQUMISTA);
        
        carta.zona = new ZonaMano();

        Etapa etapaPrincipal = new EtapaPrincipal();

        etapaPrincipal.iniciar();

        etapaPrincipal.invocarAZonaDeReserva(carta);

        assert carta.zona instanceof ZonaReserva;
    }

    @Test
    void testEnLaEtapaPrincipalSePuedeInvocarUnArtefactoAZonaDeArtefactos(){

        Carta carta = new Carta(CartasDisponibles.ANTIMAGIA);
        
        carta.zona = new ZonaMano();

        Etapa etapaPrincipal = new EtapaPrincipal();

        etapaPrincipal.iniciar();

        etapaPrincipal.invocarAZonaDeArtefacto(carta);

        assert carta.zona instanceof ZonaArtefecto;
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

        assert siguienteEtapa instanceof EtapaFinal;
    }

    @Test
    void testEnLaEtapaDeAtaqueNoSePuedenMoverCartas(){

        Carta carta = new Carta(CartasDisponibles.ALQUMISTA);
        
        carta.zona = new ZonaReserva();

        Etapa etapaDeAtaque = new EtapaDeAtaque();

        etapaDeAtaque.iniciar();

        etapaDeAtaque.moverCarta(carta);

        assert carta.zona instanceof ZonaReserva;
    }

    @Test
    void testEnLaEtapaDeAtaqueNoSePuedeInvocarUnaCriaturaAZonaDeCombate(){
        
        Carta carta = new Carta(CartasDisponibles.ALQUMISTA);
        
        carta.zona = new ZonaMano();

        Etapa etapaDeAtaque = new EtapaDeAtaque();

        etapaDeAtaque.iniciar();

        etapaDeAtaque.invocarAZonaDeCombate(carta);

        assert carta.zona instanceof ZonaMano;
    }

    @Test
    void testEnLaEtapaDeAtaqueNoSePuedeInvocarUnaCriaturaAZonaDeReserva(){
    

        Carta carta = new Carta(CartasDisponibles.ALQUMISTA);
        
        carta.zona = new ZonaMano();

        Etapa etapaDeAtaque = new EtapaDeAtaque();

        etapaDeAtaque.iniciar();

        etapaDeAtaque.invocarAZonaDeReserva(carta);

        assert carta.zona instanceof ZonaMano;
    }

    @Test
    void testEnLaEtapaDeAtaqueNoSePuedeInvocarUnArtefactoAZonaDeArtefactos(){
        
        Carta carta = new Carta(CartasDisponibles.ANTIMAGIA);
        
        carta.zona = new ZonaMano();

        Etapa etapaDeAtaque = new EtapaDeAtaque();

        etapaDeAtaque.iniciar();

        etapaDeAtaque.invocarAZonaDeArtefacto(carta);

        assert carta.zona instanceof ZonaMano;
    }

    // Etapa final

    @Test
    void testCreacionDeUnaEtapaFinal(){
        Etapa etapaFinal = new EtapaFinal();

        assert etapaFinal instanceof EtapaFinal;
    }

    @Test
    void testEnLaEtapaFinalNoSePuedenMoverCartas(){

        Carta carta = new Carta(CartasDisponibles.ALQUMISTA);
        
        carta.zona = new ZonaReserva();

        Etapa etapaFinal = new EtapaFinal();

        etapaFinal.iniciar();

        etapaFinal.moverCarta(carta);

        assert carta.zona instanceof ZonaReserva;
    }

    @Test
    void testEnLaEtapaFinalNoSePuedeInvocarUnaCriaturaAZonaDeCombate(){
        
        Carta carta = new Carta(CartasDisponibles.ALQUMISTA);
        
        carta.zona = new ZonaMano();

        Etapa etapaFinal = new EtapaFinal();

        etapaFinal.iniciar();

        etapaFinal.invocarAZonaDeCombate(carta);

        assert carta.zona instanceof ZonaMano;
    }

    @Test
    void testEnLaEtapaFinalNoSePuedeInvocarUnaCriaturaAZonaDeReserva(){
    
        Carta carta = new Carta(CartasDisponibles.ALQUMISTA);
        
        carta.zona = new ZonaMano();

        Etapa etapaFinal = new EtapaFinal();

        etapaFinal.iniciar();

        etapaFinal.invocarAZonaDeReserva(carta);

        assert carta.zona instanceof ZonaMano;
    }

    @Test
    void testEnLaEtapaFinalNoSePuedeInvocarUnArtefactoAZonaDeArtefactos(){
        
        Carta carta = new Carta(CartasDisponibles.ANTIMAGIA);
        
        carta.zona = new ZonaMano();

        Etapa etapaFinal = new EtapaFinal();

        etapaFinal.iniciar();

        etapaFinal.invocarAZonaDeArtefacto(carta);

        assert carta.zona instanceof ZonaMano;
    }

}
