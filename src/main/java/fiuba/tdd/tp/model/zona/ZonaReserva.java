package fiuba.tdd.tp.model.zona;

import fiuba.tdd.tp.model.Excepciones.MovimientoInvalido;
import fiuba.tdd.tp.model.carta.Tipo;

public class ZonaReserva implements Zona {

    @Override 
    public Zona cambiarZona() {
        return new ZonaCombate();
    }

    @Override
    public Zona moverACombate() throws MovimientoInvalido {
       throw new MovimientoInvalido("");
    }

    @Override
    public Zona moverAReserva() throws MovimientoInvalido {
        throw new MovimientoInvalido("");
    }

    @Override
    public Zona invocar() throws MovimientoInvalido {
        throw new MovimientoInvalido("");
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
        if (tipoCarta == Tipo.Artefacto){
            return true;
        }
        return false;
    }

    @Override
    public boolean curar() {
        return true;
    }

    @Override
    public boolean damagePorAccion() {
        return false;
    }

    @Override
    public boolean damagePorAtributo(boolean aplicaAmbosJugadores) {
        if (!aplicaAmbosJugadores){
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
       return false;
    }

    @Override
    public boolean drenar() {
        return false;
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
        return false;
    }

    @Override
    public boolean tomarCarta(Tipo tipoCarta) {
        if (tipoCarta == Tipo.Artefacto){
            return true;
        }
        return false;
    }

    @Override
    public boolean transferirCarta() {
        return false;
    }

    @Override
    public boolean transferirEnergia() {
        return true;
    }
}