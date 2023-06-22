package fiuba.tdd.tp.service;


public class SolicitudDePartida {
    
    private Integer modoPartida;
	private String unJugador;
	private String otroJugador;
	private String unMazo;
	private String otroMazo;

    public SolicitudDePartida (Integer modoPartida, String unJugador, String otroJugador, String unMazo, String otroMazo) {
        this.modoPartida = modoPartida;
        this.unJugador = unJugador;
        this.otroJugador = otroJugador;
        this.unMazo = unMazo;
        this.otroMazo = otroMazo;
    }

}
