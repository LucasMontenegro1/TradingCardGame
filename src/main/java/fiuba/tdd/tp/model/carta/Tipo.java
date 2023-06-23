package fiuba.tdd.tp.model.carta;

import fiuba.tdd.tp.model.turno.Etapa;
import fiuba.tdd.tp.model.turno.EtapaDeAtaque;
import fiuba.tdd.tp.model.turno.EtapaPrincipal;

public enum Tipo {
    Accion(new EtapaPrincipal()),
    Artefacto(new EtapaPrincipal()),
    Criatura(new EtapaDeAtaque()),
    Reaccion(new EtapaPrincipal());

    public Etapa etapa;
    Tipo(Etapa etapa){
        this.etapa = etapa;
    }
}
