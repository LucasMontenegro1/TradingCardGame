package fiuba.tdd.tp;

import fiuba.tdd.tp.carta.Carta;
import fiuba.tdd.tp.carta.CartasDisponibles;
import fiuba.tdd.tp.carta.Tipo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CartaTests {
    @Test
    void testCrearCarta() {    
        Carta carta = new Carta(CartasDisponibles.AGUA);

        Assertions.assertTrue(carta.esTipo(Tipo.Artefacto));
        Assertions.assertEquals("AGUA", carta.nombreCarta());
    }
    
    @Test
    void testCrearCartaTipoCriatura() {
        Carta carta = new Carta(CartasDisponibles.ALQUIMISTA);
        Assertions.assertTrue(carta.esTipo(Tipo.Criatura));
    }

    
    @Test
    void testCrearCartaTipoAccion() {
        Carta carta = new Carta(CartasDisponibles.ANTIMAGIA);
        Assertions.assertTrue(carta.esTipo(Tipo.Accion));
    }

    
    @Test
    void testCrearCartaTipoReaccion() {
        Carta carta = new Carta(CartasDisponibles.BARRERAMAGICA);
        Assertions.assertTrue(carta.esTipo(Tipo.Reaccion));
    }

    
    @Test
    void testCrearCartaTipoArtefacto() {
        Carta carta = new Carta(CartasDisponibles.ALQUIMISTA);
        Assertions.assertTrue(carta.esTipo(Tipo.Artefacto));
    }

    @Test
    void testCrearCartaConVariosTipos() {
        Carta carta = new Carta(CartasDisponibles.ALQUIMISTA);
        Assertions.assertTrue(carta.esTipo(Tipo.Criatura));
        Assertions.assertTrue(carta.esTipo(Tipo.Artefacto));
    }
    
    @Test
    void testCrearCartaAntimagia() {
        Carta carta = new Carta(CartasDisponibles.ANTIMAGIA);
        assertTrue(carta.nombreCarta() == CartasDisponibles.ANTIMAGIA.nombre);
    }

    @Test
    void testCartaDisminuirHP() {
        Carta alquimista = new Carta(CartasDisponibles.ALQUIMISTA);

        alquimista.disminuirHP(2);

        assertEquals(alquimista.hp, 1);
    }

    @Test
    void testCartaDisminuirHPMasDelHPDeLaCarta() {
        Carta alquimista = new Carta(CartasDisponibles.ALQUIMISTA);

        alquimista.disminuirHP(4);

        assertEquals(alquimista.hp, 0);
    }

    @Test
    void testCartaAumentarHP() {
        Carta alquimista = new Carta(CartasDisponibles.ALQUIMISTA);

        alquimista.disminuirHP(2);
        alquimista.aumentarHP(1);

        assertEquals(alquimista.hp, 2);
    }

    @Test
    void testCartaNoPuedeAumentarMasSuHP() {
        Carta alquimista = new Carta(CartasDisponibles.ALQUIMISTA);

        assertEquals(alquimista.hp, 3);
        alquimista.aumentarHP(2);
        assertEquals(alquimista.hp, 3);
    }
}
