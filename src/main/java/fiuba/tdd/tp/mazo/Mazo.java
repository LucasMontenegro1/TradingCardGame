package fiuba.tdd.tp.mazo;

import java.util.HashMap;

import fiuba.tdd.tp.Excepciones.MazoInvalido;
import fiuba.tdd.tp.modo.Modo;

public class Mazo {
    private HashMap<String, Integer> cartas;
    private Modo modo;

    public Mazo(HashMap<String, Integer> cartas, Modo modo) throws MazoInvalido {
        this.modo = modo;
        if (this.modo.verificarMazoValido(cartas)) {
            System.out.println("No debería entrar acá");
            this.cartas = cartas;
        } else {
            throw new MazoInvalido("No se puede crear el mazo con las cartas seleccionadas");
        }
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

    public void eliminarCarta(String nombreCarta) throws MazoInvalido {
        if (!this.modo.removerCarta(this.cartas, nombreCarta)) {
            throw new MazoInvalido("No es posible agregar la carta");
        }

        Integer cantidad = this.cartas.get(nombreCarta) != null ? this.cartas.get(nombreCarta) : 0;
        if (cantidad == 1) {
            this.cartas.remove(nombreCarta);
        } else {
            this.cartas.put(nombreCarta, cantidad-1);
        }
    }

    /*
    public Integer tomarCarta() {
        if (cartas.size() == 0){
            return null;
        }

        Random random = new Random();
        return this.cartas.get(random.nextInt(this.cartas.size()));
    }
     */
}
