package fiuba.tdd.tp.carta.Metodos;

import fiuba.tdd.tp.carta.MetodoCarta;
import fiuba.tdd.tp.carta.Tipo;
import fiuba.tdd.tp.etapa.Etapa;
import fiuba.tdd.tp.zona.Zona;
import fiuba.tdd.tp.zona.ZonaCombate;
import fiuba.tdd.tp.zona.ZonaDescarte;
import fiuba.tdd.tp.zona.ZonaMano;

public class TomarCarta implements MetodoCarta {

    private int cantidad;
    private Tipo tipo;
    public TomarCarta(int cantidad, Tipo  tipo){
        this.cantidad = cantidad;
        this.tipo = tipo;
    }

    @Override
    public void ejecutar() {}

    @Override
    public boolean esAplicableA(Etapa etapa, Zona zona) {
        if (this.tipo == Tipo.Criatura){
            return tipo.etapa.getClass() == etapa.getClass() && zona.getClass() == ZonaCombate.class;
        }
        return tipo == Tipo.Energia && zona.getClass() != ZonaDescarte.class && zona.getClass()!= ZonaMano.class;
    }
}
