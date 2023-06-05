package fiuba.tdd.tp.jugador;

import java.util.HashMap;
import java.util.Map.Entry;

import fiuba.tdd.tp.Excepciones.CartaNoEncontrada;
import fiuba.tdd.tp.Excepciones.DineroInsuficiente;
import fiuba.tdd.tp.Excepciones.MazoExistente;
import fiuba.tdd.tp.carta.CartasDisponibles;
import fiuba.tdd.tp.mazo.Mazo;

public class Jugador {

    private String nombre;
    private String contra;
    private int cantdDinero;
    private HashMap<String, Integer> cartas = new HashMap<>();
    private HashMap<String, Mazo> mazos = new HashMap<>();


    public Jugador(String nombreJugador, String contra) {
        this.nombre = nombreJugador;
        this.contra = contra;
        this.cantdDinero = 0;
    }

    public int getCantdDinero() {
        return cantdDinero;
    }

    public HashMap<String, Integer> getCartas() {
        return cartas;
    }

    public Object getNombre() {
        return nombre;
    }

    public HashMap<String, Mazo> getMazos() {
        return this.mazos;
    }

    public void depositarDinero(int cantidad) {
        this.cantdDinero += cantidad;
    }

    public void extraerDinero(int cantidad) throws DineroInsuficiente {
        if (cantidad > this.cantdDinero) {
            throw new DineroInsuficiente("No puede extraer esa cantidad, saldo insuficiente");
        }
        this.cantdDinero -= cantidad;
    }

    public void comprarCarta(CartasDisponibles carta) throws DineroInsuficiente {
        int precioCarta = carta.precioCarta();
        String nombreCarta = carta.nombreCarta();

        if (this.cantdDinero < precioCarta) {
            throw new DineroInsuficiente("No puede realizar la compra, saldo insuficiente");
        }

        this.extraerDinero(precioCarta);
        
        if (this.cartas.containsKey(nombreCarta)) {
            int cantidad = this.cartas.get(nombreCarta);
            this.cartas.put(nombreCarta, cantidad+1);
        } else {
            this.cartas.put(nombreCarta, 1);
        }
    }

    public void eliminarCarta(CartasDisponibles carta) throws CartaNoEncontrada {

        String nombreCarta = carta.nombreCarta();

        if (!this.cartas.containsKey(nombreCarta)) {
            throw new CartaNoEncontrada("Usted no tiene la carta");
        } 

        int cantidad = this.cartas.get(nombreCarta);
        if (cantidad == 1) {
            this.cartas.remove(nombreCarta);
        } else {
            this.cartas.put(nombreCarta, cantidad-1);
        }
    }

    public void agregarMazo(String nombre, Mazo mazo) throws MazoExistente, CartaNoEncontrada {
        if (this.mazos.get(nombre) != null) {
            throw new MazoExistente("No puede agregar un mazo con ese nombre");
        }

        if (this.cartas.size() == 0) {
            throw new CartaNoEncontrada("No tiene las cartas necesarias para armar el mazo");
        }

        for (Entry<String, Integer> carta : mazo.cartas.entrySet()) {
            String nombreCarta = carta.getKey();
            Integer cantidadMazo = carta.getValue();

            Integer cantidadAcual = this.cartas.get(nombreCarta);

            if (cantidadAcual == cantidadMazo) {
                this.cartas.remove(nombreCarta);
            } else if (cantidadAcual > cantidadMazo) {
                this.cartas.put(nombreCarta, cantidadAcual-cantidadMazo);
            } else {
                throw new CartaNoEncontrada("No tiene las cartas necesarias para armar el mazo");
            }
        }


        this.mazos.put(nombre, mazo);
    }

    public void eliminarMazo(String nombre) {
        this.mazos.remove(nombre);
    }
}
