package fiuba.tdd.tp.jugador;

import java.util.HashMap;

public class Mazo {
    
    public HashMap<String, Integer> cartas;
    private Integer cantidadCartas;

    public Mazo(HashMap<String, Integer> cartas) {
        this.cartas = cartas;
        this.cantidadCartas = 0;

        for (Integer cantidad : cartas.values()) {
            this.cantidadCartas += cantidad;
        }
    }

    public Mazo() {
        this.cartas = new HashMap<>();
        this.cantidadCartas = 0;
    }

    public Integer cantdCartas() {
        return this.cantidadCartas;
    }

    public Integer cantCartaEspecifica(String nombreCarta) {
        Integer cantidadCartaEspecifica = cartas.containsKey(nombreCarta) ? cartas.get(nombreCarta) : 0; 
        return cantidadCartaEspecifica;
    }

    public void agregarCarta(String nombreCarta, Integer nuevaCantidad) {
        Integer cantidad = this.cartas.containsKey(nombreCarta) ? this.cartas.get(nombreCarta) : 0;
        this.cartas.put(nombreCarta, cantidad+nuevaCantidad);
        this.cantidadCartas += 1;
    }

    public void eliminarCarta(String nombreCarta) {
        Integer cantidad = this.cartas.get(nombreCarta) != null ? this.cartas.get(nombreCarta) : 0;
        if (cantidad > 0) {
            if (cantidad == 1) {
                this.cartas.remove(nombreCarta);
            } else {
                this.cartas.put(nombreCarta, cantidad-1);
            }
            this.cantidadCartas -= 1;
        } 
    }
}
