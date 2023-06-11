package fiuba.tdd.tp.zona;

public class ZonaArtefacto implements Zona {

    public static final String nombreZona = "ZonaArtefacto";

    @Override
    public Zona cambiarZona() {
        return new ZonaArtefacto();
    }
    
    @Override
    public Zona moverACombate() {
        return new ZonaArtefacto();
    }

    @Override
    public Zona moverAReserva() {
        return new ZonaArtefacto();
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