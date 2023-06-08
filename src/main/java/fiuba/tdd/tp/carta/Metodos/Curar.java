package fiuba.tdd.tp.carta.Metodos;

import fiuba.tdd.tp.etapa.Etapa;
import fiuba.tdd.tp.etapa.EtapaPrincipal;
import fiuba.tdd.tp.zona.Zona;
import fiuba.tdd.tp.zona.ZonaDescarte;
import fiuba.tdd.tp.zona.ZonaMano;

public class Curar implements MetodoCarta {
    private int hp;
    private int max;

    public Curar(int hp, int max){
        this.hp = hp;
        this.max = max;
    }

    @Override
    public void ejecutar() {

    }

    @Override
    public boolean esAplicableA(Etapa etapa, Zona zona) {
        return etapa instanceof EtapaPrincipal && !(zona instanceof ZonaDescarte || zona instanceof ZonaMano);
    }
}
