package fiuba.tdd.tp.carta;

import java.util.ArrayList;

import fiuba.tdd.tp.carta.Metodos.MetodoCarta;
import fiuba.tdd.tp.zona.Zona;
import fiuba.tdd.tp.zona.ZonaDescarte;

public class Carta {
    public String nombre;
    public ArrayList<Tipo> tipos;
    public ArrayList<Atributo> atributos;
    public Zona zona;
    public ArrayList<MetodoCarta> metodos;
    public Integer hp;

    public Carta(CartasDisponibles carta) {
        this.hp = 10;
        this.tipos = carta.tipos;
        this.nombre = carta.nombre;
        this.atributos = carta.atributos;
        this.metodos = carta.metodos;
        this.zona = null;
    }

    public boolean esTipo(Tipo tipo) {
        return this.tipos.contains(tipo);
    }

    public String nombreCarta() {
        return nombre;
    }

    public void cambiarZona() {
        this.zona = this.zona.cambiarZona();
    }
    
    public void moverACombate() {
        if (this.nombre != "BARRERAMAGICA") {
            this.zona = this.zona.moverACombate();
        }
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

    public void aumentarHP(Integer cantidad) {
        this.hp += cantidad;
    }
}