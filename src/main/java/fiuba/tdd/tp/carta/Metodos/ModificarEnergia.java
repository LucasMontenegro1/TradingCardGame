package fiuba.tdd.tp.carta.Metodos;

import fiuba.tdd.tp.carta.Energia;
import fiuba.tdd.tp.carta.Tipo;
import fiuba.tdd.tp.etapa.Etapa;
import fiuba.tdd.tp.zona.Zona;
import fiuba.tdd.tp.zona.ZonaCombate;
import fiuba.tdd.tp.zona.ZonaDescarte;
import fiuba.tdd.tp.zona.ZonaMano;

public class ModificarEnergia implements MetodoCarta {

    // la cantidad indica en cuantos puntos,aumenta o
    // disminuye la cantidad de energía del jugador
    private int cantidad;
    private Energia tipoEnergia;

    private Tipo tipo;

    public ModificarEnergia(Energia tipoEnergia, int cantidad, Tipo tipo){
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.tipoEnergia = tipoEnergia;
    }

    @Override
    public void ejecutar() {}

    @Override
    public boolean esAplicableA(Etapa etapa, Zona zona) {
        if (this.tipo == Tipo.Criatura){
            return tipo.etapa.getClass() == etapa.getClass() && zona instanceof ZonaCombate;
        }
        return tipo == Tipo.Artefacto && !(zona instanceof ZonaDescarte || zona instanceof ZonaMano);
    }
}
