package fiuba.tdd.tp.modo;

import fiuba.tdd.tp.carta.Carta;
import fiuba.tdd.tp.carta.CartasDisponibles;
import fiuba.tdd.tp.carta.Energia;
import fiuba.tdd.tp.mazo.Mazo;

import java.util.HashMap;
import java.util.Map.Entry;

public class Modo2 implements Modo{

    final Integer puntos = 0;

    @Override
    public Carta ejecutarEtapaInicial(Mazo mazo, Integer puntos) {
        if (puntos > 6) {
            return null;
        }

        // return mazo.tomar_carta();
        return new Carta(CartasDisponibles.ALQUIMISTA);
    }

    @Override
    public void iniciarPartida() {

    }

    @Override
    public boolean verificarMazoValido(HashMap<String, Integer> cartas) {
        Integer cantCartas = 0;
        for (Integer cantidad : cartas.values()) {
            cantCartas += cantidad;
        }

        if (cantCartas != 60) {
            return false;
        }

        for (Entry<String, Integer> carta : cartas.entrySet()) {
            String nombreCarta = carta.getKey();
            Integer cantidad = carta.getValue();
            if (cantidad > 4 && !Energia.esEnergia(nombreCarta)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean agregarCarta(HashMap<String, Integer> cartas, String nombreCarta) {
        return false;
    }

    @Override
    public boolean removerCarta(HashMap<String, Integer> cartas, String nombreCarta) {
        return false;
    }

    @Override
    public Integer asignarPuntos() {
        return puntos;
    }

}
