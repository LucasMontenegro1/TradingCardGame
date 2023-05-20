package fiuba.tdd.tp.carta;

public class Carta {
    String nombre;
    String descripcion;
    Tipo tipo;


    public Carta(Tipo tipo, String nombre, String descripcion){
        this.tipo = tipo;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Tipo getTipo() {
        return tipo;
    }


    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
