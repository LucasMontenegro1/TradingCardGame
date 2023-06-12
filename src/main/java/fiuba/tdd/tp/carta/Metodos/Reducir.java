package fiuba.tdd.tp.carta.Metodos;

import java.util.ArrayList;
import java.util.Deque;

import fiuba.tdd.tp.carta.Carta;
import fiuba.tdd.tp.carta.Energia;
import fiuba.tdd.tp.carta.Tipo;
import fiuba.tdd.tp.etapa.Etapa;
import fiuba.tdd.tp.etapa.EtapaInicial;
import fiuba.tdd.tp.tablero.Tablero;
import fiuba.tdd.tp.zona.Zona;

public class Reducir extends MetodoCarta {

    public Reducir(ArrayList<Integer> costoDeUso) {
        tipo = Tipo.Reaccion;
        costo = costoDeUso;
    }

    @Override
    public boolean esAplicableA(Etapa etapa, Zona zona, Deque<MetodoCarta> pilaMetodos) {
       return zona != null && !(etapa instanceof EtapaInicial);
    }
    
    @Override
    public void ejecutar(Tablero enJuego, Tablero contrincante, Deque<MetodoCarta> pilaMetodos, 
                            String jugadorObjetivo, Carta cartaObjetivo, Carta cartaActivada, Energia energia) {
        
        cartaActivada.hp += 1;
    }
    
}
