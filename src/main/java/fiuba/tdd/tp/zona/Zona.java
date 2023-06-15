package fiuba.tdd.tp.zona;

import fiuba.tdd.tp.Excepciones.MovimientoInvalido;

public interface Zona {

    public Zona cambiarZona() throws MovimientoInvalido;

    public Zona moverACombate() throws MovimientoInvalido;

    public Zona moverAReserva() throws MovimientoInvalido;

    public Zona invocar() throws MovimientoInvalido;

    public Zona descartar();
}