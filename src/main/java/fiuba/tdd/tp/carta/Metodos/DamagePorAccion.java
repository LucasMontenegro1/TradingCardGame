package fiuba.tdd.tp.carta.Metodos;

import java.util.ArrayList;
import java.util.Deque;

import fiuba.tdd.tp.Excepciones.MovimientoInvalido;
import fiuba.tdd.tp.carta.Carta;
import fiuba.tdd.tp.carta.Energia;
import fiuba.tdd.tp.carta.Tipo;
import fiuba.tdd.tp.etapa.Etapa;
import fiuba.tdd.tp.etapa.EtapaPrincipal;
import fiuba.tdd.tp.partida.Ejecucion;
import fiuba.tdd.tp.tablero.Tablero;
import fiuba.tdd.tp.zona.Zona;
import fiuba.tdd.tp.zona.ZonaMano;

public class DamagePorAccion extends MetodoCarta {

    private int hp;

    public DamagePorAccion(int hp, ArrayList<Integer> costoDeUso){
        tipo = Tipo.Accion;
        costo = costoDeUso;
        this.hp = hp;
    }

    @Override
    public boolean esAplicableA(Etapa etapa, Zona zona, Deque<Ejecucion> pilaMetodos) {
        return etapa instanceof EtapaPrincipal && zona instanceof ZonaMano;
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
