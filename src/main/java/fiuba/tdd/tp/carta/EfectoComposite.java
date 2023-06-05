package fiuba.tdd.tp.carta;

import fiuba.tdd.tp.etapa.Etapa;
import fiuba.tdd.tp.zona.Zona;

import java.util.ArrayList;
import java.util.List;

public class EfectoComposite implements MetodoCarta{
    private List<MetodoCarta> efectos = new ArrayList<>();

    public void setEfectos(MetodoCarta efecto){
        efectos.add(efecto);
    }

    @Override
    public void ejecutar() {
        for(MetodoCarta efecto : this.efectos){
            efecto.ejecutar();
        }
    }

    @Override
    public boolean esAplicableA(Etapa etapa, Zona zona) {
        for (MetodoCarta efecto : this.efectos){
            if (efecto.esAplicableA(etapa,zona)){
                return true;
            }
        }
        return false;
    }
}
