package fiuba.tdd.tp.model.zona;

import fiuba.tdd.tp.model.Excepciones.MovimientoInvalido;
import fiuba.tdd.tp.model.carta.Tipo;

public class ZonaMano implements Zona {

    @Override
    public Zona cambiarZona() throws MovimientoInvalido {
        throw new MovimientoInvalido("");
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

    @Override
    public boolean ataque() {
        return false;
    }

    @Override
    public boolean aumentarEnergia(Tipo tipoCarta) {
        return false;
    }

    @Override
    public boolean curar() {
        return false;
    }

    @Override
    public boolean damagePorAccion() {
        return true;
    }

    @Override
    public boolean damagePorAtributo(boolean aplicaAmbosJugadores) {
        if (aplicaAmbosJugadores){
            return true;
        }
        return false;
    }

    @Override
    public boolean descartarCarta() {
        return true;
    }

    @Override
    public boolean descartarMano() {
        return true;
    }

    @Override
    public boolean destruir() {
        return true;
    }

    @Override
    public boolean drenar() {
        return true;
    }

    @Override
    public boolean reducir() {
       return true;
    }

    @Override
    public boolean replica() {
        return false;
    }

    @Override
    public boolean resonancia() {
        return false;
    }

    @Override
    public boolean sabotear() {
        return false;
    }

    @Override
    public boolean sacrificio() {
        return true;
    }

    @Override
    public boolean tomarCarta(Tipo tipoCarta) {
        return false;
    }

    @Override
    public boolean transferirCarta() {
        return true;
    }

    @Override
    public boolean transferirEnergia() {
        return false;
    }
}