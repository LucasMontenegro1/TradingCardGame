package fiuba.tdd.tp.carta.Metodos;

import java.util.ArrayList;
import java.util.Deque;

import fiuba.tdd.tp.carta.Carta;
import fiuba.tdd.tp.carta.Energia;
import fiuba.tdd.tp.carta.Tipo;
import fiuba.tdd.tp.etapa.Etapa;
import fiuba.tdd.tp.etapa.EtapaPrincipal;
import fiuba.tdd.tp.partida.Ejecucion;
import fiuba.tdd.tp.tablero.Tablero;
import fiuba.tdd.tp.zona.Zona;
import fiuba.tdd.tp.zona.ZonaMano;

public class Destruir extends MetodoCarta {

    public Destruir(ArrayList<Integer> costoDeUso) {
        tipo = Tipo.Accion;
        costo = costoDeUso;
    }
    
    @Override
    public boolean esAplicableA(Etapa etapa, Zona zona, Deque<MetodoCarta> pilaMetodos) {
        return zona instanceof ZonaMano && etapa instanceof EtapaPrincipal;
    }
    
    @Override
    public void ejecutar(Tablero enJuego, Tablero contrincante, Deque<Ejecucion> pilaMetodos, 
                            String jugadorObjetivo, ArrayList<Carta> cartasObjetivo, Carta cartaActivada, Energia energia) {
        
        for (Carta carta : cartasObjetivo) {
            carta.descartar();
        }
    }
}
