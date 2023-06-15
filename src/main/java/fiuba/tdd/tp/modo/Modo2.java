package fiuba.tdd.tp.modo;

import fiuba.tdd.tp.Excepciones.ModoSinPuntosDeVida;
import fiuba.tdd.tp.Excepciones.MovimientoInvalido;
import fiuba.tdd.tp.carta.Energia;
import fiuba.tdd.tp.jugador.Tablero;
import java.util.HashMap;
import java.util.Map.Entry;

public class Modo2 implements Modo {

    final Integer maxPuntosVictoria = 6;
    final Integer puntos = 0;
    final Integer maxCartasRepetidas = 4;
    final Integer cantCartasMazo = 60;

    final Integer maxZonaReserva = 5;
    final Integer maxZonaCombate = 1;
    final Integer maxZonaArtefactos = 3;

    final Integer cantCartasIniciales = 5;

    final Integer puntosFinDePartida = 6;

    @Override
    public boolean ejecutarEtapaInicial(Tablero tablero) throws MovimientoInvalido {
        if (tablero.puntos > maxPuntosVictoria) {
            return false;
        } else if (!tablero.cartas.isEmpty()) {
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
    public String calcularGanador(Tablero tablero1, Tablero tablero2) {
        
        if (tablero1.puntos > puntosFinDePartida) {
           return tablero1.usuario;
        } else if (tablero2.puntos > puntosFinDePartida) {
            return tablero2.usuario;
        } 
        
        return null;
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

    @Override
	public Integer obtenerPuntosDeVida(Tablero tablero) throws ModoSinPuntosDeVida {
		throw new ModoSinPuntosDeVida("");
	}

    @Override
    public Integer disminuirHPJugador(Integer cantidad) {
        return 0;
    }
}
