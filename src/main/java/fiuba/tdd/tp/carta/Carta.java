package fiuba.tdd.tp.carta;

import java.util.ArrayList;

import fiuba.tdd.tp.carta.Metodos.MetodoCarta;
import fiuba.tdd.tp.zona.Zona;
import fiuba.tdd.tp.zona.ZonaDescarte;
import fiuba.tdd.tp.zona.ZonaMano;

public class Carta {
    public String nombre;
    public ArrayList<Tipo> tipos;
    public ArrayList<Atributo> atributos;
    public Zona zona;
    public ArrayList<MetodoCarta> metodos;
    public Integer hp;
    public Integer maxHP;
    public ArrayList<Integer> costoDeInvocacion;

    public Carta(CartasDisponibles carta) {
        this.nombre = carta.nombre;
        this.hp = carta.hp;
        this.maxHP = carta.hp;
        this.tipos = carta.tipos;
        this.atributos = carta.atributos;
        this.metodos = carta.metodos;
        this.costoDeInvocacion = carta.costoDeInvocacion;
        this.zona = null;
    }

    public boolean esTipo(Tipo tipo) {
        return this.tipos.contains(tipo);
    }

    public String nombreCarta() {
        return nombre;
    }

    public void cambiarZona() {
        if (this.zona == null) {
            this.zona = new ZonaMano();
        } else {
            this.zona = this.zona.cambiarZona();
        }
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
        if (this.hp != null && this.hp > 0) {
            this.hp = Math.min(cantidad + this.hp, this.maxHP);
        
        }
    }

    public void disminuirHP(Integer cantidad) {
        if (this.hp != null) {
            this.hp = Math.max(this.hp - cantidad, 0);
        }
    }

    public ArrayList<Integer> getCostoDeInvocacion(){
        return this.costoDeInvocacion;
    }
}