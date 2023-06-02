package fiuba.tdd.tp.zona;


public class ZonaReserva implements Zona {

    @Override 
    public Zona cambiarZona() {
        return new ZonaCombate();
    }

    @Override
    public Zona moverACombate() {
       return new ZonaReserva();
    }

    @Override
    public Zona moverAReserva() {
        return new ZonaReserva();
    }

    @Override
    public Zona invocar() {
        return new ZonaReserva();
    }

    @Override
    public Zona descartar() {
        return new ZonaDescarte();
    }

}