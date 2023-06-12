package fiuba.tdd.tp.carta.Metodos;

import java.util.ArrayList;
import java.util.Deque;

import fiuba.tdd.tp.carta.Carta;
import fiuba.tdd.tp.carta.Energia;
import fiuba.tdd.tp.carta.Tipo;
import fiuba.tdd.tp.etapa.Etapa;
import fiuba.tdd.tp.tablero.Tablero;
import fiuba.tdd.tp.zona.Zona;
import fiuba.tdd.tp.zona.ZonaDescarte;

public class Resonancia extends MetodoCarta {

    public Resonancia(Tipo tipoCarta, ArrayList<Integer> costoDeUso) {
        tipo = tipoCarta;
        costo = costoDeUso;
    }

    @Override
    public boolean esAplicableA(Etapa etapa, Zona zona, Deque<MetodoCarta> pilaMetodos) {
        if (zona instanceof ZonaDescarte || etapa.getClass() != tipo.etapa.getClass()) return false;
        assert pilaMetodos.peekLast() != null;
        return pilaMetodos.peekLast() instanceof Resonancia;
    }

    @Override
    public void ejecutar(Tablero enJuego, Tablero contrincante, Deque<MetodoCarta> pilaMetodos, 
                            String jugadorObjetivo, Carta cartaObjetivo, Carta cartaActivada, Energia energia) {
        
        
    }
}
