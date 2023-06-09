package fiuba.tdd.tp.carta;

public enum Energia {
    Agua("AGUA"),
    Fuego("FUEGO"),
    Planta("PLANTA");

    public String tipo;

    Energia(String tipo) {
        this.tipo = tipo;
    }

    public static boolean esEnergia(String nombreCarta) {
        return Agua.tipo == nombreCarta || Fuego.tipo == nombreCarta || Planta.tipo == nombreCarta;
    }
}
