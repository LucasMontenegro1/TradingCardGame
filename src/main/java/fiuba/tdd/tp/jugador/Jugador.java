package fiuba.tdd.tp.jugador;

public class Jugador {
    public String nombre;
    private String contra;
    public int cantdDinero;

    public Jugador(String nombreJugador, String contra) {
        this.nombre = nombreJugador;
        this.contra = contra;
        this.cantdDinero = 0;
    }
}
