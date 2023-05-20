package fiuba.tdd.tp.carta;

public class Carta {
    String nombre;
    String descripcion;
    Tipo tipo;


    Carta(Tipo tipo, String nombre, String descripcion){
        this.tipo = tipo;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

}
