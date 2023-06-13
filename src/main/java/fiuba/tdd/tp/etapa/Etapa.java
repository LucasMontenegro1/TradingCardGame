package fiuba.tdd.tp.etapa;

import java.util.ArrayList;

import fiuba.tdd.tp.carta.Carta;

public interface Etapa {
    void iniciar(ArrayList<Carta> cartas);

    Etapa finalizar();
    
    void invocarAZonaDeCombate(Carta carta);

    void invocarAZonaDeReserva(Carta carta);

    void invocarAZonaDeArtefacto(Carta carta);

    void moverCarta(Carta carta);
    
}
