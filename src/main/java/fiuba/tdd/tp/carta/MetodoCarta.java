package fiuba.tdd.tp.carta;

import fiuba.tdd.tp.etapa.Etapa;
import fiuba.tdd.tp.zona.Zona;

public interface MetodoCarta {
    public void ejecutar();
    public boolean esAplicableA(Etapa etapa, Zona zona);
}
