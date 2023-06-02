package fiuba.tdd.tp.zona;

public class ZonaCombate implements Zona {

    @Override
    public Zona cambiarZona() {
        return new ZonaReserva();
    }

    @Override
    public Zona moverACombate() {
        return new ZonaCombate();
    }

    @Override
    public Zona moverAReserva() {
        return new ZonaCombate();
    }

    @Override
    public Zona invocar() {
        return new ZonaCombate();
    }

    @Override
    public Zona descartar() {
        return new ZonaDescarte();
    }
    
}