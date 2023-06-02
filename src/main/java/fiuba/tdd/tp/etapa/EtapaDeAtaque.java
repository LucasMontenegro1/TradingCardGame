package fiuba.tdd.tp.etapa;

import fiuba.tdd.tp.carta.Carta;

public class EtapaDeAtaque implements Etapa {
    public enum ZonasEtapaAtaque {
        ZonaCombate
    }

    @Override
    public void iniciar() {
    }

    @Override
    public Etapa finalizar() {
        return null;
    }
    
    @Override
    public void moverCarta(Carta carta) {
    }

    @Override
    public void invocarAZonaDeCombate(Carta carta) {
        
    }

    @Override
    public void invocarAZonaDeReserva(Carta carta) {
        
    }

    @Override
    public void invocarAZonaDeArtefacto(Carta carta) {
    }
    
}
