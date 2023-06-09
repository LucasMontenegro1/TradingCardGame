package fiuba.tdd.tp.tablero;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import fiuba.tdd.tp.carta.Carta;
import fiuba.tdd.tp.carta.CartasDisponibles;
import fiuba.tdd.tp.carta.Metodos.MetodoCarta;
import fiuba.tdd.tp.etapa.Etapa;
import fiuba.tdd.tp.mazo.Mazo;

public class Tablero {
    
    public String usuario;
    public ArrayList<Carta> cartas = new ArrayList<>();
    public Integer puntos;
    public HashMap<String, Integer> energia = new HashMap<String, Integer>();
    
    final String AGUA  = "AGUA";
    final String FUEGO  = "FUEGO";
    final String PLANTA  = "PLANTA";

    public Tablero(String nombreJugador, Mazo mazo) {
        this.usuario = nombreJugador;
        this.puntos = mazo.getModo().asignarPuntos();
        this.energia.put(AGUA, 0);
        this.energia.put(FUEGO, 0);
        this.energia.put(PLANTA, 0);

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

    private void modificarEnergiaEspecifica(String tipo, Integer cantidad) {
        Integer energia = this.energia.get(tipo);
        this.energia.put(tipo, energia+cantidad);
    }

    public void aumentarEnergias(int agua, int fuego, int planta) {
        modificarEnergiaEspecifica(AGUA, agua);
        modificarEnergiaEspecifica(FUEGO, fuego);
        modificarEnergiaEspecifica(PLANTA, planta);    
    }

    public void disminuirEnergias(int agua, int fuego, int planta) {
        if (this.energia.get(AGUA) > 0) {
            modificarEnergiaEspecifica(AGUA, -agua);
        }
        if (this.energia.get(FUEGO) > 0) {
            modificarEnergiaEspecifica(FUEGO, -fuego);
        }
        if (this.energia.get(PLANTA) > 0) {
            modificarEnergiaEspecifica(PLANTA, -planta);    
        }
    }

    public Integer energiaFuego() {
        return this.energia.get(FUEGO);
    }

    public Integer energiaAgua() {
        return this.energia.get(AGUA);
    }

    public Integer energiaPlanta() {
        return this.energia.get(PLANTA);
    }
}
