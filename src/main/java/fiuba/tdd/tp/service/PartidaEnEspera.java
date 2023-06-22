package fiuba.tdd.tp.service;

import fiuba.tdd.tp.model.jugador.Mazo;
import fiuba.tdd.tp.model.modo.Modo;

public class PartidaEnEspera {
    private Modo modo;
    private String jugador;
    private String contrincante;
    private Mazo mazo;

    public PartidaEnEspera(Modo modo, String jugador, String contrincante, Mazo mazo){
        this.modo = modo;
        this.jugador = jugador;
        this.contrincante = contrincante;
        this.mazo = mazo;
    }

    public Modo getModo(){
        return this.modo;
    }

    public String getJugador(){
        return this.jugador;
    }

    public String getContrincante(){
        return this.contrincante;
    }

    public Mazo getMazo(){
        return this.mazo;
    }
}
