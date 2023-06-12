package fiuba.tdd.tp.etapa;

import java.util.ArrayList;

import fiuba.tdd.tp.carta.Carta;

public class EtapaDeAtaque implements Etapa {

    public ArrayList<Carta> cartasJugador;

    @Override
    public void iniciar(ArrayList<Carta> cartas) {
        this.cartasJugador = cartas;
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
