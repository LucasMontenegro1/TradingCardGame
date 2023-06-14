package fiuba.tdd.tp.zona;

public class ZonaMano implements Zona {

    @Override
    public Zona cambiarZona() {
        return new ZonaMano();
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