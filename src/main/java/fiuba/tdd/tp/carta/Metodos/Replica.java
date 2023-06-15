package fiuba.tdd.tp.carta.Metodos;

import java.util.ArrayList;
import java.util.Deque;

import fiuba.tdd.tp.carta.Carta;
import fiuba.tdd.tp.carta.Energia;
import fiuba.tdd.tp.carta.Tipo;
import fiuba.tdd.tp.jugador.Tablero;
import fiuba.tdd.tp.partida.Ejecucion;
import fiuba.tdd.tp.turno.Etapa;
import fiuba.tdd.tp.zona.Zona;
import fiuba.tdd.tp.zona.ZonaDescarte;

public class Replica extends MetodoCarta {

    public Replica(ArrayList<Integer> costoDeUso) {
        tipo = Tipo.Reaccion;
        costo = costoDeUso;
    }

    @Override
    public boolean esAplicableA(Etapa etapa, Zona zona, Deque<Ejecucion> pilaMetodos, ArrayList<Carta> cartasUsadas) {
        if (pilaMetodos == null || zona instanceof ZonaDescarte || etapa.getClass() != tipo.etapa.getClass()) {
            return false;
        } 
        
        return pilaMetodos.peekLast() != null && pilaMetodos.peekLast().metodo.tipo == Tipo.Artefacto;
    }

    @Override
    public void ejecutar(Tablero enJuego, Tablero contrincante, Deque<Ejecucion> pilaMetodos, 
                            String jugadorObjetivo, ArrayList<Carta> cartasObjetivo, Carta cartaActivada, Energia energia) {
            
        Ejecucion metodo = pilaMetodos.peekLast();
        //logica para elegir
        pilaMetodos.add(metodo);
    }
    
}
