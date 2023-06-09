package fiuba.tdd.tp.modo;

import fiuba.tdd.tp.carta.Carta;
import fiuba.tdd.tp.carta.CartasDisponibles;
import fiuba.tdd.tp.carta.Energia;
import fiuba.tdd.tp.mazo.Mazo;

import java.util.HashMap;
import java.util.Map.Entry;

public class Modo1 implements Modo {
    
    final Integer puntos = 20;

    @Override
    public Carta ejecutarEtapaInicial(Mazo  mazo, Integer puntos) {
        // return mazo.tomarCarta();
        return new Carta(CartasDisponibles.ALQUMISTA);
    }

    @Override
    public void iniciarPartida() {

    }

    private Integer cantidadCartas(HashMap<String, Integer> cartas) {
        Integer cantCartas = 0;
        for (Integer cantidad : cartas.values()) {
            cantCartas += cantidad;
        }
        
        return cantCartas;
    }

    public boolean agregarCarta(HashMap<String, Integer> cartas, String nombreCarta) {
        Integer cantCartas = cantidadCartas(cartas);
        Integer cantCarta = cartas.get(nombreCarta) != null ? cartas.get(nombreCarta) : 0;

        return !(cantCartas == 60 || cantCarta == 3);
    }

    public boolean removerCarta(HashMap<String, Integer> cartas, String nombreCarta) {
        return cantidadCartas(cartas) != 40 && cartas.get(nombreCarta) != null;
    }

    @Override
    public boolean verificarMazoValido(HashMap<String, Integer> cartas) {
        
        Integer cantCartas = cantidadCartas(cartas);

        if (cantCartas > 60 || 40 > cantCartas) {
            return false;
        }

        for (Entry<String, Integer> carta : cartas.entrySet()) {
            String nombreCarta = carta.getKey();
            Integer cantidad = carta.getValue();
            if (cantidad > 3 && !Energia.esEnergia(nombreCarta)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public Integer asignarPuntos() {
        return puntos;
    }
}
