package fiuba.tdd.tp.model.carta.Metodos;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;

import fiuba.tdd.tp.model.Excepciones.MovimientoInvalido;
import fiuba.tdd.tp.model.carta.Atributo;
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

public class DamagePorAtributo extends MetodoCarta {

    private boolean ambosJugadores;
    private int hp;
    private Atributo atributo;

    public DamagePorAtributo(int hp, boolean ambosJugadores, Atributo atributo, Tipo tipoCarta) {
        tipo = tipoCarta;
        this.ambosJugadores = ambosJugadores;
        this.hp = hp;
        this.atributo = atributo;
    }

    @Override
    public boolean esAplicableA(Etapa etapa, Zona zona, Deque<Ejecucion> pilaMetodos, ArrayList<Carta> cartasUsadas, HashMap<Energia, Integer> energiaJugador) {
        if (ambosJugadores){
            return etapa instanceof EtapaPrincipal && zona instanceof ZonaMano;
        }
        return etapa instanceof EtapaPrincipal && !(zona instanceof ZonaDescarte || zona instanceof ZonaMano);
    }

    @Override
    public void ejecutar(Tablero enJuego, Tablero contrincante, Deque<Ejecucion> pilaMetodos, 
                            String jugadorObjetivo, ArrayList<Carta> cartasObjetivo, Carta cartaActivada, Energia energia) throws MovimientoInvalido {
        
        if (this.ambosJugadores) {
            disminuirHPCartas(enJuego);
        }
        disminuirHPCartas(contrincante);

    }

    private void disminuirHPCartas(Tablero unTablero) throws MovimientoInvalido {
        for (Carta unaCarta : unTablero.cartas) {
            if (unaCarta.esTipo(this.tipo) && (unaCarta.esAtributo(this.atributo)) && !(unaCarta.zona instanceof ZonaMano || unaCarta.zona instanceof ZonaDescarte)) {
                unaCarta.disminuirHP(this.hp);
                if (unaCarta.hp == 0) {
                    unaCarta.descartar();
                    unTablero.aumentarPuntos(1);
                    unTablero.cartasEnZona(null).get(0).cambiarZona();
                }
            }
        }
    }
}