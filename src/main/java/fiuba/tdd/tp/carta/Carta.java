package fiuba.tdd.tp.carta;

import java.util.ArrayList;
import java.util.List;

import fiuba.tdd.tp.zona.Zona;
import fiuba.tdd.tp.zona.ZonaDescarte;

public class Carta {
    String nombre;
    String descripcion;
    List<Tipo> tipos;
    List<Atributo> atributos;
    public Zona zona;

    public Carta(List<Tipo> tipos, String nombre, String descripcion, Zona zona) {
        this.tipos = tipos;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.atributos = new ArrayList<>();
        this.zona = zona;
    }

    public Carta(List<Tipo> tipos, String nombre,
            String descripcion, List<Atributo> atributos, Zona zona) {
        this(tipos, nombre, descripcion, zona);
        if (this.isTipo(Tipo.Criatura)) {
            this.atributos = atributos;
        } else {
            throw new IllegalArgumentException("Solo las cartas tipo criatura pueden tener atributos");
        }
    }

    public Carta(Tipo tipo, String nombre, String descripcion, Zona zona) {
        this.tipos = new ArrayList<Tipo>();
        this.tipos.add(tipo);
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.zona = zona;
    }

    public boolean isTipo(Tipo tipo) {
        return this.tipos.contains(tipo);
    }

    public boolean hasAtributo(Atributo atributo) {
        return this.atributos.contains(atributo);
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void cambiarZona() {
        this.zona = this.zona.cambiarZona();
    }
    
    public void moverACombate() {
        this.zona = this.zona.moverACombate();
    }
    
    public void moverAReserva(){
        this.zona = this.zona.moverAReserva();
    }
    
    public void aplicarEfecto() {
        this.zona = new ZonaDescarte();
    }

    public void descartar(){
        this.zona = this.zona.descartar();
    }
}
