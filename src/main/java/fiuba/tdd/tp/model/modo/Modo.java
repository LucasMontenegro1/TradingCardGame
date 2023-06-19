package fiuba.tdd.tp.model.modo;

import java.util.ArrayList;
import java.util.HashMap;

import fiuba.tdd.tp.model.Excepciones.ModoSinPuntosDeVida;
import fiuba.tdd.tp.model.Excepciones.MovimientoInvalido;
import fiuba.tdd.tp.model.carta.Carta;
import fiuba.tdd.tp.model.jugador.Tablero;

public interface Modo {
    
    public boolean ejecutarEtapaInicial(Tablero tablero) throws MovimientoInvalido;

    public void iniciarTableros(Tablero tablero1, Tablero tablero2) throws MovimientoInvalido;

    public boolean verificarMazoValido(HashMap<String, Integer> cartas);

    public boolean agregarCarta(HashMap<String, Integer> cartas, String nombreCarta);

    public boolean removerCarta(HashMap<String, Integer> cartas, String nombreCarta);

    public Integer asignarPuntos();

    public Integer getMaxZonaCombate();

    public Integer getMaxZonaReserva();

    public Integer getMaxZonaArtefactos();

	public String calcularGanador(Tablero tablero1, Tablero tablero2);

    public default void tomarCarta(Tablero tablero, Integer cantidad) throws MovimientoInvalido {
        
        ArrayList<Carta> cartasEnMazo = tablero.cartasEnZona(null);
        for (int i = 0; i < cantidad; i++) {
            if (!cartasEnMazo.isEmpty()) {
                cartasEnMazo.get(i).cambiarZona();
            }
        }

    }

    public Integer obtenerPuntosDeVida(Tablero tablero) throws ModoSinPuntosDeVida;

    public Integer disminuirHPJugador(Integer cantidad);
}