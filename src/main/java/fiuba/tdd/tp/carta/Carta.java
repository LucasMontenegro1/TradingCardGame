package fiuba.tdd.tp.carta;

import java.util.ArrayList;
import java.util.HashMap;

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
    public HashMap<Energia, Integer> costoDeInvocacion = new HashMap<>();

    public Carta(CartasDisponibles carta) {
        this.nombre = carta.nombre;
        this.hp = carta.hp;
        this.maxHP = carta.hp;
        this.tipos = carta.tipos;
        this.atributos = carta.atributos;
        this.metodos = carta.metodos;
        this.costoDeInvocacion.put(Energia.Fuego, carta.costoDeInvocacion.get(0));
        this.costoDeInvocacion.put(Energia.Planta, carta.costoDeInvocacion.get(1));
        this.costoDeInvocacion.put(Energia.Agua, carta.costoDeInvocacion.get(2));
        this.zona = null;
    }

    public boolean esTipo(Tipo tipo) {
        return this.tipos.contains(tipo);
    }

    public String nombreCarta() {
        return nombre;
    }

    public boolean esAtributo(Atributo atributo) {
        return this.atributos.contains(atributo);
    }

    public void cambiarZona() {
        if (this.zona == null) {
            this.zona = new ZonaMano();
        } else {
            this.zona = this.zona.cambiarZona();
        }
    }
    
    public void moverAArtefacto() {
        this.zona = this.zona.invocar();
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

    public HashMap<Energia, Integer> getCostoDeInvocacion(){
        return this.costoDeInvocacion;
    }
}