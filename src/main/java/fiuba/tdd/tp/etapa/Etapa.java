package fiuba.tdd.tp.etapa;

import java.util.ArrayList;

import fiuba.tdd.tp.Excepciones.MovimientoInvalido;
import fiuba.tdd.tp.carta.Carta;

public interface Etapa {
    void iniciar(ArrayList<Carta> cartas) throws MovimientoInvalido;

    Etapa finalizar();
    
    void invocarAZonaDeCombate(Carta carta) throws MovimientoInvalido;

    void invocarAZonaDeReserva(Carta carta) throws MovimientoInvalido;

    void invocarAZonaDeArtefacto(Carta carta) throws MovimientoInvalido;

    void moverCarta(Carta carta) throws MovimientoInvalido;

}
