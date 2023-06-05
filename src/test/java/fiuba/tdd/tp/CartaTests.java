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
        Carta carta = new Carta(CartasDisponibles.ENERGIA);

        Assertions.assertTrue(carta.isTipo(Tipo.Artefacto));
        Assertions.assertEquals("Energía", carta.getNombre());
    }
    
    @Test
    public void crearCartaTipoCriatura() {
        Carta carta = new Carta(CartasDisponibles.ALQUMISTA);
        Assertions.assertTrue(carta.isTipo(Tipo.Criatura));
    }

    
    @Test
    public void crearCartaTipoAccion() {
        Carta carta = new Carta(CartasDisponibles.ANTIMAGIA);
        Assertions.assertTrue(carta.isTipo(Tipo.Accion));
    }

    
    @Test
    public void crearCartaTipoReaccion() {
        Carta carta = new Carta(CartasDisponibles.BARRERAMAGICA);
        Assertions.assertTrue(carta.isTipo(Tipo.Reaccion));
    }

    
    @Test
    public void crearCartaTipoArtefacto() {
        Carta carta = new Carta(CartasDisponibles.ALQUMISTA);
        Assertions.assertTrue(carta.isTipo(Tipo.Artefacto));
    }

    @Test
    public void crearCartaConVariosTipos() {
        Carta carta = new Carta(CartasDisponibles.ALQUMISTA);
        Assertions.assertTrue(carta.isTipo(Tipo.Criatura));
        Assertions.assertTrue(carta.isTipo(Tipo.Artefacto));
    }

    
    @Test
    public void crearCartaAntimagia(){
        Carta carta = new Carta(CartasDisponibles.ANTIMAGIA);
        assertTrue(carta.getNombre() == CartasDisponibles.ANTIMAGIA.nombreCarta());
    }
}
