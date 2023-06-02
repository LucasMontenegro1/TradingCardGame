package fiuba.tdd.tp.turno;

import fiuba.tdd.tp.etapa.Etapa;
import fiuba.tdd.tp.etapa.EtapaInicial;
import fiuba.tdd.tp.mazo.Mazo;
import fiuba.tdd.tp.modo.Modo;

public class Turno {
    
    private Etapa etapa; 

    public Etapa etapaActual(){
        return this.etapa;
    }
    
    public Turno (Modo modo, Mazo mazo, Integer puntos){
        this.etapa = new EtapaInicial(modo,mazo,puntos);    
    }
    
    public void iniciarTurno(){
        this.etapa.iniciar();
    }

    public void pasarDeEtapa(){
        this.etapa = this.etapa.finalizar();
    }

}