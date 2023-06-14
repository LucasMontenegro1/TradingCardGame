package fiuba.tdd.tp.zona;

import fiuba.tdd.tp.Excepciones.MovimientoInvalido;

public class ZonaArtefacto implements Zona {

    public static final String nombreZona = "ZonaArtefacto";

    @Override
    public Zona cambiarZona() throws MovimientoInvalido {
        //return new ZonaArtefacto();
        throw new MovimientoInvalido("");
    }
    
    @Override
    public Zona moverACombate() throws MovimientoInvalido {
        //return new ZonaArtefacto();
        throw new MovimientoInvalido("");
    }

    @Override
    public Zona moverAReserva() throws MovimientoInvalido {
        //return new ZonaArtefacto();
        throw new MovimientoInvalido("");
    }

    @Override
    public Zona invocar() throws MovimientoInvalido {
        //return new ZonaArtefacto();
        throw new MovimientoInvalido("");
    }

    @Override
    public Zona descartar() {
        return new ZonaDescarte();
    }

}