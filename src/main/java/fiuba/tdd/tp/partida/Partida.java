package fiuba.tdd.tp.partida;

import java.util.Deque;
import fiuba.tdd.tp.Excepciones.PartidaInvalida;
import fiuba.tdd.tp.carta.Carta;
import fiuba.tdd.tp.carta.Energia;
import fiuba.tdd.tp.carta.Metodos.MetodoCarta;
import fiuba.tdd.tp.jugador.Mazo;
import fiuba.tdd.tp.modo.Modo;
import fiuba.tdd.tp.tablero.Tablero;
import fiuba.tdd.tp.turno.Turno;

public class Partida {

    private String jugadorEnTurno;
    private Tablero tablero1;
    private Tablero tablero2;
    private Turno turno;
    private Modo modo;
    private Deque<Ejecucion> pilaDeEjecucion;
    private boolean partidaEnJuego;
    private String ganador;

    public Partida(Modo modoPartida, String unJugador, String otroJugador, Mazo unMazo, Mazo otroMazo) throws PartidaInvalida {
        if (!modoPartida.verificarMazoValido(unMazo.cartas) || !modoPartida.verificarMazoValido(otroMazo.cartas)) {
            throw new PartidaInvalida("Los mazos de los jugadores no son compatibles");
        }
        this.modo = modoPartida;
        this.partidaEnJuego = true;
        this.ganador = null;
		this.jugadorEnTurno = unJugador;
        this.tablero1 = new Tablero(unJugador, unMazo, modoPartida);
        this.tablero2 = new Tablero(otroJugador, otroMazo, modoPartida);
        this.turno = new Turno(modoPartida, this.tablero1.puntos);
    }

    public Turno turnoEnProceso() {
        return turno;
    }

    public void iniciarPartida() {
        
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

    public void terminarEtapa() {
        this.turno.pasarDeEtapa();
        this.turno.etapaActual().iniciar(null);
    
        if (this.turno.etapaActual() == null) {
            if (jugadorEnTurno == tablero1.usuario) {
                this.turno = new Turno(this.modo, this.tablero2.puntos);
                this.jugadorEnTurno = tablero2.usuario;
            } else {
                this.turno = new Turno(this.modo, this.tablero1.puntos);
                this.jugadorEnTurno = tablero1.usuario;
            }
        }
    }

    public boolean jugadorEnTurno(String unJugador) {
        return unJugador == jugadorEnTurno;
    }
    
    private String verificarGanador(Tablero tablero) {
        if (this.partidaEnJuego = this.modo.partidaEnProceso(tablero.puntos)) {
            return tablero.usuario;
        }

        return null;
    }

    public void agregarMetodoDeCarta(MetodoCarta metodo, String jugadorObjetivo, Carta cartaObjetivo, Carta cartaActivada, Energia energia) {
        
        Ejecucion unaEjecucion = new Ejecucion(metodo, tablero1, tablero2, pilaDeEjecucion, jugadorObjetivo, cartaObjetivo, cartaActivada, energia);
        
        pilaDeEjecucion.push(unaEjecucion);
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

        pilaDeEjecucion.clear();
    }
}