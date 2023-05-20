package fiuba.tdd.tp;

import fiuba.tdd.tp.carta.Carta;
import fiuba.tdd.tp.carta.Tipo;
import fiuba.tdd.tp.carta.tipos.Criatura;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CartaTests {
    @Test
    public void crearCarta() {
        Tipo tipo = new Criatura();
        String nombre = "Carta1";
        String descripcion = "Descripción de la carta";

        Carta carta = new Carta(tipo, nombre, descripcion);

        Assertions.assertEquals(tipo, carta.getTipo());
        Assertions.assertEquals(nombre, carta.getNombre());
        Assertions.assertEquals(descripcion, carta.getDescripcion());
    }


}
