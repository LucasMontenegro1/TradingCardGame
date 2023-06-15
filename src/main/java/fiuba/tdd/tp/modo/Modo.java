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

	public boolean partidaEnProceso(Integer puntos);

    public default void tomarCarta(ArrayList<Carta> cartas, Integer cantidad) throws MovimientoInvalido {
        
        Integer indiceCarta = 0;
        Zona zona = cartas.get(indiceCarta).zona;

        while (zona != null) {
            indiceCarta++;
            zona = cartas.get(indiceCarta).zona;
        }

        for (int i = 0; i < cantidad; i++) {
            Carta unaCarta = cartas.get(indiceCarta+i);
            unaCarta.cambiarZona();
        }
    }

    public Integer obtenerPuntosDeVida(Tablero tablero) throws ModoSinPuntosDeVida;
}