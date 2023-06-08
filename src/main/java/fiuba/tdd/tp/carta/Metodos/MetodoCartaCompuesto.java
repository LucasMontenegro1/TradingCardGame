package fiuba.tdd.tp.carta.Metodos;

import fiuba.tdd.tp.etapa.Etapa;
import fiuba.tdd.tp.zona.Zona;

public class MetodoCartaCompuesto implements MetodoCarta {

    private MetodoCarta metodoUno;
    private MetodoCarta metodoDos;

    public MetodoCartaCompuesto(final MetodoCarta metodoUno, final MetodoCarta metodoDos) {
        this.metodoUno = metodoUno;
        this.metodoDos = metodoDos;
    }

    @Override
    public boolean esAplicableA(Etapa etapa, Zona zona) {
        return this.metodoUno.esAplicableA(etapa, zona) && this.metodoDos.esAplicableA(etapa, zona);
    }

    @Override
    public void ejecutar() {
        this.metodoUno.ejecutar();
        this.metodoDos.ejecutar();
    }
}
