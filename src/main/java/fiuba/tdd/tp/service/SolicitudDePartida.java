package fiuba.tdd.tp.service;


public class SolicitudDePartida {
    
	private String contrincante;
	private String mazo;

    public SolicitudDePartida (String contrincante, String mazo) {
        this.contrincante = contrincante;
        this.mazo = mazo;
    }

    public String contrincante() {
        return contrincante;
    }

    public String mazo() {
        return mazo;
    }

}
