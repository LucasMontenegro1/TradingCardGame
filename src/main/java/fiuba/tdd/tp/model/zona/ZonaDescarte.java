package fiuba.tdd.tp.model.zona;

import fiuba.tdd.tp.model.Excepciones.MovimientoInvalido;

public class ZonaDescarte implements Zona {

    @Override
    public Zona cambiarZona() throws MovimientoInvalido {
        // return new ZonaDescarte();
        throw new MovimientoInvalido("");
    }

    @Override
    public Zona moverACombate() throws MovimientoInvalido {
        // return new ZonaDescarte();
        throw new MovimientoInvalido("");
    }

    @Override
    public Zona moverAReserva() throws MovimientoInvalido {
        // return new ZonaDescarte();
        throw new MovimientoInvalido("");
    }

    @Override
    public Zona invocar() throws MovimientoInvalido {
        // return new ZonaDescarte();
        throw new MovimientoInvalido("");
    }

    @Override
    public Zona descartar() {
        return new ZonaDescarte();
    }
    
}