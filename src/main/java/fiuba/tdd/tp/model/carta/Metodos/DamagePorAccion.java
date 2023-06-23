package fiuba.tdd.tp.model.carta.Metodos;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;

import fiuba.tdd.tp.model.Excepciones.MovimientoInvalido;
import fiuba.tdd.tp.model.carta.Carta;
import fiuba.tdd.tp.model.carta.Energia;
import fiuba.tdd.tp.model.carta.Tipo;
import fiuba.tdd.tp.model.jugador.Tablero;
import fiuba.tdd.tp.model.partida.Ejecucion;
import fiuba.tdd.tp.model.turno.Etapa;
import fiuba.tdd.tp.model.zona.Zona;

public class DamagePorAccion extends MetodoCarta {

    private int hp;

    public DamagePorAccion(int hp) {
        tipo = Tipo.Accion;
        this.hp = hp;
    }

    @Override
    public boolean esAplicableA(Etapa etapa, Zona zona, Deque<Ejecucion> pilaMetodos, ArrayList<Carta> cartasUsadas, HashMap<Energia, Integer> energiaJugador) {
        if (etapa == null || zona == null){
            return false;
        }
        return etapa.damagePorAccion() && zona.damagePorAccion();
    }

    @Override
    public void ejecutar(Tablero enJuego, Tablero contrincante, Deque<Ejecucion> pilaMetodos, 
                            String jugadorObjetivo, ArrayList<Carta> cartasObjetivo, Carta cartaActivada, Energia energia) throws MovimientoInvalido {

        if (jugadorObjetivo != null) {
            contrincante.disminuirPuntos(3);
        } else {
            for (Carta carta: cartasObjetivo) {
                carta.disminuirHP(this.hp);
                if (carta.hp == 0) {
                    carta.descartar();
                    enJuego.aumentarPuntos(1);
                    enJuego.cartasEnZona(null).get(0).cambiarZona();
                }
            }
        }
    }
}
