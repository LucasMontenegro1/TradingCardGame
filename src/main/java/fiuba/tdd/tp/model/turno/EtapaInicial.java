package fiuba.tdd.tp.model.turno;

import fiuba.tdd.tp.model.Excepciones.MovimientoInvalido;
import fiuba.tdd.tp.model.carta.Carta;
import fiuba.tdd.tp.model.carta.Tipo;
import fiuba.tdd.tp.model.jugador.Tablero;
import fiuba.tdd.tp.model.modo.Modo;

public class EtapaInicial implements Etapa {

    private Modo modoPartida;
    private boolean asiginacion;

    public EtapaInicial(Modo modo) {
        this.modoPartida = modo;
    }

    @Override
    public void iniciar(Tablero tablero) throws MovimientoInvalido {
        this.asiginacion = this.modoPartida.ejecutarEtapaInicial(tablero);
    }

    @Override
    public Etapa finalizar() {

        if (!this.asiginacion){
            return null;
        }
        return new EtapaPrincipal();
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
    public boolean damagePorAtributo() {
        return false;
    }

    @Override
    public boolean descartar() {
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
    public boolean impedir() {
        return false;
    }

    @Override
    public boolean reducir() {
        return false;
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
