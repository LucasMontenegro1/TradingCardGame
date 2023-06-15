package fiuba.tdd.tp.carta;

import fiuba.tdd.tp.turno.Etapa;
import fiuba.tdd.tp.turno.EtapaDeAtaque;
import fiuba.tdd.tp.turno.EtapaPrincipal;

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
