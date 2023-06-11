package fiuba.tdd.tp.carta.Metodos;

import java.util.ArrayList;
import java.util.Deque;

import fiuba.tdd.tp.carta.Carta;
import fiuba.tdd.tp.carta.Energia;
import fiuba.tdd.tp.carta.Tipo;
import fiuba.tdd.tp.etapa.Etapa;
import fiuba.tdd.tp.tablero.Tablero;
import fiuba.tdd.tp.zona.Zona;

public class Resonancia extends MetodoCarta {

    public Resonancia(Tipo tipoCarta) {
        tipo = tipoCarta;
    }

    @Override
    public boolean esAplicableA(Etapa etapa, Zona zona, Deque<MetodoCarta> pilaMetodos) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'esAplicableA'");
    }

    @Override
    public void ejecutar(Tablero enJuego, Tablero contrincante, Deque<MetodoCarta> pilaMetodos, String jugadorObjetivo,
            Carta carta, Energia energia) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'ejecutar'");
    }

    public void reaccion(Deque<MetodoCarta> pilaMetodos, ArrayList<Carta> criaturas) {
                
    }
}
