package fiuba.tdd.tp.service;


public class SolicitudDePartida {
    
    private Integer modoPartida;
	private String otroJugador;
	private String unMazo;
	private String otroMazo;

    public SolicitudDePartida (Integer modoPartida, String otroJugador, String unMazo, String otroMazo) {
        this.modoPartida = modoPartida;
        this.otroJugador = otroJugador;
        this.unMazo = unMazo;
        this.otroMazo = otroMazo;
    }

    public Integer modoPartida() {
        return modoPartida;
    }

    public String otroJugador() {
        return otroJugador;
    }

    public String unMazo() {
        return unMazo;
    }

    public String otroMazo() {
        return otroMazo;
    }

}
