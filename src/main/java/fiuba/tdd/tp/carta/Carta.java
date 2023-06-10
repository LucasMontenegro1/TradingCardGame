package fiuba.tdd.tp.carta;

import java.util.ArrayList;
import java.util.List;

import fiuba.tdd.tp.carta.Metodos.MetodoCarta;
import fiuba.tdd.tp.zona.Zona;
import fiuba.tdd.tp.zona.ZonaDescarte;

public class Carta {
    String nombre;
    List<Tipo> tipos;
    List<Atributo> atributos;
    public Zona zona;
    public ArrayList<MetodoCarta> metodos;

    public Carta(CartasDisponibles carta) {
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
}