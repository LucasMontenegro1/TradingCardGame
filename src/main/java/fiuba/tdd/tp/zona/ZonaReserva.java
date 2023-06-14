package fiuba.tdd.tp.zona;

import fiuba.tdd.tp.Excepciones.MovimientoInvalido;

public class ZonaReserva implements Zona {

    @Override 
    public Zona cambiarZona() {
        return new ZonaCombate();
    }

    @Override
    public Zona moverACombate() throws MovimientoInvalido {
       // return new ZonaReserva();
       throw new MovimientoInvalido("");
    }

    @Override
    public Zona moverAReserva() throws MovimientoInvalido {
        // return new ZonaReserva();
        throw new MovimientoInvalido("");
    }

    @Override
    public Zona invocar() throws MovimientoInvalido {
        // return new ZonaReserva();
        throw new MovimientoInvalido("");
    }

    @Override
    public Zona descartar() {
        return new ZonaDescarte();
    }

}