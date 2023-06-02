package fiuba.tdd.tp.etapa;

import fiuba.tdd.tp.carta.Carta;
import fiuba.tdd.tp.mazo.Mazo;
import fiuba.tdd.tp.modo.Modo;

public class EtapaInicial implements Etapa {

    private Modo modoPartida;
    private Mazo mazoJugador;
    private Integer puntosPartida;
    private Carta cartaAsignada;

    public EtapaInicial(Modo modo, Mazo mazo, Integer puntos){
        this.modoPartida = modo;
        this.mazoJugador = mazo;
        this.puntosPartida = puntos;
    }

    @Override
    public void iniciar() {
        this.cartaAsignada = this.modoPartida.ejecutarEtapaInicial(mazoJugador, puntosPartida);
    }

    @Override
    public Etapa finalizar() {
        return null;
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
