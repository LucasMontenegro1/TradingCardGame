package fiuba.tdd.tp.model.carta.Metodos;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;

import fiuba.tdd.tp.model.Excepciones.MovimientoInvalido;
import fiuba.tdd.tp.model.carta.Carta;
import fiuba.tdd.tp.model.carta.Energia;
import fiuba.tdd.tp.model.jugador.Tablero;
import fiuba.tdd.tp.model.partida.Ejecucion;
import fiuba.tdd.tp.model.turno.Etapa;
import fiuba.tdd.tp.model.zona.Zona;

public class MetodoCartaCompuesto extends MetodoCarta {

    private MetodoCarta metodoUno;
    private MetodoCarta metodoDos;

    public MetodoCartaCompuesto(final MetodoCarta metodoUno, final MetodoCarta metodoDos) {
        this.metodoUno = metodoUno;
        this.metodoDos = metodoDos;
    }

    @Override
    public boolean esAplicableA(Etapa etapa, Zona zona, Deque<Ejecucion> pilaMetodos, ArrayList<Carta> cartasUsadas, HashMap<Energia, Integer> energiaJugador) {
        return  this.metodoUno.esAplicableA(etapa, zona,pilaMetodos, cartasUsadas, energiaJugador) && 
                this.metodoDos.esAplicableA(etapa, zona, pilaMetodos, cartasUsadas, energiaJugador);
    }

    @Override
    public void ejecutar(Tablero enJuego, Tablero contrincante, Deque<Ejecucion> pilaMetodos, 
                            String jugadorObjetivo, ArrayList<Carta> cartasObjetivo, Carta cartaActivada, Energia energia) throws MovimientoInvalido {
                                
        this.metodoUno.ejecutar(enJuego, contrincante, pilaMetodos, jugadorObjetivo, cartasObjetivo, cartaActivada, energia);
        this.metodoDos.ejecutar(enJuego, contrincante, pilaMetodos, jugadorObjetivo, cartasObjetivo, cartaActivada, energia);
    }
}
