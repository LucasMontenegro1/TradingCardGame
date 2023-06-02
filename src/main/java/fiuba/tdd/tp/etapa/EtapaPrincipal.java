package fiuba.tdd.tp.etapa;

import fiuba.tdd.tp.carta.Carta;

public class EtapaPrincipal implements Etapa{

    public enum ZonasEtapaPrincipal {
        ZonaMano,
        ZonaArtefecto,
        ZonaCombate,
        ZonaReserva
    }

    @Override
    public void iniciar() {
    }
    
    @Override
    public void moverCarta(Carta carta){
        carta.cambiarZona();
    }

    @Override
    public Etapa finalizar() {
        return null;
    }

    @Override
    public void invocarAZonaDeCombate(Carta carta) {
        carta.moverACombate();
    }

    @Override
    public void invocarAZonaDeReserva(Carta carta) {
        carta.moverAReserva();
    }

    @Override
    public void invocarAZonaDeArtefacto(Carta carta) {
       carta.zona = carta.zona.invocar();
    }
    
}
