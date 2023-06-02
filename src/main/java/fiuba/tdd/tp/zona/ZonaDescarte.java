package fiuba.tdd.tp.zona;


public class ZonaDescarte implements Zona {

    @Override
    public Zona cambiarZona() {
        return new ZonaDescarte();
    }

    @Override
    public Zona moverACombate() {
        return new ZonaDescarte();
    }

    @Override
    public Zona moverAReserva() {
        return new ZonaDescarte();
    }

    @Override
    public Zona invocar() {
        return new ZonaDescarte();
    }

    @Override
    public Zona descartar() {
        return new ZonaDescarte();
    }
    
}