package fiuba.tdd.tp.carta.Metodos;

import fiuba.tdd.tp.etapa.Etapa;
import fiuba.tdd.tp.zona.Zona;

public interface MetodoCarta {
    public void ejecutar();
    public boolean esAplicableA(final Etapa etapa, final Zona zona);
}
