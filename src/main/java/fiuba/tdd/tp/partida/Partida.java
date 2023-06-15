package fiuba.tdd.tp.partida;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import fiuba.tdd.tp.Excepciones.CartaNoActivable;
import fiuba.tdd.tp.Excepciones.CartaNoEncontrada;
import fiuba.tdd.tp.Excepciones.EnergiaInsuficiente;
import fiuba.tdd.tp.Excepciones.ModoSinPuntosDeVida;
import fiuba.tdd.tp.Excepciones.MovimientoInvalido;
import fiuba.tdd.tp.Excepciones.PartidaInvalida;
import fiuba.tdd.tp.carta.Carta;
import fiuba.tdd.tp.carta.Energia;
import fiuba.tdd.tp.carta.Metodos.MetodoCarta;
import fiuba.tdd.tp.etapa.Etapa;
import fiuba.tdd.tp.etapa.EtapaInicial;
import fiuba.tdd.tp.jugador.Mazo;
import fiuba.tdd.tp.modo.Modo;
import fiuba.tdd.tp.tablero.Tablero;
import fiuba.tdd.tp.turno.Turno;
import fiuba.tdd.tp.zona.ZonaArtefacto;
import fiuba.tdd.tp.zona.ZonaCombate;
import fiuba.tdd.tp.zona.ZonaReserva;

public class Partida {

    final static String ZonaArtefacto = "ZonaArtefacto";
    final static String ZonaCombate = "ZonaCombate";
    final static String ZonaReserva = "ZonaReserva";

    public String jugadorEnTurno;
    private Tablero tablero1;
    private Tablero tablero2;
    private Turno turno;
    private Modo modo;
    public Deque<Ejecucion> pilaDeEjecucion;
    private boolean partidaEnJuego;
    private String ganador;
    public Integer maxZonaCombate;
    public Integer maxZonaReserva;
    public Integer maxZonaArtefactos;

    public Partida(Modo modoPartida, String unJugador, String otroJugador, Mazo unMazo, Mazo otroMazo) throws PartidaInvalida {
        if (!modoPartida.verificarMazoValido(unMazo.cartas) || !modoPartida.verificarMazoValido(otroMazo.cartas)) {
            throw new PartidaInvalida("Los mazos de los jugadores no son compatibles");
        }
        this.modo = modoPartida;
        this.partidaEnJuego = true;
        this.ganador = null;
        this.pilaDeEjecucion = null;
		this.jugadorEnTurno = unJugador;
        this.tablero1 = new Tablero(unJugador, unMazo, modoPartida);
        this.tablero2 = new Tablero(otroJugador, otroMazo, modoPartida);
        this.turno = new Turno(modoPartida, this.tablero1.puntos);
        this.maxZonaReserva = modoPartida.getMaxZonaReserva();
        this.maxZonaArtefactos = modoPartida.getMaxZonaArtefactos();
        this.maxZonaCombate = modoPartida.getMaxZonaCombate();
    }

    public Turno turnoEnProceso() {
        return turno;
    }

    public void iniciarPartida() throws MovimientoInvalido {

        tablero1.iniciarTablero();
        tablero2.iniciarTablero();
        
        this.modo.iniciarTableros(tablero1.cartas, tablero2.cartas);

        this.turno.iniciarTurno(this.tablero1.cartasEnZona(null));
        this.turno.pasarDeEtapa();
    }

    public Tablero tableroJugador(String jugador) {
        if (this.tablero1.usuario == jugador) {
            return this.tablero1;
        } else if (this.tablero2.usuario == jugador) {
            return this.tablero2;
        }

        return null;
    }

    public Integer puntosDeVida(String jugador) throws ModoSinPuntosDeVida {
        
        Tablero tablero = tableroJugador(jugador);

        return modo.obtenerPuntosDeVida(tablero);
    }
    
    public Tablero tableroEnTurno() {
        return tableroJugador(jugadorEnTurno);
    }

    public Tablero tableroEnEspera() {
        if (tablero1.usuario == jugadorEnTurno) {
            return tablero2;
        }

        return tablero1;
    }

    public void terminarEtapa() throws MovimientoInvalido {
        this.turno.pasarDeEtapa();
    
        if (this.turno.etapaActual() == null) {
            if (jugadorEnTurno == tablero1.usuario) {
                this.turno = new Turno(this.modo, this.tablero2.puntos);
                this.jugadorEnTurno = tablero2.usuario;
            } else {
                this.turno = new Turno(this.modo, this.tablero1.puntos);
                this.jugadorEnTurno = tablero1.usuario;
            }
        }

        Tablero tablero = tableroEnTurno();
        this.turno.etapaActual().iniciar(tablero.cartas);
        if (this.turno.etapaActual() instanceof EtapaInicial) {
            terminarEtapa();
        }
    }

