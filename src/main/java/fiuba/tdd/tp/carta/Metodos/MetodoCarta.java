package fiuba.tdd.tp.carta.Metodos;

import java.util.Deque;

import fiuba.tdd.tp.carta.Carta;
import fiuba.tdd.tp.carta.Energia;
import fiuba.tdd.tp.carta.Tipo;
import fiuba.tdd.tp.etapa.Etapa;
import fiuba.tdd.tp.tablero.Tablero;
import fiuba.tdd.tp.zona.Zona;

public abstract class  MetodoCarta {
    protected Tipo tipo;
    public abstract void ejecutar(Tablero enJuego, Tablero contrincante, Deque<MetodoCarta> pilaMetodos, 
                                    String jugadorObjetivo, Carta cartaObjetivo, Carta cartaActivada, Energia energia);
    public abstract boolean esAplicableA(final Etapa etapa, final Zona zona, Deque<MetodoCarta> pilaMetodos);
}
