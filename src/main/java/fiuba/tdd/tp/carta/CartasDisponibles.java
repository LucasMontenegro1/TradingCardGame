package fiuba.tdd.tp.carta;

public enum CartasDisponibles {
    ENERGIA("Energía", 10),
    ALQUMISTA("Alquimista", 20),
    ANTIMAGIA("Antimagia", 15),
    BARRERAMAGICA("Barrera Mágica", 12);

    private String nombre;
    private int precio;

    CartasDisponibles(String nombre, int precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public String nombreCarta() {
        return nombre;
    }

    public int precioCarta() {
        return precio;
    }
}
