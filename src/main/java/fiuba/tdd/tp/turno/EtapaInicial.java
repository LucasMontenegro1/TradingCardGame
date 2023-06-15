package fiuba.tdd.tp.turno;

import fiuba.tdd.tp.Excepciones.MovimientoInvalido;
import fiuba.tdd.tp.carta.Carta;
import fiuba.tdd.tp.jugador.Tablero;
import fiuba.tdd.tp.modo.Modo;

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
}