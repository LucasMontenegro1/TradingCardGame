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
import fiuba.tdd.tp.zona.ZonaDescarte;

public class Descartar extends MetodoCarta {

    public Descartar(Tipo tipoCarta, ArrayList<Integer> costoDeUso) {
        tipo = tipoCarta;
        costo = costoDeUso;
    }

    @Override
    public boolean esAplicableA(Etapa etapa, Zona zona, Deque<Ejecucion> pilaMetodos) {
        return !(etapa instanceof EtapaInicial) && !(zona == null || zona instanceof ZonaDescarte);
    }

    @Override
    public void ejecutar(Tablero enJuego, Tablero contrincante, Deque<Ejecucion> pilaMetodos, 
                            String jugadorObjetivo, ArrayList<Carta> cartasObjetivo, Carta cartaActivada, Energia energia)  {
        
        cartaActivada.descartar();
    }
}
