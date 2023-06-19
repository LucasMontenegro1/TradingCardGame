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
import fiuba.tdd.tp.model.zona.ZonaCombate;
import fiuba.tdd.tp.model.zona.ZonaDescarte;
import fiuba.tdd.tp.model.zona.ZonaMano;

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
        if (this.tipo == Tipo.Criatura) {
            return tipo.etapa.getClass() == etapa.getClass() && zona instanceof ZonaCombate;
        }
        return tipo == Tipo.Artefacto && !(zona instanceof ZonaDescarte || zona instanceof ZonaMano) && etapa instanceof EtapaPrincipal;
    }

    @Override
    public void ejecutar(Tablero enJuego, Tablero contrincante, Deque<Ejecucion> pilaMetodos, 
                            String jugadorObjetivo, ArrayList<Carta> cartasObjetivo, Carta cartaActivada, Energia energia) {
        enJuego.aumentarEnergia(this.tipoEnergia, this.cantidad);
    }

}