package fiuba.tdd.tp.modo;

import fiuba.tdd.tp.carta.Carta;
import fiuba.tdd.tp.mazo.Mazo;

import java.util.List;

public class Modo2 implements Modo{
    @Override
    public Carta ejecutarEtapaInicial(Mazo mazo, Integer puntos) {
        if (puntos == 6) {
            return null;
        }
        return mazo.tomar_carta();
    }

    @Override
    public void iniciarPartida() {

    }

    @Override
    public void agregarCarta(List<Carta> cartas, Carta carta) {

    }
}
