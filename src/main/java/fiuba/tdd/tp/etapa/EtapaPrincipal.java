package fiuba.tdd.tp.etapa;

import java.util.ArrayList;

import fiuba.tdd.tp.Excepciones.MovimientoInvalido;
import fiuba.tdd.tp.carta.Carta;

public class EtapaPrincipal implements Etapa{

    public ArrayList<Carta> cartasJugador;

    @Override
    public void iniciar(ArrayList<Carta> cartas) {
        this.cartasJugador = cartas;
    }
    
    @Override
    public void moverCarta(Carta carta) throws MovimientoInvalido{
        carta.cambiarZona();
    }

    @Override
    public Etapa finalizar() {
        return new EtapaDeAtaque();
    }

    @Override
    public void invocarAZonaDeCombate(Carta carta) throws MovimientoInvalido {
        carta.moverACombate();
    }

    @Override
    public void invocarAZonaDeReserva(Carta carta) throws MovimientoInvalido {
        carta.moverAReserva();
    }

    @Override
    public void invocarAZonaDeArtefacto(Carta carta) throws MovimientoInvalido {
       carta.moverAArtefacto();
    }
}
