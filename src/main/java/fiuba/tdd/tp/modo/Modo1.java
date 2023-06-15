package fiuba.tdd.tp.modo;

import fiuba.tdd.tp.Excepciones.MovimientoInvalido;
import fiuba.tdd.tp.carta.Energia;
import fiuba.tdd.tp.jugador.Tablero;
import java.util.HashMap;
import java.util.Map.Entry;

public class Modo1 implements Modo {
    
    final Integer puntos = 20;
    final Integer maxCartasMazo = 60;
    final Integer minCartasMazo = 40;
    final Integer maxCartasRepetidas = 3;

    final Integer maxZonaReserva = 0;
    final Integer maxZonaCombate = 5;
    final Integer maxZonaArtefactos = 5;
    
    final Integer cantCartasIniciales = 5;

    final Integer puntosFinDePartida = 0;

    @Override
    public boolean ejecutarEtapaInicial(Tablero tablero) throws MovimientoInvalido {
        if (tablero.jugadorAtacadoConDrenar) {
            tablero.disminuirEnergia(tablero.obtenerEnergiaMaxima(), 1);
        }
        
        if (!tablero.cartas.isEmpty()) {
            tomarCarta(tablero, 1);
            return true;
       }

       return false;
    }

    @Override
    public void iniciarTableros(Tablero tablero1, Tablero tablero2) throws MovimientoInvalido {
        tomarCarta(tablero1, cantCartasIniciales);
        tomarCarta(tablero2, cantCartasIniciales);
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
    public String calcularGanador(Tablero tablero1, Tablero tablero2) {
        
        if (tablero1.puntos <= puntosFinDePartida) {
           return tablero2.usuario;
        } else if (tablero2.puntos <= puntosFinDePartida) {
            return tablero1.usuario;
        } 
        
        return null;
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

	@Override
	public Integer obtenerPuntosDeVida(Tablero tablero) {
		return tablero.puntos;
	}

    @Override
    public Integer disminuirHPJugador(Integer cantidad) {
        return cantidad;
    }
}
