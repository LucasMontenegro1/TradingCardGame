package fiuba.tdd.tp.carta.Metodos;

import fiuba.tdd.tp.carta.MetodoCarta;

public class TomarCarta implements MetodoCarta {

    private int cantidad;

    public TomarCarta(int cantidad){
        this.cantidad = cantidad;
    }

    @Override
    public void ejecutar() {}
}
