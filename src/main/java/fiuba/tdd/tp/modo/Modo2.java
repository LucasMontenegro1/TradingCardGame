package fiuba.tdd.tp.modo;

import fiuba.tdd.tp.carta.Carta;
import fiuba.tdd.tp.carta.Energia;
import fiuba.tdd.tp.zona.ZonaMano;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;

public class Modo2 implements Modo{

    final Integer maxPuntosVictoria = 6;
    final Integer puntos = 0;
    final Integer maxCartasRepetidas = 4;
    final Integer cantCartasMazo = 60;

    final Integer maxZonaReserva = 5;
    final Integer maxZonaCombate = 1;
    final Integer maxZonaArtefactos = 3;

    final Integer cantCartasIniciales = 5;

    @Override
    public boolean ejecutarEtapaInicial(ArrayList<Carta> cartas, Integer puntos) {
        if (puntos > maxPuntosVictoria) {
            return false;
        }
        else if (cartas.size() > 0){
            Random random = new Random();
            Carta cartaAleatoria  = cartas.get(random.nextInt(cartas.size()));
            cartaAleatoria.zona = new ZonaMano();
            return true;
        }
        return false;
    }

    @Override
    public Integer getCantCartasIniciales() {
        return cantCartasIniciales;
    }

    @Override
    public boolean verificarMazoValido(HashMap<String, Integer> cartas) {
        Integer cantCartas = 0;
        for (Integer cantidad : cartas.values()) {
            cantCartas += cantidad;
        }

        if (cantCartas != cantCartasMazo) {
            return false;
        }

        for (Entry<String, Integer> carta : cartas.entrySet()) {
            String nombreCarta = carta.getKey();
            Integer cantidad = carta.getValue();
            if (cantidad > maxCartasRepetidas && !Energia.esEnergia(nombreCarta)) {
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

    @Override
    public Integer getMaxZonaCombate() {
        return maxZonaCombate;
    }

    @Override
    public Integer getMaxZonaReserva() {
        return maxZonaReserva;
    }

    @Override
    public Integer getMaxZonaArtefactos() {
        return maxZonaArtefactos;
    }

}
