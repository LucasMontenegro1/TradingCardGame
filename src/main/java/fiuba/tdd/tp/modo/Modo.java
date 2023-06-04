package fiuba.tdd.tp.modo;

import java.util.HashMap;

import fiuba.tdd.tp.carta.Carta;
import fiuba.tdd.tp.mazo.Mazo;

public interface Modo {
    public Carta ejecutarEtapaInicial(Mazo mazo, Integer puntos);

    public void iniciarPartida();

    public boolean verificarMazoValido(HashMap<String, Integer> cartas);

    public boolean agregarCarta(HashMap<String, Integer> cartas, String nombreCarta);

    public boolean removerCarta(HashMap<String, Integer> cartas, String nombreCarta);
}