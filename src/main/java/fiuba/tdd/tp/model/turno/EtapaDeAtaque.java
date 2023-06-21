package fiuba.tdd.tp.model.turno;

import java.util.ArrayList;

import fiuba.tdd.tp.model.Excepciones.MovimientoInvalido;
import fiuba.tdd.tp.model.carta.Carta;
import fiuba.tdd.tp.model.carta.Tipo;
import fiuba.tdd.tp.model.jugador.Tablero;

public class EtapaDeAtaque implements Etapa {

    public ArrayList<Carta> cartasJugador;

    @Override
    public void iniciar(Tablero tablero) {
        this.cartasJugador = tablero.cartas;
    }

    @Override
    public Etapa finalizar() {
        return null;
    }
    
    @Override
    public void moverCarta(Carta carta) throws MovimientoInvalido {
        throw new MovimientoInvalido("");
    }

    @Override
    public void invocarAZonaDeCombate(Carta carta) throws MovimientoInvalido {
        throw new MovimientoInvalido("");
    }

    @Override
    public void invocarAZonaDeReserva(Carta carta) throws MovimientoInvalido {
        throw new MovimientoInvalido("");
    }

    @Override
    public void invocarAZonaDeArtefacto(Carta carta) throws MovimientoInvalido {
        throw new MovimientoInvalido("");
    }
    
    @Override
    public boolean ataque() {
        return true;
    }

    @Override
    public boolean aumentarEnergia(Tipo tipoCarta) {
        if (tipoCarta == Tipo.Criatura){
            return true;
        }
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
    public boolean damagePorAtributo() {
        return false;
    }

    @Override
    public boolean descartar() {
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
    public boolean impedir() {
        return true;
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
    public boolean sabotear() {
        return true;
    }

    @Override
    public boolean sacrificio() {
        return false;
    }

    @Override
    public boolean tomarCarta(Tipo tipoCarta) {
        if (tipoCarta == Tipo.Criatura){    
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
