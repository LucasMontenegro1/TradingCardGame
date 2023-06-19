package fiuba.tdd.tp.model.partida;

import java.util.ArrayList;
import java.util.Deque;

import fiuba.tdd.tp.model.Excepciones.MovimientoInvalido;
import fiuba.tdd.tp.model.carta.Carta;
import fiuba.tdd.tp.model.carta.Energia;
import fiuba.tdd.tp.model.carta.Metodos.MetodoCarta;
import fiuba.tdd.tp.model.jugador.Tablero;

public class Ejecucion {

    public MetodoCarta metodo;
    private Tablero tableroEnJuego;
    private Tablero tableroContrincante;
    private Deque<Ejecucion> pilaDeEjecucion;
    private String jugadorObjetivo;
    public ArrayList<Carta> cartasObjetivo;
    private Carta cartaActivada;    
    private Energia energia;
    
    public Ejecucion (MetodoCarta metodo, Tablero tableroEnJuego, Tablero tableroContrincante, Deque<Ejecucion> pilaDeEjecucion, 
                        String jugadorObjetivo, ArrayList<Carta> cartasObjetivo, Carta cartaActivada, Energia energia) {
        this.metodo = metodo;
        this.tableroEnJuego = tableroEnJuego;
        this.tableroContrincante = tableroContrincante;
        this.pilaDeEjecucion = pilaDeEjecucion;
        this.jugadorObjetivo = jugadorObjetivo;
        this.cartasObjetivo = cartasObjetivo;
        this.cartaActivada = cartaActivada;
        this.energia = energia;
    }

    public void ejecutar() throws MovimientoInvalido {
        this.metodo.ejecutar(tableroEnJuego, tableroContrincante, pilaDeEjecucion, jugadorObjetivo,
                                cartasObjetivo, cartaActivada, energia);
    }
}
