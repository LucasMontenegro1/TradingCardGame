package fiuba.tdd.tp.modo;

import fiuba.tdd.tp.carta.Carta;
import fiuba.tdd.tp.mazo.Mazo;

import java.util.HashMap;
import java.util.List;

public class Modo2 implements Modo{
    @Override
    public Carta ejecutarEtapaInicial(Mazo mazo, Integer puntos) {
        if (puntos > 6) {
            return null;
        }
        return mazo.tomar_carta();
    }

    @Override
    public void iniciarPartida() {

    }

    @Override
    public boolean verificarMazoValido(HashMap<String, Integer> cartas){
        if (cartas.size() != 60 ){
            return false;
        }
        for (Integer cantidad : cartas.values()) {
            if (cantidad > 4) return false;
        }
        return true;
    }

}
