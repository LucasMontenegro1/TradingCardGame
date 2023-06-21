package fiuba.tdd.tp.model.zona;

import fiuba.tdd.tp.model.Excepciones.MovimientoInvalido;
import fiuba.tdd.tp.model.carta.Tipo;

public class ZonaDescarte implements Zona {

    @Override
    public Zona cambiarZona() throws MovimientoInvalido {
        throw new MovimientoInvalido("");
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
        return false;
    }

    @Override
    public boolean curar() {
        return false;
    }

    @Override
    public boolean damagePorAccion() {
        return false;
    }

    @Override
    public boolean damagePorAtributo(boolean aplicaAmbosJugadores) {
        return false;
    }

    @Override
    public boolean descartarCarta() {
        return false;
    }

    @Override
    public boolean descartarMano() {
        return false;
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
        return true;
    }
    
    @Override
    public boolean resonancia() {
        return true;
    }

    @Override
    public boolean sabotear() {
        return true;
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
       return false;
    }
    
}