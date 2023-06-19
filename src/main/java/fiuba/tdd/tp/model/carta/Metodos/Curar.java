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
import fiuba.tdd.tp.model.turno.EtapaPrincipal;
import fiuba.tdd.tp.model.zona.Zona;
import fiuba.tdd.tp.model.zona.ZonaDescarte;
import fiuba.tdd.tp.model.zona.ZonaMano;

public class Curar extends MetodoCarta {
    
    private int hp;

    public Curar(int hp) {
        tipo = Tipo.Artefacto;
        this.hp = hp;
    }

    @Override
    public boolean esAplicableA(Etapa etapa, Zona zona, Deque<Ejecucion> pilaMetodos, ArrayList<Carta> cartasUsadas, HashMap<Energia, Integer> energiaJugador) {
        return etapa instanceof EtapaPrincipal && !(zona instanceof ZonaDescarte || zona instanceof ZonaMano);
    }

    @Override
   public void ejecutar(Tablero enJuego, Tablero contrincante, Deque<Ejecucion> pilaMetodos, 
                            String jugadorObjetivo, ArrayList<Carta> cartasObjetivo, Carta cartaActivada, Energia energia) {
        
        for (Carta carta: cartasObjetivo){
            carta.aumentarHP(this.hp);
        }
    }
}
