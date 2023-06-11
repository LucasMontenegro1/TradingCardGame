package fiuba.tdd.tp.carta.Metodos;

import java.util.Deque;

import fiuba.tdd.tp.carta.Carta;
import fiuba.tdd.tp.carta.Energia;
import fiuba.tdd.tp.carta.Tipo;
import fiuba.tdd.tp.etapa.Etapa;
import fiuba.tdd.tp.tablero.Tablero;
import fiuba.tdd.tp.zona.Zona;
import fiuba.tdd.tp.zona.ZonaDescarte;

public class Replica extends MetodoCarta {

    public Tipo tipo = Tipo.Reaccion;

    @Override
    public boolean esAplicableA(Etapa etapa, Zona zona, Deque<MetodoCarta> pilaMetodos) {
        if (zona instanceof ZonaDescarte || etapa.getClass() != tipo.etapa.getClass()) return false;
        assert pilaMetodos.peekLast() != null;
        return pilaMetodos.peekLast().tipo == Tipo.Artefacto;
    }

    @Override
    public void ejecutar(Tablero enJuego, Tablero contrincante, Deque<MetodoCarta> pilaMetodos, String jugadorObjetivo,
            Carta carta, Energia energia) {
            MetodoCarta metodo = pilaMetodos.peekLast();
            //logica para elegir
            pilaMetodos.add(metodo);
    }
    
}
