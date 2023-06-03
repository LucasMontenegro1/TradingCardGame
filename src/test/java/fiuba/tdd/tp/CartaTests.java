package fiuba.tdd.tp;

import fiuba.tdd.tp.carta.Atributo;
import fiuba.tdd.tp.carta.Carta;
import fiuba.tdd.tp.carta.CartasDisponibles;
import fiuba.tdd.tp.carta.Tipo;
import fiuba.tdd.tp.zona.Zona;
import fiuba.tdd.tp.zona.ZonaMano;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CartaTests {
    @Test
    public void crearCarta() {
        Tipo tipo = Tipo.Criatura;
        String nombre = "Carta1";
        Zona zona = new ZonaMano();

        Carta carta = new Carta(tipo, nombre, zona);

        Assertions.assertTrue(carta.isTipo(tipo));
        Assertions.assertEquals(nombre, carta.getNombre());
    }

    @Test
    public void crearCartaTipoCriatura() {
        Tipo tipo = Tipo.Criatura;
        String nombre = "Carta1";
        Zona zona = new ZonaMano();

        Carta carta = new Carta(tipo, nombre, zona);
        Assertions.assertTrue(carta.isTipo(Tipo.Criatura));
    }

    @Test
    public void crearCartaTipoAccion() {
        Tipo tipo = Tipo.Accion;
        String nombre = "Carta1";
        String descripcion = "Descripción de la carta";
        Zona zona = new ZonaMano();

        Carta carta = new Carta(tipo, nombre, zona);
        Assertions.assertTrue(carta.isTipo(Tipo.Accion));
    }

    @Test
    public void crearCartaTipoReaccion() {
        Tipo tipo = Tipo.Reaccion;
        String nombre = "Carta1";
        String descripcion = "Descripción de la carta";
        Zona zona = new ZonaMano();

        Carta carta = new Carta(tipo, nombre, zona);
        Assertions.assertTrue(carta.isTipo(Tipo.Reaccion));
    }

    @Test
    public void crearCartaTipoArtefacto() {
        Tipo tipo = Tipo.Artefacto;
        String nombre = "Carta1";
        Zona zona = new ZonaMano();

        Carta carta = new Carta(tipo, nombre, zona);
        Assertions.assertTrue(carta.isTipo(Tipo.Artefacto));
    }

    @Test
    public void crearCartaTipoEnergia() {
        Tipo tipo = Tipo.Energia;
        String nombre = "Carta1";
        Zona zona = new ZonaMano();

        Carta carta = new Carta(tipo, nombre, zona);
        Assertions.assertTrue(carta.isTipo(Tipo.Energia));
    }

    @Test
    public void crearCartaConVariosTipos() {
        Tipo tipo = Tipo.Criatura;
        Tipo tipo2 = Tipo.Accion;
        String nombre = "Carta1";
        Zona zona = new ZonaMano();

        Carta carta = new Carta(new ArrayList<>(Arrays.asList(tipo, tipo2)), nombre, zona);
        Assertions.assertTrue(carta.isTipo(tipo));
        Assertions.assertTrue(carta.isTipo(tipo2));
    }
    @Test
    public void crearCartaNoCriaturaConAtributos() {
        Tipo tipo = Tipo.Accion;
        Atributo atributo = Atributo.Metal;
        String nombre = "Carta1";
        String descripcion = "Descripción de la carta";
        Zona zona = new ZonaMano();

        assertThrows(IllegalArgumentException.class, () -> {
            Carta carta = new Carta(new ArrayList<>(Arrays.asList(tipo)), nombre,
                    new ArrayList<>(Arrays.asList(atributo)), zona);
        });
    }

    @Test
    public void crearCartaAntimagia(){
        Carta carta = new Carta(CartasDisponibles.ANTIMAGIA,new ZonaMano());
        assertTrue(carta.getNombre() == CartasDisponibles.ANTIMAGIA.nombreCarta());
    }
}
