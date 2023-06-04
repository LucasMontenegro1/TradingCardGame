package fiuba.tdd.tp.carta.Metodos;

import fiuba.tdd.tp.carta.Atributo;
import fiuba.tdd.tp.carta.MetodoCarta;
import fiuba.tdd.tp.etapa.Etapa;
import fiuba.tdd.tp.etapa.EtapaPrincipal;
import fiuba.tdd.tp.zona.Zona;
import fiuba.tdd.tp.zona.ZonaDescarte;
import fiuba.tdd.tp.zona.ZonaMano;

public class DamagePorAtributo implements MetodoCarta {
    private boolean ambosJugadores;
    private int hp;
    private Atributo atributo;

    public DamagePorAtributo(int hp, boolean ambosJugadores, Atributo atributo){
        this.ambosJugadores = ambosJugadores;
        this.hp = hp;
        this.atributo = atributo;
    }

    @Override
    public void ejecutar() {

    }

    @Override
    public boolean esAplicableA(Etapa etapa, Zona zona) {
        if (ambosJugadores){
            return etapa instanceof EtapaPrincipal && zona instanceof ZonaMano;
        }
        return etapa instanceof EtapaPrincipal && !(zona instanceof ZonaDescarte || zona instanceof ZonaMano);
    }
}
