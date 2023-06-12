package fiuba.tdd.tp.carta.Metodos;

import java.util.Deque;

import fiuba.tdd.tp.carta.Carta;
import fiuba.tdd.tp.carta.Energia;
import fiuba.tdd.tp.carta.Tipo;
import fiuba.tdd.tp.etapa.Etapa;
import fiuba.tdd.tp.etapa.EtapaFinal;
import fiuba.tdd.tp.etapa.EtapaInicial;
import fiuba.tdd.tp.tablero.Tablero;
import fiuba.tdd.tp.zona.Zona;
import fiuba.tdd.tp.zona.ZonaDescarte;
import fiuba.tdd.tp.zona.ZonaMano;

public class DescartarDeMano extends MetodoCarta {

    public DescartarDeMano() {
        tipo = Tipo.Reaccion;
    }

    @Override
    public boolean esAplicableA(Etapa etapa, Zona zona, Deque<MetodoCarta> pilaMetodos) {
        return !(zona instanceof ZonaDescarte) && !(etapa instanceof EtapaInicial || etapa instanceof EtapaFinal);
    }
    
    @Override
    public void ejecutar(Tablero enJuego, Tablero contrincante, Deque<MetodoCarta> pilaMetodos, 
                            String jugadorObjetivo, Carta cartaObjetivo, Carta cartaActivada, Energia energia) {
        
        if (cartaActivada.zona instanceof ZonaMano) {
            cartaActivada.descartar();
        }
    }
}
