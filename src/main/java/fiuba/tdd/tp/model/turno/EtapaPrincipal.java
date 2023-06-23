package fiuba.tdd.tp.model.turno;

import java.util.ArrayList;

import fiuba.tdd.tp.model.Excepciones.MovimientoInvalido;
import fiuba.tdd.tp.model.carta.Carta;
import fiuba.tdd.tp.model.carta.Tipo;
import fiuba.tdd.tp.model.jugador.Tablero;

public class EtapaPrincipal implements Etapa{

    public ArrayList<Carta> cartasJugador;

    @Override
    public void iniciar(Tablero tablero) {
        this.cartasJugador = tablero.cartas;
    }
    
    @Override
    public void moverCarta(Carta carta) throws MovimientoInvalido{
        carta.cambiarZona();
    }

    @Override
    public Etapa finalizar() {
        return new EtapaDeAtaque();
    }

    @Override
    public void invocarAZonaDeCombate(Carta carta) throws MovimientoInvalido {
        carta.moverACombate();
    }

    @Override
    public void invocarAZonaDeReserva(Carta carta) throws MovimientoInvalido {
        carta.moverAReserva();
    }

    @Override
    public void invocarAZonaDeArtefacto(Carta carta) throws MovimientoInvalido {
       carta.moverAArtefacto();
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
        return true;
    }

    @Override
    public boolean damagePorAtributo() {
        return true;
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
        return true;
    }

    @Override
    public boolean drenar() {
        return true;
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
        if (tipoCarta == Tipo.Artefacto) {
            return true;
        }
        return false;
    }

    @Override
    public boolean transferirCarta() {
        return true;
    }

    @Override
    public boolean transferirEnergia() {
        return true;
    }
}
