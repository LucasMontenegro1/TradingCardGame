package fiuba.tdd.tp.carta.Metodos;

import fiuba.tdd.tp.etapa.Etapa;
import fiuba.tdd.tp.etapa.EtapaPrincipal;
import fiuba.tdd.tp.zona.Zona;
import fiuba.tdd.tp.zona.ZonaMano;

public class Sacrificar implements MetodoCarta {
    @Override
    public void ejecutar() {

    }

    @Override
    public boolean esAplicableA(Etapa etapa, Zona zona) {
        return etapa instanceof EtapaPrincipal && zona instanceof ZonaMano;
    }
}
