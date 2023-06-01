package fiuba.tdd.tp;

import fiuba.tdd.tp.carta.Atributo;
import fiuba.tdd.tp.carta.Carta;
import fiuba.tdd.tp.carta.Tipo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CartaTests {
    @Test
    public void crearCarta() {
        Tipo tipo = Tipo.Criatura;
        String nombre = "Carta1";
        String descripcion = "Descripción de la carta";

        Carta carta = new Carta(tipo, nombre, descripcion);

        Assertions.assertTrue(carta.isTipo(tipo));
        Assertions.assertEquals(nombre, carta.getNombre());
        Assertions.assertEquals(descripcion, carta.getDescripcion());
    }

    @Test
    public void crearCartaTipoCriatura(){
        Tipo tipo = Tipo.Criatura;
        String nombre = "Carta1";
        String descripcion = "Descripción de la carta";

        Carta carta = new Carta(tipo, nombre, descripcion);
        Assertions.assertTrue(carta.isTipo(Tipo.Accion));
    }

    @Test
    public void crearCartaTipoAccion(){
        Tipo tipo = Tipo.Accion;
        String nombre = "Carta1";
        String descripcion = "Descripción de la carta";

        Carta carta = new Carta(tipo, nombre, descripcion);
        Assertions.assertTrue(carta.isTipo(Tipo.Accion));
    }

    @Test
    public void crearCartaTipoReaccion(){
        Tipo tipo = Tipo.Reaccion;
        String nombre = "Carta1";
        String descripcion = "Descripción de la carta";

        Carta carta = new Carta(tipo, nombre, descripcion);
        Assertions.assertTrue(carta.isTipo(Tipo.Accion));
    }


    @Test
    public void crearCartaTipoArtefacto(){
        Tipo tipo = Tipo.Artefacto;
        String nombre = "Carta1";
        String descripcion = "Descripción de la carta";

        Carta carta = new Carta(tipo, nombre, descripcion);
        Assertions.assertTrue(carta.isTipo(Tipo.Accion));
    }

    @Test
    public void crearCartaTipoEnergia(){
        Tipo tipo = Tipo.Energia;
        String nombre = "Carta1";
        String descripcion = "Descripción de la carta";

        Carta carta = new Carta(tipo, nombre, descripcion);
        Assertions.assertTrue(carta.isTipo(Tipo.Accion));
    }
    @Test
    public void crearCartaConVariosTipos(){
        Tipo tipo = Tipo.Criatura;
        Tipo tipo2 = Tipo.Accion;
        String nombre = "Carta1";
        String descripcion = "Descripción de la carta";

        Carta carta = new Carta(new ArrayList<>(Arrays.asList(tipo, tipo2)), nombre, descripcion);
        Assertions.assertTrue(carta.isTipo(tipo));
        Assertions.assertTrue(carta.isTipo(tipo2));
    }

    @Test
    public void crearCartaConAtributos(){

    }

    @Test
    public void crearCartaNoCriaturaConAtributos(){
        Tipo tipo = Tipo.Accion;
        Atributo atributo = Atributo.Metal;
        String nombre = "Carta1";
        String descripcion = "Descripción de la carta";
        assertThrows(IllegalArgumentException.class, () -> {
            Carta carta = new Carta(new ArrayList<>(Arrays.asList(tipo)), nombre, descripcion,
                    new ArrayList<>(Arrays.asList(atributo)));
        });
    }

}
