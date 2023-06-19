package fiuba.tdd.tp.model.carta.Metodos;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;

import fiuba.tdd.tp.model.carta.Carta;
import fiuba.tdd.tp.model.carta.Energia;
import fiuba.tdd.tp.model.carta.Tipo;
import fiuba.tdd.tp.model.jugador.Tablero;
import fiuba.tdd.tp.model.partida.Ejecucion;
import fiuba.tdd.tp.model.turno.Etapa;
import fiuba.tdd.tp.model.turno.EtapaInicial;
import fiuba.tdd.tp.model.zona.Zona;
import fiuba.tdd.tp.model.zona.ZonaDescarte;

public class Descartar extends MetodoCarta {

    public Descartar(Tipo tipoCarta) {
        tipo = tipoCarta;
    }

    @Override
    public boolean esAplicableA(Etapa etapa, Zona zona, Deque<Ejecucion> pilaMetodos, ArrayList<Carta> cartasUsadas, HashMap<Energia, Integer> energiaJugador) {
        return !(etapa instanceof EtapaInicial) && !(zona == null || zona instanceof ZonaDescarte);
    }

    @Override
    public void ejecutar(Tablero enJuego, Tablero contrincante, Deque<Ejecucion> pilaMetodos, 
                            String jugadorObjetivo, ArrayList<Carta> cartasObjetivo, Carta cartaActivada, Energia energia)  {
        
        cartaActivada.descartar();
    }
}
