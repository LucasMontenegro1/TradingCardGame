package fiuba.tdd.tp.carta;

import java.util.ArrayList;
import java.util.List;

public class Carta {
    String nombre;
    String descripcion;
    List<Tipo> tipos;
    List<Atributo> atributos;


    public Carta(List<Tipo> tipos, String nombre, String descripcion){
        this.tipos = tipos;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.atributos = new ArrayList<>();
    }

    public Carta(List<Tipo> tipos, String nombre,
                 String descripcion, List<Atributo> atributos){
        this(tipos,nombre,descripcion);
        if(this.isTipo(Tipo.Criatura)) {
            this.atributos = atributos;
        } else {
            throw new IllegalArgumentException("Solo las cartas tipo criatura pueden tener atributos");
        }
    }

    public Carta(Tipo tipo, String nombre, String descripcion){
        this.tipos = new ArrayList<Tipo>();
        this.tipos.add(tipo);
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public boolean isTipo(Tipo tipo){
        return this.tipos.contains(tipo);
    }

    public boolean hasAtributo(Atributo atributo){return this.atributos.contains(atributo);}

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
