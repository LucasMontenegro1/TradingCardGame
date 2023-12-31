package fiuba.tdd.tp.model.jugador;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Deque;

import fiuba.tdd.tp.model.Excepciones.CartaNoEncontrada;
import fiuba.tdd.tp.model.Excepciones.EnergiaInsuficiente;
import fiuba.tdd.tp.model.carta.Carta;
import fiuba.tdd.tp.model.carta.CartasDisponibles;
import fiuba.tdd.tp.model.carta.Energia;
import fiuba.tdd.tp.model.carta.Tipo;
import fiuba.tdd.tp.model.carta.Metodos.MetodoCarta;
import fiuba.tdd.tp.model.modo.Modo;
import fiuba.tdd.tp.model.partida.Ejecucion;
import fiuba.tdd.tp.model.turno.Etapa;
import fiuba.tdd.tp.model.zona.ZonaDescarte;

public class Tablero {

    public String usuario;
    public ArrayList<Carta> cartas = new ArrayList<>();
    public Integer puntos;
    public HashMap<Energia, Integer> energia = new HashMap<Energia, Integer>();
    public Mazo mazoInicial;
    public ArrayList<String> mazoOrdenado;
    public boolean jugadorAtacadoConDrenar;
    public Modo modo;
    
    final String AGUA  = "AGUA";
    final String FUEGO  = "FUEGO";
    final String PLANTA  = "PLANTA";

    public Tablero(String nombreJugador, Mazo mazo, Modo modo) {
        this.usuario = nombreJugador;
        this.puntos = modo.asignarPuntos();
        this.energia.put(Energia.Agua, 0);
        this.energia.put(Energia.Fuego, 0);
        this.energia.put(Energia.Planta, 0);
        this.mazoInicial = mazo;
        this.mazoOrdenado = new ArrayList<String>();
        this.jugadorAtacadoConDrenar = false;
        this.modo = modo;
    }

    public void setMazo(Mazo mazo){
        this.mazoInicial = mazo;
    }

    public void aumentarPuntos(Integer cantidad) {
        this.puntos += cantidad;
    }

    public void disminuirPuntos(Integer cantidad) {
        this.puntos -= this.modo.disminuirHPJugador(cantidad);
    }

    public Carta buscarCarta(String nombreCarta, String zona) {
        for (Carta carta : cartas) {

            if (carta.nombreCarta().equals(nombreCarta) && (carta.zona != null) && 
                !(carta.zona instanceof ZonaDescarte) && !(carta.zona.getClass().getSimpleName().equals(zona))) {

                return carta;
            }
        }
        
        return null;
    }

    public void reordenarMazo(ArrayList<String> listaOrdenada) throws CartaNoEncontrada {

        HashMap<String, Integer> cartasOrdenInicial = new HashMap<>(mazoInicial.cartas); 
        Mazo mazoInicial = new Mazo(cartasOrdenInicial);

        for (String carta : listaOrdenada) {
            if (mazoInicial.cartas.containsKey(carta)) {
                mazoInicial.eliminarCarta(carta);
                this.mazoOrdenado.add(carta);
            } else {
                throw new CartaNoEncontrada("La lista ordenada no corresponde con el mazo");
            }
        }
    }

    public void iniciarTablero() {

        if (mazoOrdenado.isEmpty()) {
            for (Entry<String, Integer> carta : mazoInicial.cartas.entrySet()) {
                String nombreCarta = carta.getKey();
                Integer cantidadCartas = carta.getValue();

                for (int i = 0; i < cantidadCartas; i++) {
                    mazoOrdenado.add(nombreCarta);
                }
            }

            Collections.shuffle(mazoOrdenado);
        }

        for (String nombreCarta : mazoOrdenado) {
            this.cartas.add(new Carta(CartasDisponibles.valueOf(nombreCarta)));
        }
    }

    public HashMap<Carta, ArrayList<MetodoCarta>> cartasUsables(Etapa etapa, Deque<Ejecucion> pila, ArrayList<Carta> cartasUsadas) {
        
        HashMap<Carta, ArrayList<MetodoCarta>> cartasUsables = new HashMap<>();

        this.cartas.forEach(carta -> {
            carta.metodos.forEach(efecto -> {
                if (carta.zona != null && 
                    efecto.esAplicableA(etapa, carta.zona, pila, cartasUsadas, this.energia) &&
                    !cartasUsadas.contains(carta)
                ) {
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

    public ArrayList<Carta> cartasEnZona(String zona){
        
        ArrayList<Carta> cartas = new ArrayList<>();

        this.cartas.forEach(carta -> {
            if (carta.zona == null && zona == null) {
                cartas.add(carta);
            } else if (carta.zona != null && carta.zona.getClass().getSimpleName() == zona) {
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

    public boolean verificarEnergia(Carta carta) throws EnergiaInsuficiente {
        HashMap<Energia, Integer> costos = carta.getCostoDeInvocacion();

        Integer fuegoActual = this.energia.get(Energia.Fuego);
        Integer plantaActual = this.energia.get(Energia.Planta);
        Integer aguaActual = this.energia.get(Energia.Fuego);

        Integer costoFuego = costos.get(Energia.Fuego);
        Integer costoPlanta = costos.get(Energia.Planta);
        Integer costoAgua = costos.get(Energia.Fuego);

        if (fuegoActual >= costoFuego && plantaActual >= costoPlanta && aguaActual >= costoAgua) {
            return true;
        } 

        throw new EnergiaInsuficiente("No tiene energía suficiente");
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

    public int energiaFuego() {
        return this.energia.get(Energia.Fuego);
    }

    public int energiaAgua() {
        return this.energia.get(Energia.Agua);
    }

    public int energiaPlanta() {
        return this.energia.get(Energia.Planta);
    }

    public void recibirDrenar() {
        this.jugadorAtacadoConDrenar = true;
    }

    public Energia obtenerEnergiaMaxima() {

        Energia energiaMaxima = null;
        Integer maxActual = 0;

        for (Energia energiaEspcifica : this.energia.keySet()) {
            Integer cantidadEnergia = this.energia.get(energiaEspcifica);
            if (cantidadEnergia > maxActual) {
                energiaMaxima = energiaEspcifica;
                maxActual = cantidadEnergia;
            }
        }

        return energiaMaxima;
    }
}
