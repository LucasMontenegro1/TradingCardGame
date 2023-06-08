package fiuba.tdd.tp.carta;

import java.util.ArrayList;
import java.util.List;

public enum CartasDisponibles {
    ENERGIA("ENERGIA", 10,new ArrayList<Atributo>(), new ArrayList<Tipo>(){{add(Tipo.Artefacto);}}),
    ALQUMISTA("Alquimista", 20,new ArrayList<Atributo>(){{add(Atributo.Ciencia);}}, new ArrayList<Tipo>(){{add(Tipo.Criatura); add(Tipo.Artefacto);}} ),
    ANTIMAGIA("Antimagia", 15,new ArrayList<Atributo>(), new ArrayList<Tipo>(){{add(Tipo.Artefacto);add(Tipo.Accion);}}),
    BARRERAMAGICA("Barrera Mágica", 12, new ArrayList<>(){{add(Atributo.Magia);}},new ArrayList<Tipo>(){{add(Tipo.Criatura); add(Tipo.Reaccion);}});

    private String nombre;
    private int precio;
    private List<Tipo> tipos;
    private List<Atributo> atributos;

    CartasDisponibles(String nombre, int precio,List<Atributo> atributos, ArrayList<Tipo> tipos) {
        this.nombre = nombre;
        this.precio = precio;
        this.tipos = tipos;
        this.atributos = atributos;
    }

    public List<Atributo> atributos(){
        return this.atributos;
    }

    public List<Tipo> tipos(){
        return tipos;
    }

    public String nombreCarta() {
        return nombre;
    }

    public int precioCarta() {
        return precio;
    }
}
