package fiuba.tdd.tp.model.zona;

import fiuba.tdd.tp.model.Excepciones.MovimientoInvalido;
import fiuba.tdd.tp.model.carta.Tipo;

public interface Zona {

    public Zona cambiarZona() throws MovimientoInvalido;

    public Zona moverACombate() throws MovimientoInvalido;

    public Zona moverAReserva() throws MovimientoInvalido;

    public Zona invocar() throws MovimientoInvalido;

    public Zona descartar();

    boolean ataque();

    boolean aumentarEnergia(Tipo tipoCarta);

    boolean curar();

    boolean damagePorAccion();

    boolean damagePorAtributo(boolean aplicaAmbosJugadores);

    boolean descartarCarta();

    boolean descartarMano();

    boolean destruir();

    boolean drenar();

    boolean reducir();

    boolean replica();

    boolean resonancia();

    boolean sabotear();

    boolean sacrificio();

    boolean tomarCarta(Tipo tipoCarta);

    boolean transferirCarta();

    boolean transferirEnergia();
}