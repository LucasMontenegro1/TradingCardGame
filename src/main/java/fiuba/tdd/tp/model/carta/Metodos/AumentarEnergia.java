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
import fiuba.tdd.tp.model.zona.Zona;

public class AumentarEnergia extends MetodoCarta {

    private int cantidad;
    private Energia tipoEnergia;

    public AumentarEnergia(Energia tipoEnergia, int cantidad, Tipo tipoCarta) {
        tipo = tipoCarta;
        this.cantidad = cantidad;
        this.tipoEnergia = tipoEnergia;
    }

    @Override
    public boolean esAplicableA(Etapa etapa, Zona zona, Deque<Ejecucion> pilaMetodos, ArrayList<Carta> cartasUsadas, HashMap<Energia, Integer> energiaJugador) {
        if (etapa == null || zona == null){
            return false;
        }
        return etapa.aumentarEnergia(this.tipo) && zona.aumentarEnergia(this.tipo);
    }

    @Override
    public void ejecutar(Tablero enJuego, Tablero contrincante, Deque<Ejecucion> pilaMetodos, 
                            String jugadorObjetivo, ArrayList<Carta> cartasObjetivo, Carta cartaActivada, Energia energia) {
        enJuego.aumentarEnergia(this.tipoEnergia, this.cantidad);
    }

}