package fiuba.tdd.tp.turno;

import fiuba.tdd.tp.Excepciones.MovimientoInvalido;
import fiuba.tdd.tp.carta.Carta;
import fiuba.tdd.tp.jugador.Tablero;

public interface Etapa {
    void iniciar(Tablero tablero) throws MovimientoInvalido;

    Etapa finalizar();
    
    void invocarAZonaDeCombate(Carta carta) throws MovimientoInvalido;

    void invocarAZonaDeReserva(Carta carta) throws MovimientoInvalido;

    void invocarAZonaDeArtefacto(Carta carta) throws MovimientoInvalido;

    void moverCarta(Carta carta) throws MovimientoInvalido;

}
