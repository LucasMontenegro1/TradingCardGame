package fiuba.tdd.tp.modo;

import fiuba.tdd.tp.carta.Carta;
import fiuba.tdd.tp.mazo.Mazo;

import java.util.HashMap;
import java.util.List;

public class Modo1 implements Modo {


    @Override
    public Carta ejecutarEtapaInicial(Mazo mazo, Integer puntos) {
        return mazo.tomar_carta();
    }

    @Override
    public void iniciarPartida() {

    }

    @Override
    public boolean agregarCarta(HashMap<String, Integer> cartas, Carta carta) {
        int cant_carta = cartas.get(carta.getNombre());
        if (cartas.size() == 60 || cant_carta == 3) {
            return false;
        } else {
            cartas.put(carta.getNombre(), cant_carta + 1);
            return true;
        }
    }
}
