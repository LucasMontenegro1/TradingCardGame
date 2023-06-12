package fiuba.tdd.tp.partida;

import java.util.Deque;

import fiuba.tdd.tp.carta.Carta;
import fiuba.tdd.tp.carta.Energia;
import fiuba.tdd.tp.carta.Metodos.MetodoCarta;
import fiuba.tdd.tp.tablero.Tablero;

public class Ejecucion {

    private MetodoCarta metodo;
    private Tablero tableroEnJuego;
    private Tablero tableroContrincante;
    private Deque<Ejecucion> pilaDeEjecucion;
    private String jugadorObjetivo;
    private Carta cartaObjetivo;
    private Carta cartaActivada;    
    private Energia energia;
    
    public Ejecucion (MetodoCarta metodo, Tablero tableroEnJuego, Tablero tableroContrincante, Deque<Ejecucion> pilaDeEjecucion, 
                        String jugadorObjetivo, Carta cartaObjetivo, Carta cartaActivada, Energia energia) {
        this.metodo = metodo;
        this.tableroEnJuego = tableroEnJuego;
        this.tableroContrincante = tableroContrincante;
        this.pilaDeEjecucion = pilaDeEjecucion;
        this.jugadorObjetivo = jugadorObjetivo;
        this.cartaObjetivo = cartaObjetivo;
        this.cartaActivada = cartaActivada;
        this.energia = energia;
    }

    public void ejecutar() {
        this.metodo.ejecutar(tableroEnJuego, tableroContrincante, pilaDeEjecucion, jugadorObjetivo,
                                cartaObjetivo, cartaActivada, energia);
    }
}
