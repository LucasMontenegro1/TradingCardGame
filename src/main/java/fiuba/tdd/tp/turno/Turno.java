package fiuba.tdd.tp.turno;

import java.util.ArrayList;

import fiuba.tdd.tp.Excepciones.MovimientoInvalido;
import fiuba.tdd.tp.carta.Carta;
import fiuba.tdd.tp.etapa.Etapa;
import fiuba.tdd.tp.etapa.EtapaInicial;
import fiuba.tdd.tp.modo.Modo;

public class Turno {
    
    private Etapa etapa; 

    public Etapa etapaActual(){
        return this.etapa;
    }
    
    public Turno (Modo modo, Integer puntos){
        this.etapa = new EtapaInicial(modo, puntos);    
    }
    
    public void iniciarTurno(ArrayList<Carta> cartas) throws MovimientoInvalido {
        this.etapa.iniciar(cartas);
    }

    public void pasarDeEtapa(){
        this.etapa = this.etapa.finalizar();
    }
}