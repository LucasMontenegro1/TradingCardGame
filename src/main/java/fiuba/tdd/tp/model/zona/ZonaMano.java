package fiuba.tdd.tp.model.zona;

import fiuba.tdd.tp.model.Excepciones.MovimientoInvalido;

public class ZonaMano implements Zona {

    @Override
    public Zona cambiarZona() throws MovimientoInvalido {
        //return new ZonaMano();
        throw new MovimientoInvalido("");
    }

    @Override
    public Zona moverACombate() {
        return new ZonaCombate();
    }

    @Override
    public Zona moverAReserva() {
        return new ZonaReserva();
    }

    @Override
    public Zona invocar() {
        return new ZonaArtefacto();
    }    

    @Override
    public Zona descartar() {
        return new ZonaDescarte();
    }
}