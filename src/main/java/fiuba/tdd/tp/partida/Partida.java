package fiuba.tdd.tp.partida;

import fiuba.tdd.tp.Excepciones.PartidaInvalida;
import fiuba.tdd.tp.mazo.Mazo;
import fiuba.tdd.tp.modo.Modo;
import fiuba.tdd.tp.tablero.Tablero;
import fiuba.tdd.tp.turno.Turno;

public class Partida {

    private String jugadorEnTurno;
    private Tablero tablero1;
    private Tablero tablero2;
    private Turno turno;
    private Modo modo;

    public Partida(Modo modoPartida, String unJugador, String otroJugador, Mazo unMazo, Mazo otroMazo) throws PartidaInvalida {
        if (!modoPartida.verificarMazoValido(unMazo.cartas) || !modoPartida.verificarMazoValido(otroMazo.cartas)) {
            throw new PartidaInvalida("Los mazos de los jugadores no son compatibles");
        }
        this.modo = modoPartida;
		this.jugadorEnTurno = unJugador;
        this.tablero1 = new Tablero(unJugador, unMazo);
        this.tablero2 = new Tablero(otroJugador, otroMazo);
        this.turno = new Turno(null, null, null);
    }

    
}
