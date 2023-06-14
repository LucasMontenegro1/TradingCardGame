package fiuba.tdd.tp.carta.Metodos;

import java.util.ArrayList;
import java.util.Deque;

import fiuba.tdd.tp.carta.Carta;
import fiuba.tdd.tp.carta.Energia;
import fiuba.tdd.tp.carta.Tipo;
import fiuba.tdd.tp.etapa.Etapa;
import fiuba.tdd.tp.etapa.EtapaInicial;
import fiuba.tdd.tp.partida.Ejecucion;
import fiuba.tdd.tp.tablero.Tablero;
import fiuba.tdd.tp.zona.Zona;

public class Impedir extends MetodoCarta {

    public Impedir(ArrayList<Integer> costoDeUso) {
        tipo = Tipo.Reaccion;
        costo = costoDeUso;
    }
    
    @Override
    public boolean esAplicableA(Etapa etapa, Zona zona, Deque<MetodoCarta> pilaMetodos) {
        
        MetodoCarta ultimoMetodo = pilaMetodos.peekLast();

        return ultimoMetodo.tipo == Tipo.Reaccion && zona != null && !(etapa instanceof EtapaInicial);
    }

    @Override
    public void ejecutar(Tablero enJuego, Tablero contrincante, Deque<Ejecucion> pilaMetodos, 
                            String jugadorObjetivo, ArrayList<Carta> cartasObjetivo, Carta cartaActivada, Energia energia) {

        pilaMetodos.pop();
    }
}
