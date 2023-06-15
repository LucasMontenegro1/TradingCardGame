package fiuba.tdd.tp.modo;

import java.util.ArrayList;
import java.util.HashMap;

import fiuba.tdd.tp.Excepciones.ModoSinPuntosDeVida;
import fiuba.tdd.tp.Excepciones.MovimientoInvalido;
import fiuba.tdd.tp.carta.Carta;
import fiuba.tdd.tp.tablero.Tablero;
import fiuba.tdd.tp.zona.Zona;

public interface Modo {
    
    public boolean ejecutarEtapaInicial(ArrayList<Carta> cartas, Integer puntos) throws MovimientoInvalido;

    public void iniciarTableros(ArrayList<Carta> cartas1, ArrayList<Carta> cartas2) throws MovimientoInvalido;

    public boolean verificarMazoValido(HashMap<String, Integer> cartas);

    public boolean agregarCarta(HashMap<String, Integer> cartas, String nombreCarta);

    public boolean removerCarta(HashMap<String, Integer> cartas, String nombreCarta);

    public Integer asignarPuntos();

    public Integer getMaxZonaCombate();

    public Integer getMaxZonaReserva();

    public Integer getMaxZonaArtefactos();

	public String calcularGanador(Tablero tablero1, Tablero tablero2);

    public default void tomarCarta(ArrayList<Carta> cartas, Integer cantidad) throws MovimientoInvalido {
        
        Zona zona = cartas.get(0).zona;

        for (int i = 0; i < cartas.size(); i++) {
            zona = cartas.get(i).zona;
            
            if (zona == null) {
                for (int j = 0; j < cantidad; j++) {
                    Carta unaCarta = cartas.get(j+i);
                    unaCarta.cambiarZona();
                }
                break;
            }
        }

    }

    public Integer obtenerPuntosDeVida(Tablero tablero) throws ModoSinPuntosDeVida;
}