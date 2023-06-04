package fiuba.tdd.tp.modo;

import fiuba.tdd.tp.carta.Carta;
import fiuba.tdd.tp.mazo.Mazo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Modo1 implements Modo {

    @Override
    public Carta ejecutarEtapaInicial(Mazo mazo, Integer puntos) {
        return mazo.tomar_carta();
    }

    @Override
    public void iniciarPartida() {

    }

    public boolean agregarCarta(HashMap<String, Integer> cartas, String nombreCarta) {
        int cantCarta = cartas.get(nombreCarta);
        return !(cartas.size() == 60 || cantCarta == 3);
    }

    public boolean removerCarta(int cantCartas) {
        return cantCartas != 40;
    }

    @Override
    public boolean verificarMazoValido(HashMap<String, Integer> cartas){
        if (cartas.size() > 60 && 40 > cartas.size()){
            return false;
        }
        for (Integer cantidad : cartas.values()) {
            if (cantidad > 3) return false;
        }
        return true;
    }
}
