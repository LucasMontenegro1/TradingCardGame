package fiuba.tdd.tp.zona;

import fiuba.tdd.tp.Excepciones.MovimientoInvalido;

public class ZonaCombate implements Zona {

    @Override
    public Zona cambiarZona() {
        return new ZonaReserva();
    }

    @Override
    public Zona moverACombate() throws MovimientoInvalido {
        //return new ZonaCombate();
        throw new MovimientoInvalido("");
    }

    @Override
    public Zona moverAReserva() throws MovimientoInvalido {
        //return new ZonaCombate();
        throw new MovimientoInvalido("");
    }

    @Override
    public Zona invocar() throws MovimientoInvalido {
        //return new ZonaCombate();
        throw new MovimientoInvalido("");
    }

    @Override
    public Zona descartar() {
        return new ZonaDescarte();
    }
    
}