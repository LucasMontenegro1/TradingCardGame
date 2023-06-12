package fiuba.tdd.tp.etapa;

import java.util.ArrayList;

import fiuba.tdd.tp.carta.Carta;
import fiuba.tdd.tp.modo.Modo;

public class EtapaInicial implements Etapa {

    private Modo modoPartida;
    private ArrayList<Carta> cartasJugador;
    private Integer puntosPartida;
    private boolean asginacion;

    public EtapaInicial(Modo modo, ArrayList<Carta> cartas, Integer puntos){
        this.modoPartida = modo;
        this.cartasJugador = cartas;
        this.puntosPartida = puntos;
    }

    @Override
    public void iniciar() {
        this.asginacion = this.modoPartida.ejecutarEtapaInicial(cartasJugador, puntosPartida);
    }

    @Override
    public Etapa finalizar() {

        if (!this.asginacion){
            return new EtapaFinal();
        }
        return new EtapaPrincipal();
    }
    
    @Override
    public void moverCarta(Carta carta) {
    }

    @Override
    public void invocarAZonaDeCombate(Carta carta) {
    }

    @Override
    public void invocarAZonaDeReserva(Carta carta) {
        
    }

    @Override
    public void invocarAZonaDeArtefacto(Carta carta) {
    }
}
