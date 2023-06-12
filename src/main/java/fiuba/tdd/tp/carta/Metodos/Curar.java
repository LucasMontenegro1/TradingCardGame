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
import fiuba.tdd.tp.zona.ZonaDescarte;
import fiuba.tdd.tp.zona.ZonaMano;

public class Curar extends MetodoCarta {
    
    private int hp;

    public Curar(int hp, ArrayList<Integer> costoDeUso){
        tipo = Tipo.Artefacto;
        costo = costoDeUso;
        this.hp = hp;
    }

    @Override
    public boolean esAplicableA(Etapa etapa, Zona zona, Deque<MetodoCarta> pilaMetodos) {
        return etapa instanceof EtapaPrincipal && !(zona instanceof ZonaDescarte || zona instanceof ZonaMano);
    }

    @Override
   public void ejecutar(Tablero enJuego, Tablero contrincante, Deque<Ejecucion> pilaMetodos, 
                            String jugadorObjetivo, Carta cartaObjetivo, Carta cartaActivada, Energia energia) {
        
        cartaObjetivo.aumentarHP(this.hp);
    }
}
