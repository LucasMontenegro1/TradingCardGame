package fiuba.tdd.tp.jugador;

import java.util.ArrayList;

import fiuba.tdd.tp.Excepciones.DineroInsuficiente;
import fiuba.tdd.tp.carta.Carta;
import fiuba.tdd.tp.carta.CartasDisponibles;

public class Jugador {

    private String nombre;
    private String contra;
    private int cantdDinero;
    private ArrayList<Carta> cartas = new ArrayList<Carta>();

    public Jugador(String nombreJugador, String contra) {
        this.nombre = nombreJugador;
        this.contra = contra;
        this.cantdDinero = 0;
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

        if (this.cantdDinero < precioCarta) {
            throw new DineroInsuficiente("No puede realizar la compra, saldo insuficiente");
        }

        this.extraerDinero(precioCarta);
        // TODO: que el constructor de carta reciba una carta disponible del enum
        // Carta nuevaCarta = new Carta(carta);
        Carta nuevaCarta = new Carta(new ArrayList<>(1), carta.nombreCarta(), "descripcion", null);
        this.cartas.add(nuevaCarta);
    }

    public int getCantdDinero() {
        return cantdDinero;
    }

    public ArrayList<Carta> getCartas() {
        return cartas;
    }

    public Object getNombre() {
        return nombre;
    }
}
