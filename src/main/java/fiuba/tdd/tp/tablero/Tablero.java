package fiuba.tdd.tp.tablero;

import java.util.ArrayList;
import java.util.Map.Entry;

import fiuba.tdd.tp.carta.Carta;
import fiuba.tdd.tp.carta.CartasDisponibles;
import fiuba.tdd.tp.mazo.Mazo;

public class Tablero {
    
    public String usuario;
    public ArrayList<Carta> cartas = new ArrayList<>();
    public Integer puntos;

    public Tablero(String nombreJugador, Mazo mazo) {
        this.usuario = nombreJugador;
        this.puntos = mazo.getModo().asignarPuntos();

        for (Entry<String, Integer> carta : mazo.cartas.entrySet()) {
            String nombreCarta = carta.getKey();
            Integer cantidadMazo = carta.getValue();

            for (int i = 0; i < cantidadMazo; i++) {
                this.cartas.add(new Carta(CartasDisponibles.valueOf(nombreCarta)));
            }
        }
    }
}
