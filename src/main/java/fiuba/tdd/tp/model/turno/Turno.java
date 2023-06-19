package fiuba.tdd.tp.model.turno;

import fiuba.tdd.tp.model.Excepciones.MovimientoInvalido;
import fiuba.tdd.tp.model.jugador.Tablero;
import fiuba.tdd.tp.model.modo.Modo;

public class Turno {
    
    private Etapa etapa; 

    public Turno (Modo modo) {
        this.etapa = new EtapaInicial(modo);    
    }

    public Etapa etapaActual() {
        return this.etapa;
    }    
    
    public void iniciarTurno(Tablero tablero) throws MovimientoInvalido {
        this.etapa.iniciar(tablero);
    }

    public void pasarDeEtapa() {
        this.etapa = this.etapa.finalizar();
    }
}