package fiuba.tdd.tp.modo;

import java.util.ArrayList;
import java.util.HashMap;

import fiuba.tdd.tp.carta.Carta;

public interface Modo {
    public boolean ejecutarEtapaInicial(ArrayList<Carta> cartas, Integer puntos);

    public void iniciarPartida();

    public boolean verificarMazoValido(HashMap<String, Integer> cartas);

    public boolean agregarCarta(HashMap<String, Integer> cartas, String nombreCarta);

    public boolean removerCarta(HashMap<String, Integer> cartas, String nombreCarta);

    public Integer asignarPuntos();

    public Integer getMaxZonaCombate();

    public Integer getMaxZonaReserva();

    public Integer getMaxZonaArtefactos();
}