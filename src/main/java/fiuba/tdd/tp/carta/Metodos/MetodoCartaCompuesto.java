package fiuba.tdd.tp.carta.Metodos;

import java.util.ArrayList;
import java.util.Deque;

import fiuba.tdd.tp.carta.Carta;
import fiuba.tdd.tp.carta.Energia;
import fiuba.tdd.tp.etapa.Etapa;
import fiuba.tdd.tp.partida.Ejecucion;
import fiuba.tdd.tp.tablero.Tablero;
import fiuba.tdd.tp.zona.Zona;

public class MetodoCartaCompuesto extends MetodoCarta {

    private MetodoCarta metodoUno;
    private MetodoCarta metodoDos;

    public MetodoCartaCompuesto(final MetodoCarta metodoUno, final MetodoCarta metodoDos) {
        this.metodoUno = metodoUno;
        this.metodoDos = metodoDos;
    }

    @Override
    public boolean esAplicableA(Etapa etapa, Zona zona, Deque<Ejecucion> pilaMetodos) {
        return this.metodoUno.esAplicableA(etapa, zona,pilaMetodos) && this.metodoDos.esAplicableA(etapa, zona,pilaMetodos);
    }

    @Override
    public void ejecutar(Tablero enJuego, Tablero contrincante, Deque<Ejecucion> pilaMetodos, 
                            String jugadorObjetivo, ArrayList<Carta> cartasObjetivo, Carta cartaActivada, Energia energia) {
                                
        this.metodoUno.ejecutar(enJuego, contrincante, pilaMetodos, jugadorObjetivo, cartasObjetivo, cartaActivada, energia);
        this.metodoDos.ejecutar(enJuego, contrincante, pilaMetodos, jugadorObjetivo, cartasObjetivo, cartaActivada, energia);
    }
}
