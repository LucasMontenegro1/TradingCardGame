package fiuba.tdd.tp.zona;

public class ZonaArtefecto implements Zona {

    @Override
    public Zona cambiarZona() {
        return new ZonaArtefecto();
    }
    
    @Override
    public Zona moverACombate() {
        return new ZonaArtefecto();
    }

    @Override
    public Zona moverAReserva() {
        return new ZonaArtefecto();
    }

    @Override
    public Zona invocar() {
        return new ZonaArtefecto();
    }

    @Override
    public Zona descartar() {
        return new ZonaDescarte();
    }

}