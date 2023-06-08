package fiuba.tdd.tp.carta.Metodos;

import fiuba.tdd.tp.etapa.Etapa;
import fiuba.tdd.tp.zona.Zona;

public class Impedir implements MetodoCarta {
    @Override
    public void ejecutar() {

    }

    @Override
    public boolean esAplicableA(Etapa etapa, Zona zona) {
        return false;
    }
}
