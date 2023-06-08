package fiuba.tdd.tp.tablero;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import fiuba.tdd.tp.carta.Carta;
import fiuba.tdd.tp.carta.CartasDisponibles;
import fiuba.tdd.tp.carta.MetodoCarta;
import fiuba.tdd.tp.etapa.Etapa;
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
            Integer cantidadCartas = carta.getValue();

            for (int i = 0; i < cantidadCartas; i++) {
                this.cartas.add(new Carta(CartasDisponibles.valueOf(nombreCarta)));
            }
        }
    }

    public void aumentarPuntosDeVida(Integer cantidad){
        this.puntos += cantidad;
    }

    public void disminuyePuntos(Integer cantidad) {
        this.puntos -= cantidad;
    }

    public HashMap<String, ArrayList<MetodoCarta>> cartasUsables(Etapa etapa) {
        
        HashMap<String, ArrayList<MetodoCarta>> cartasUsables = new HashMap<>();

        this.cartas.forEach(carta -> {
            carta.efectos.forEach(efecto -> {
                if (efecto.esAplicableA(etapa, carta.zona)) {
                    ArrayList<MetodoCarta> efectosCarta = cartasUsables.get(carta.getNombre());
                    if (efectosCarta == null) {
                        efectosCarta = new ArrayList<>();
                    }
                        
                    efectosCarta.add(efecto);
                    cartasUsables.put(carta.getNombre(), efectosCarta);
                }
            });
        });

        return cartasUsables;
    }
}
