package fiuba.tdd.tp.carta;

import java.util.ArrayList;
import java.util.List;

import fiuba.tdd.tp.zona.Zona;
import fiuba.tdd.tp.zona.ZonaDescarte;

public class Carta {
    String nombre;
    List<Tipo> tipos;
    List<Atributo> atributos;

    public Zona zona;

    public Carta(CartasDisponibles carta, Zona zona){
        this.tipos = carta.tipos();
        this.nombre = carta.nombreCarta();
        this.atributos = carta.atributos();
        this.zona = zona;
    }


    public Carta(List<Tipo> tipos, String nombre, Zona zona) {
        this.tipos = tipos;
        this.nombre = nombre;
        this.atributos = new ArrayList<>();
        this.zona = zona;
        if (!this.isTipo(Tipo.Criatura) && !this.atributos.isEmpty()){
            throw new IllegalArgumentException("Solo las cartas tipo criatura pueden tener atributos");
        }
    }

    public Carta(List<Tipo> tipos, String nombre, List<Atributo> atributos, Zona zona) {
        this(tipos, nombre, zona);
        if (this.isTipo(Tipo.Criatura)) {
            this.atributos = atributos;
        } else {
            throw new IllegalArgumentException("Solo las cartas tipo criatura pueden tener atributos");
        }
    }

    public Carta(Tipo tipo, String nombre, Zona zona) {
        this.tipos = new ArrayList<Tipo>();
        this.tipos.add(tipo);
        this.nombre = nombre;
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
