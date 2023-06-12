package fiuba.tdd.tp.carta.Metodos;

import java.util.Deque;

import fiuba.tdd.tp.carta.Atributo;
import fiuba.tdd.tp.carta.Carta;
import fiuba.tdd.tp.carta.Energia;
import fiuba.tdd.tp.carta.Tipo;
import fiuba.tdd.tp.etapa.Etapa;
import fiuba.tdd.tp.etapa.EtapaPrincipal;
import fiuba.tdd.tp.tablero.Tablero;
import fiuba.tdd.tp.zona.Zona;
import fiuba.tdd.tp.zona.ZonaDescarte;
import fiuba.tdd.tp.zona.ZonaMano;

public class DamagePorAtributo extends MetodoCarta {

    private boolean ambosJugadores;
    private int hp;
    private Atributo atributo;

    public DamagePorAtributo(int hp, boolean ambosJugadores, Atributo atributo, Tipo tipoCarta) {
        this.ambosJugadores = ambosJugadores;
        this.hp = hp;
        this.atributo = atributo;
        tipo = tipoCarta;
    }

    @Override
    public boolean esAplicableA(Etapa etapa, Zona zona, Deque<MetodoCarta> pilaMetodos) {
        if (ambosJugadores){
            return etapa instanceof EtapaPrincipal && zona instanceof ZonaMano;
        }
        return etapa instanceof EtapaPrincipal && !(zona instanceof ZonaDescarte || zona instanceof ZonaMano);
    }

    @Override
    public void ejecutar(Tablero enJuego, Tablero contrincante, Deque<MetodoCarta> pilaMetodos, 
                            String jugadorObjetivo, Carta cartaObjetivo, Carta cartaActivada, Energia energia) {
        
        if (this.ambosJugadores) {
            disminuirHPCartas(enJuego);
        }
        disminuirHPCartas(contrincante);

    }

    private void disminuirHPCartas(Tablero unTablero) {
        for (Carta unaCarta : unTablero.cartas) {
            if (unaCarta.esTipo(this.tipo) && !(unaCarta.zona instanceof ZonaMano && unaCarta.zona instanceof ZonaDescarte)) {
                unaCarta.disminuirHP(this.hp);
            }
        }
    }
}