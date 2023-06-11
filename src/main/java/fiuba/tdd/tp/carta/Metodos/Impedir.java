package fiuba.tdd.tp.carta.Metodos;

import java.util.Deque;

import fiuba.tdd.tp.carta.Carta;
import fiuba.tdd.tp.carta.Energia;
import fiuba.tdd.tp.carta.Tipo;
import fiuba.tdd.tp.etapa.Etapa;
import fiuba.tdd.tp.etapa.EtapaInicial;
import fiuba.tdd.tp.tablero.Tablero;
import fiuba.tdd.tp.zona.Zona;

public class Impedir extends MetodoCarta {

    public Impedir() {
        tipo = Tipo.Reaccion;
    }
    
    @Override
    public boolean esAplicableA(Etapa etapa, Zona zona, Deque<MetodoCarta> pilaMetodos) {
        
        MetodoCarta ultimoMetodo = pilaMetodos.peekLast();

        return ultimoMetodo.tipo == Tipo.Reaccion && zona != null && !(etapa instanceof EtapaInicial);
    }

    @Override
    public void ejecutar(Tablero enJuego, Tablero contrincante, Deque<MetodoCarta> pilaMetodos, String jugadorObjetivo,
            Carta carta, Energia energia) {

            pilaMetodos.pop();
    }
}
