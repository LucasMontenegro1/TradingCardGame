package fiuba.tdd.tp.tablero;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Deque;
import fiuba.tdd.tp.carta.Carta;
import fiuba.tdd.tp.carta.CartasDisponibles;
import fiuba.tdd.tp.carta.Energia;
import fiuba.tdd.tp.carta.Tipo;
import fiuba.tdd.tp.carta.Metodos.MetodoCarta;
import fiuba.tdd.tp.etapa.Etapa;
import fiuba.tdd.tp.mazo.Mazo;
import fiuba.tdd.tp.zona.Zona;
import java.util.ArrayDeque;
import fiuba.tdd.tp.modo.Modo;

public class Tablero {
    
    public String usuario;
    public ArrayList<Carta> cartas = new ArrayList<>();
    public Integer puntos;
    public HashMap<Energia, Integer> energia = new HashMap<Energia, Integer>();
    public Integer maxZonaCombate;
    public Integer maxZonaReserva;
    public Integer maxZonaArtefactos;
    
    final String AGUA  = "AGUA";
    final String FUEGO  = "FUEGO";
    final String PLANTA  = "PLANTA";

    public Tablero(String nombreJugador, Mazo mazo) {
        Modo modo = mazo.getModo();
        this.usuario = nombreJugador;
        this.puntos = modo.asignarPuntos();
        this.energia.put(Energia.Agua, 0);
        this.energia.put(Energia.Fuego, 0);
        this.energia.put(Energia.Planta, 0);
        this.maxZonaReserva = modo.getMaxZonaReserva();
        this.maxZonaArtefactos = modo.getMaxZonaArtefactos();
        this.maxZonaCombate = modo.getMaxZonaCombate();

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

    public HashMap<Carta, ArrayList<MetodoCarta>> cartasUsables(Etapa etapa) {
        
        HashMap<Carta, ArrayList<MetodoCarta>> cartasUsables = new HashMap<>();
        Deque<MetodoCarta> pila = new ArrayDeque<>();
        this.cartas.forEach(carta -> {
            carta.metodos.forEach(efecto -> {
                if (carta.zona != null && efecto.esAplicableA(etapa, carta.zona,pila)) {
                    ArrayList<MetodoCarta> efectosCarta = cartasUsables.get(carta);
                    if (efectosCarta == null) {
                        efectosCarta = new ArrayList<>();
                    } 
                    efectosCarta.add(efecto);
                    cartasUsables.put(carta, efectosCarta);
                }
            });
        });

        return cartasUsables;
    } 

    public ArrayList<Carta> cartasEnZona(Zona zona){
        
        ArrayList<Carta> cartas = new ArrayList<>();

        this.cartas.forEach(carta -> {
            if (carta.zona == zona){
                cartas.add(carta);
            }}
        );

        return cartas;
    }

    public ArrayList<Carta> cartasAtacables() { 

        LinkedHashMap<String, ArrayList<Carta>> cartasAtacables = new LinkedHashMap<>();
        cartasAtacables.put("ZonaCombate", new ArrayList<Carta>());
        cartasAtacables.put("ZonaReserva", new ArrayList<Carta>());
        cartasAtacables.put("ZonaArtefacto", new ArrayList<Carta>());

        for (Carta carta : this.cartas) {
            if (carta.tipos.contains(Tipo.Criatura) && carta.hp != null && carta.zona != null) {
                ArrayList<Carta> cartasZona = cartasAtacables.get(carta.zona.getClass().getSimpleName());
                cartasZona.add(carta);
            }
        }

        for (ArrayList<Carta> cartasZona : cartasAtacables.values()) {
            if (!cartasZona.isEmpty()) {
                return cartasZona;
            }
        }

        return null;
    }

    public void eliminarCarta(Carta carta) {
        this.cartas.remove(carta);
    }

    public void agregarCarta(Carta carta) { 
        this.cartas.add(carta);
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
