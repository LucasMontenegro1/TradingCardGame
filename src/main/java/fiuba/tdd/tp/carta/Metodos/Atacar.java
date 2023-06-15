package fiuba.tdd.tp.carta.Metodos;

import java.util.ArrayList;
import java.util.Deque;

import fiuba.tdd.tp.Excepciones.MovimientoInvalido;
import fiuba.tdd.tp.carta.Carta;
import fiuba.tdd.tp.carta.Energia;
import fiuba.tdd.tp.carta.Tipo;
import fiuba.tdd.tp.jugador.Tablero;
import fiuba.tdd.tp.partida.Ejecucion;
import fiuba.tdd.tp.turno.Etapa;
import fiuba.tdd.tp.turno.EtapaDeAtaque;
import fiuba.tdd.tp.zona.Zona;
import fiuba.tdd.tp.zona.ZonaCombate;

public class Atacar extends MetodoCarta {

    private int hp;
    
    public Atacar(int hp, ArrayList<Integer> costoDeUso) {
        tipo = Tipo.Criatura;
        costo = costoDeUso;
        this.hp = hp;
    }
    
    @Override
    public boolean esAplicableA(Etapa etapa, Zona zona, Deque<Ejecucion> pilaMetodos, ArrayList<Carta> cartasUsadas) {
        return etapa instanceof EtapaDeAtaque && zona instanceof ZonaCombate;
    }

    @Override
    public void ejecutar(Tablero enJuego, Tablero contrincante, Deque<Ejecucion> pilaMetodos, 
                            String jugadorObjetivo, ArrayList<Carta> cartasObjetivo, Carta cartaActivada, Energia energia) throws MovimientoInvalido {

        if (jugadorObjetivo != null) {
            contrincante.disminuirPuntos(this.hp);
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