    public boolean jugadorEnTurno(String unJugador) {
        return unJugador == jugadorEnTurno;
    }
    
    private String verificarGanador(Tablero tablero) {
        if (!this.modo.partidaEnProceso(tablero.puntos)) {
            this.partidaEnJuego = false;
            
            if (tablero == tablero1) {
                return tablero2.usuario;
            }
            
            return tablero1.usuario;
        }

        return null;
    }

    public String ganador() {
        
        return verificarGanador(tablero1) == null ? verificarGanador(tablero2) : verificarGanador(tablero1);
    }

    private void invocarPorZona(String zona, Etapa etapaActual, Tablero tablero, Carta cartaEnTablero) throws MovimientoInvalido {
        switch (zona) {
            case ZonaArtefacto -> {
                if (tablero.cartasEnZona(ZonaArtefacto.class.getSimpleName()).size() < maxZonaArtefactos) {
                    etapaActual.invocarAZonaDeArtefacto(cartaEnTablero);
                }
            }
            case ZonaCombate -> {
                if (tablero.cartasEnZona(ZonaCombate.class.getSimpleName()).size() < maxZonaCombate) {
                    etapaActual.invocarAZonaDeCombate(cartaEnTablero);
                }
            }
            case ZonaReserva -> {
                if (tablero.cartasEnZona(ZonaReserva.class.getSimpleName()).size() < maxZonaReserva) {
                    etapaActual.invocarAZonaDeReserva(cartaEnTablero);
                }
            }
        }
    }

    public Carta invocarCarta(String jugador, String carta, String zona) throws CartaNoEncontrada, EnergiaInsuficiente, MovimientoInvalido {

        Tablero tablero = tableroJugador(jugador);

        Carta cartaEnTablero = tablero.buscarCarta(carta);
        if (cartaEnTablero == null) {
            throw new CartaNoEncontrada("La carta no existe");
        } 

        Etapa etapaActual = turnoEnProceso().etapaActual();

        if (tablero.verificarEnergia(cartaEnTablero)) {
            invocarPorZona(zona, etapaActual, tablero, cartaEnTablero); 
            tablero.disminuirEnergia(Energia.Fuego, cartaEnTablero.costoDeInvocacion.get(Energia.Fuego));
            tablero.disminuirEnergia(Energia.Agua, cartaEnTablero.costoDeInvocacion.get(Energia.Agua));
            tablero.disminuirEnergia(Energia.Planta, cartaEnTablero.costoDeInvocacion.get(Energia.Planta));
        }
        
        return cartaEnTablero;
    }

    public void activarCarta(Carta carta, Integer indiceMetodo, String jugadorObjetivo, ArrayList<Carta> cartasObjetivos, Energia energia) throws CartaNoActivable {        
        Etapa etapa = turnoEnProceso().etapaActual();
        Tablero tablero = tableroEnTurno();
        Tablero tableroContrincante = tableroEnEspera();
        HashMap<Carta, ArrayList<MetodoCarta>> cartas = null;
        
        if (tablero.cartas.contains(carta)) {
            cartas = tablero.cartasUsables(etapa, pilaDeEjecucion); 
        } else if (tableroContrincante.cartas.contains(carta)) {
            cartas = tableroContrincante.cartasUsables(etapa, pilaDeEjecucion); 
            tableroContrincante = tableroEnTurno();
            tablero = tableroEnEspera();
        } else {
            throw new CartaNoActivable("Esta carta no puede ser activada en este momento");
        }
        
        if (cartas.containsKey(carta)) {
            MetodoCarta metodo = cartas.get(carta).get(indiceMetodo);
            if (pilaDeEjecucion != null) {
                Ejecucion nuevaEjecucion = new Ejecucion(metodo, tablero, tableroContrincante, pilaDeEjecucion, jugadorObjetivo, cartasObjetivos, carta, energia);
                pilaDeEjecucion.push(nuevaEjecucion);
            } else {
                System.out.println("Entro acá");
                metodo.ejecutar(tablero, tableroContrincante, pilaDeEjecucion, jugadorObjetivo, cartasObjetivos, carta, energia);
            }
        }
    }

    public void iniciarPilaDeEjecucion() {
        this.pilaDeEjecucion = new ArrayDeque<>();
    }
    
    public void ejecutarPila() {
        
        for (Ejecucion ejecucion : pilaDeEjecucion) {
            ejecucion.ejecutar();
            ganador = verificarGanador(tablero1);
            ganador = verificarGanador(tablero2);
            if (!partidaEnJuego) {
                return;
            }
        }

        pilaDeEjecucion = null;
    }
}