package fiuba.tdd.tp.etapa;

import java.util.ArrayList;

import fiuba.tdd.tp.carta.Carta;

public class EtapaPrincipal implements Etapa{

    public ArrayList<Carta> cartasJugador;

    @Override
    public void iniciar(ArrayList<Carta> cartas) {
        this.cartasJugador = cartas;
    }
    
    @Override
    public void moverCarta(Carta carta){
        carta.cambiarZona();
    }

    @Override
    public Etapa finalizar() {
        return new EtapaDeAtaque();
    }

    @Override
    public void invocarAZonaDeCombate(Carta carta) {
        carta.moverACombate();
    }

    @Override
    public void invocarAZonaDeReserva(Carta carta) {
        carta.moverAReserva();
    }

    @Override
    public void invocarAZonaDeArtefacto(Carta carta) {
       carta.zona = carta.zona.invocar();
    }
    
}
