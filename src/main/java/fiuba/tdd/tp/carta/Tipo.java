package fiuba.tdd.tp.carta;

import fiuba.tdd.tp.etapa.Etapa;
import fiuba.tdd.tp.etapa.EtapaDeAtaque;
import fiuba.tdd.tp.etapa.EtapaPrincipal;

public enum Tipo {
    Accion(new EtapaPrincipal()),
    Artefacto(new EtapaPrincipal()),
    Criatura(new EtapaDeAtaque()),
    Reaccion(new EtapaPrincipal()),

    Energia(new EtapaPrincipal());

    public Etapa etapa;
    Tipo(Etapa etapa){
        this.etapa = etapa;
    }
}
