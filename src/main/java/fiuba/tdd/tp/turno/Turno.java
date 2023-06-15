package fiuba.tdd.tp.turno;

import fiuba.tdd.tp.Excepciones.MovimientoInvalido;
import fiuba.tdd.tp.jugador.Tablero;
import fiuba.tdd.tp.modo.Modo;

public class Turno {
    
    private Etapa etapa; 

    public Etapa etapaActual(){
        return this.etapa;
    }
    
    public Turno (Modo modo) {
        this.etapa = new EtapaInicial(modo);    
    }
    
    public void iniciarTurno(Tablero tablero) throws MovimientoInvalido {
        this.etapa.iniciar(tablero);
    }

    public void pasarDeEtapa() {
        this.etapa = this.etapa.finalizar();
    }
}