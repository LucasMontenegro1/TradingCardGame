package fiuba.tdd.tp.carta.Metodos;

import java.util.ArrayList;
import java.util.Deque;

import fiuba.tdd.tp.carta.Carta;
import fiuba.tdd.tp.carta.Energia;
import fiuba.tdd.tp.carta.Tipo;
import fiuba.tdd.tp.jugador.Tablero;
import fiuba.tdd.tp.partida.Ejecucion;
import fiuba.tdd.tp.turno.Etapa;
import fiuba.tdd.tp.turno.EtapaInicial;
import fiuba.tdd.tp.zona.Zona;

public class Impedir extends MetodoCarta {

    public Impedir(ArrayList<Integer> costoDeUso) {
        tipo = Tipo.Reaccion;
        costo = costoDeUso;
    }
    
    @Override
    public boolean esAplicableA(Etapa etapa, Zona zona, Deque<Ejecucion> pilaMetodos) {
        
        if (pilaMetodos == null) {
            return false;
        }

        Ejecucion ultimoMetodo = pilaMetodos.peekLast();

        return ultimoMetodo.metodo.tipo == Tipo.Reaccion && zona != null && !(etapa instanceof EtapaInicial);
    }

    @Override
    public void ejecutar(Tablero enJuego, Tablero contrincante, Deque<Ejecucion> pilaMetodos, 
                            String jugadorObjetivo, ArrayList<Carta> cartasObjetivo, Carta cartaActivada, Energia energia) {

        pilaMetodos.pop();
    }
}
