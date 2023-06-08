package fiuba.tdd.tp.carta.Metodos;

import fiuba.tdd.tp.carta.Atributo;
import fiuba.tdd.tp.carta.MetodoCarta;
import fiuba.tdd.tp.etapa.Etapa;
import fiuba.tdd.tp.etapa.EtapaDeAtaque;
import fiuba.tdd.tp.zona.Zona;
import fiuba.tdd.tp.zona.ZonaCombate;

public class Atacar implements MetodoCarta {

    private int hp;

    public Atacar(int hp){
        this.hp = hp;
    }

    @Override
    public void ejecutar() {
        
    }

    @Override
    public boolean esAplicableA(Etapa etapa, Zona zona) {
        return etapa instanceof EtapaDeAtaque && zona instanceof ZonaCombate;
    }
}
