package fiuba.tdd.tp.carta.Metodos;

import fiuba.tdd.tp.carta.Energia;
import fiuba.tdd.tp.carta.MetodoCarta;

public class ModificarEnergia implements MetodoCarta {

    // la cantidad indica en cuantos puntos,aumenta o
    // disminuye la cantidad de energía del jugador
    private int cantidad;
    private Energia tipo;

    public ObtenerEnergia(Energia tipo, int cantidad){
        this.tipo = tipo;
        this.cantidad = cantidad;
    }

    @Override
    public void ejecutar() {}
}
