package fiuba.tdd.tp.modo;

import fiuba.tdd.tp.carta.Carta;
import fiuba.tdd.tp.carta.Energia;
import fiuba.tdd.tp.tablero.Tablero;
import fiuba.tdd.tp.zona.ZonaMano;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;

public class Modo1 implements Modo {
    
    final Integer puntos = 20;
    final Integer maxCartasMazo = 60;
    final Integer minCartasMazo = 40;
    final Integer maxCartasRepetidas = 3;

    final Integer maxZonaReserva = 0;
    final Integer maxZonaCombate = 5;
    final Integer maxZonaArtefactos = 5;
    
    final Integer cantCartasIniciales = 5;
    
    private void cartaAlAzar(ArrayList<Carta> cartas) {
        Random random = new Random();
        Carta cartaAleatoria  = cartas.get(random.nextInt(cartas.size()));
        cartaAleatoria.zona = new ZonaMano();
    }

    @Override
    public boolean ejecutarEtapaInicial(ArrayList<Carta> cartas, Integer puntos) {
        if (cartas.size() > 0){
            cartaAlAzar(cartas);
            return true;
       }
       return false;
    }

    @Override
    public Integer getCantCartasIniciales() {
        return cantCartasIniciales;
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

        return !(cantCartas == maxCartasMazo || cantCarta == maxCartasRepetidas);
    }

    public boolean removerCarta(HashMap<String, Integer> cartas, String nombreCarta) {
        return cantidadCartas(cartas) != minCartasMazo && cartas.get(nombreCarta) != null;
    }

    @Override
    public boolean verificarMazoValido(HashMap<String, Integer> cartas) {
        
        Integer cantCartas = cantidadCartas(cartas);

        if (cantCartas > maxCartasMazo || minCartasMazo > cantCartas) {
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
