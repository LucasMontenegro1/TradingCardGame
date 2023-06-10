package fiuba.tdd.tp.tablero;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import fiuba.tdd.tp.carta.Carta;
import fiuba.tdd.tp.carta.CartasDisponibles;
import fiuba.tdd.tp.carta.Energia;
import fiuba.tdd.tp.carta.Metodos.MetodoCarta;
import fiuba.tdd.tp.etapa.Etapa;
import fiuba.tdd.tp.mazo.Mazo;

public class Tablero {
    
    public String usuario;
    public ArrayList<Carta> cartas = new ArrayList<>();
    public Integer puntos;
    public HashMap<Energia, Integer> energia = new HashMap<Energia, Integer>();
    
    final String AGUA  = "AGUA";
    final String FUEGO  = "FUEGO";
    final String PLANTA  = "PLANTA";

    public Tablero(String nombreJugador, Mazo mazo) {
        this.usuario = nombreJugador;
        this.puntos = mazo.getModo().asignarPuntos();
        this.energia.put(Energia.Agua, 0);
        this.energia.put(Energia.Fuego, 0);
        this.energia.put(Energia.Planta, 0);

        for (Entry<String, Integer> carta : mazo.cartas.entrySet()) {
            String nombreCarta = carta.getKey();
            Integer cantidadCartas = carta.getValue();

            for (int i = 0; i < cantidadCartas; i++) {
                this.cartas.add(new Carta(CartasDisponibles.valueOf(nombreCarta)));
            }
        }
    }

    public void aumentarPuntos(Integer cantidad){
        this.puntos += cantidad;
    }

    public void disminuirPuntos(Integer cantidad) {
        this.puntos -= cantidad;
    }

    public HashMap<String, ArrayList<MetodoCarta>> cartasUsables(Etapa etapa) {
        
        HashMap<String, ArrayList<MetodoCarta>> cartasUsables = new HashMap<>();

        this.cartas.forEach(carta -> {
            carta.metodos.forEach(efecto -> {
                if (efecto.esAplicableA(etapa, carta.zona)) {
                    ArrayList<MetodoCarta> efectosCarta = cartasUsables.get(carta.getNombre());
                    if (efectosCarta == null) {
                        efectosCarta = new ArrayList<>();
                    }
                    //TODO: ver caso de tener cartas repetidas 
                    efectosCarta.add(efecto);
                    cartasUsables.put(carta.getNombre(), efectosCarta);
                }
            });
        });

        return cartasUsables;
    }

    private void modificarEnergiaEspecifica(Energia energia, Integer cantidad) {
        Integer cantidadEnergia = this.energia.get(energia);
        this.energia.put(energia, cantidadEnergia+cantidad);
    }

    public void aumentarEnergia(Energia energia, Integer cantidad) {
        modificarEnergiaEspecifica(energia, cantidad);    
    }

    public void disminuirEnergia(Energia energia, Integer cantidad) {
        if (this.energia.get(energia) > 0) {
            modificarEnergiaEspecifica(energia, -cantidad);
        }
    }

    public Integer energiaFuego() {
        return this.energia.get(Energia.Fuego);
    }

    public Integer energiaAgua() {
        return this.energia.get(Energia.Agua);
    }

    public Integer energiaPlanta() {
        return this.energia.get(Energia.Planta);
    }
}
