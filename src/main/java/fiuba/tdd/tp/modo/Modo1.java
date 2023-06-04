package fiuba.tdd.tp.modo;

import fiuba.tdd.tp.carta.Carta;
import fiuba.tdd.tp.carta.CartasDisponibles;
import fiuba.tdd.tp.mazo.Mazo;

import java.util.HashMap;
import java.util.Map.Entry;

public class Modo1 implements Modo {

    @Override
    public Carta ejecutarEtapaInicial(Mazo  mazo, Integer puntos) {
        // return mazo.tomarCarta();
        return new Carta(CartasDisponibles.ALQUMISTA);
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
    public boolean verificarMazoValido(HashMap<String, Integer> cartas) {
        
        Integer cantCartas = 0;
        for (Integer cantidad : cartas.values()) {
            cantCartas += cantidad;
        }

        if (cantCartas > 60 || 40 > cantCartas) {
            return false;
        }

        for (Entry<String, Integer> carta : cartas.entrySet()) {
            String nombreCarta = carta.getKey();
            Integer cantidad = carta.getValue();
            if (cantidad > 3 && nombreCarta != CartasDisponibles.ENERGIA.nombreCarta()) {
                return false;
            }
        }

        return true;
    }
}
