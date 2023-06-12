package fiuba.tdd.tp.turno;

import java.util.ArrayList;

import fiuba.tdd.tp.carta.Carta;
import fiuba.tdd.tp.etapa.Etapa;
import fiuba.tdd.tp.etapa.EtapaInicial;
import fiuba.tdd.tp.modo.Modo;

public class Turno {
    
    private Etapa etapa; 

    public Etapa etapaActual(){
        return this.etapa;
    }
    
    public Turno (Modo modo, ArrayList<Carta> cartas, Integer puntos){
        this.etapa = new EtapaInicial(modo,cartas,puntos);    
    }
    
    public void iniciarTurno(){
        this.etapa.iniciar();
    }

    public void pasarDeEtapa(){
        this.etapa = this.etapa.finalizar();
    }

}