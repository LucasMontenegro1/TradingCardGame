package fiuba.tdd.tp.zona;

import fiuba.tdd.tp.Excepciones.MovimientoInvalido;

public class ZonaArtefacto implements Zona {

    public static final String nombreZona = "ZonaArtefacto";

    @Override
    public Zona cambiarZona() throws MovimientoInvalido {
        throw new MovimientoInvalido("");
    }
    
    @Override
    public Zona moverACombate() throws MovimientoInvalido {
        throw new MovimientoInvalido("");
    }

    @Override
    public Zona moverAReserva() throws MovimientoInvalido {
        throw new MovimientoInvalido("");
    }

    @Override
    public Zona invocar() throws MovimientoInvalido {
        throw new MovimientoInvalido("");
    }

    @Override
    public Zona descartar() {
        return new ZonaDescarte();
    }

}