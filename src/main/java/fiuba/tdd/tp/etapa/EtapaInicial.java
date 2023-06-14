package fiuba.tdd.tp.etapa;

import java.util.ArrayList;

import fiuba.tdd.tp.Excepciones.MovimientoInvalido;
import fiuba.tdd.tp.carta.Carta;
import fiuba.tdd.tp.modo.Modo;

public class EtapaInicial implements Etapa {

    private Modo modoPartida;
    private ArrayList<Carta> cartasJugador;
    private Integer puntosPartida;
    private boolean asginacion;

    public EtapaInicial(Modo modo, Integer puntos){
        this.modoPartida = modo;
        this.puntosPartida = puntos;
    }

    @Override
    public void iniciar(ArrayList<Carta> cartas) throws MovimientoInvalido {
        this.cartasJugador = cartas;
        this.asginacion = this.modoPartida.ejecutarEtapaInicial(cartasJugador, puntosPartida);
    }

    @Override
    public Etapa finalizar() {

        if (!this.asginacion){
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
