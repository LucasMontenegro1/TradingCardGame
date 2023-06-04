package fiuba.tdd.tp.modo;

import java.util.HashMap;
import java.util.List;

import fiuba.tdd.tp.carta.Carta;
import fiuba.tdd.tp.mazo.Mazo;

public interface Modo {
    public Carta ejecutarEtapaInicial(Mazo mazo, Integer puntos);

    public void iniciarPartida();

    public boolean agregarCarta(HashMap<String, Integer> cartas, Carta carta);
}