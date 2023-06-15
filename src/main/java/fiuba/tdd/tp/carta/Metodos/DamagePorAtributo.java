package fiuba.tdd.tp.carta.Metodos;

import java.util.ArrayList;
import java.util.Deque;

import fiuba.tdd.tp.carta.Atributo;
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

public class DamagePorAtributo extends MetodoCarta {

    private boolean ambosJugadores;
    private int hp;
    private Atributo atributo;

    public DamagePorAtributo(int hp, boolean ambosJugadores, Atributo atributo, Tipo tipoCarta, ArrayList<Integer> costoDeUso) {
        tipo = tipoCarta;
        costo = costoDeUso;
        this.ambosJugadores = ambosJugadores;
        this.hp = hp;
        this.atributo = atributo;
    }

    @Override
    public boolean esAplicableA(Etapa etapa, Zona zona, Deque<Ejecucion> pilaMetodos) {
        if (ambosJugadores){
            return etapa instanceof EtapaPrincipal && zona instanceof ZonaMano;
        }
        return etapa instanceof EtapaPrincipal && !(zona instanceof ZonaDescarte || zona instanceof ZonaMano);
    }

    @Override
    public void ejecutar(Tablero enJuego, Tablero contrincante, Deque<Ejecucion> pilaMetodos, 
                            String jugadorObjetivo, ArrayList<Carta> cartasObjetivo, Carta cartaActivada, Energia energia) {
        
        if (this.ambosJugadores) {
            disminuirHPCartas(enJuego);
        }
        disminuirHPCartas(contrincante);

    }

    private void disminuirHPCartas(Tablero unTablero) {
        for (Carta unaCarta : unTablero.cartas) {
            if (unaCarta.esTipo(this.tipo) && (unaCarta.esAtributo(this.atributo)) && !(unaCarta.zona instanceof ZonaMano || unaCarta.zona instanceof ZonaDescarte)) {
                unaCarta.disminuirHP(this.hp);
            }
        }
    }
}