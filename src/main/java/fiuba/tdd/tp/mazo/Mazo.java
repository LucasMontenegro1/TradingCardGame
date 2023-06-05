package fiuba.tdd.tp.mazo;

import java.util.HashMap;

import fiuba.tdd.tp.Excepciones.MazoInvalido;
import fiuba.tdd.tp.modo.Modo;

public class Mazo {
    public HashMap<String, Integer> cartas;
    private Modo modo;

    public Mazo(HashMap<String, Integer> cartas, Modo modo) throws MazoInvalido {
        this.modo = modo;
        if (this.modo.verificarMazoValido(cartas)) {
            this.cartas = cartas;
        } else {
            throw new MazoInvalido("No se puede crear el mazo con las cartas seleccionadas");
        }
    }

    public Modo getModo() {
        return this.modo;
    }
    
    public Integer cantdCartas() {
        Integer cantCartas = 0;
        for (Integer cantidad : this.cartas.values()) {
            cantCartas += cantidad;
        }

        return cantCartas;
    }

    public void agregarCarta(String nombreCarta) throws MazoInvalido {
        if (this.modo.agregarCarta(cartas, nombreCarta)) {
            Integer cantidad = this.cartas.get(nombreCarta) != null ? this.cartas.get(nombreCarta) : 0;
            this.cartas.put(nombreCarta, cantidad+1);
        } else {
            throw new MazoInvalido("No es posible agregar la carta");
        }
    }

    private void eliminar(String nombreCarta) {
        Integer cantidad = this.cartas.get(nombreCarta) != null ? this.cartas.get(nombreCarta) : 0;
        if (cantidad == 1) {
            this.cartas.remove(nombreCarta);
        } else {
            this.cartas.put(nombreCarta, cantidad-1);
        }
    }

    public void eliminarCarta(String nombreCarta) throws MazoInvalido {
        if (!this.modo.removerCarta(this.cartas, nombreCarta)) {
            throw new MazoInvalido("No es posible agregar la carta");
        }

        eliminar(nombreCarta);
    }
}
