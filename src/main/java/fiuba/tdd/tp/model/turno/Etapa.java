package fiuba.tdd.tp.model.turno;

import fiuba.tdd.tp.model.Excepciones.MovimientoInvalido;
import fiuba.tdd.tp.model.carta.Carta;
import fiuba.tdd.tp.model.carta.Tipo;
import fiuba.tdd.tp.model.jugador.Tablero;

public interface Etapa {
    void iniciar(Tablero tablero) throws MovimientoInvalido;

    Etapa finalizar();
    
    void invocarAZonaDeCombate(Carta carta) throws MovimientoInvalido;

    void invocarAZonaDeReserva(Carta carta) throws MovimientoInvalido;

    void invocarAZonaDeArtefacto(Carta carta) throws MovimientoInvalido;

    void moverCarta(Carta carta) throws MovimientoInvalido;

    boolean ataque();

    boolean aumentarEnergia(Tipo tipoCarta);

    boolean curar();

    boolean damagePorAccion();

    boolean damagePorAtributo();

    boolean descartar();

    boolean descartarMano();

    boolean destruir();

    boolean drenar();

    boolean impedir();

    boolean reducir();

    boolean replica();

    boolean sabotear();

    boolean sacrificio();

    boolean tomarCarta(Tipo tipoCarta);

    boolean transferirCarta();

    boolean transferirEnergia();
}
