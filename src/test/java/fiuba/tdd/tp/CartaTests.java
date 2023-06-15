package fiuba.tdd.tp;

import fiuba.tdd.tp.carta.Carta;
import fiuba.tdd.tp.carta.CartasDisponibles;
import fiuba.tdd.tp.carta.Energia;
import fiuba.tdd.tp.carta.Tipo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.HashMap;

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
    void testCartaNoPuedeAumentarSuHPMasAllaDeSuMaximo() {
        Carta alquimista = new Carta(CartasDisponibles.ALQUIMISTA);

        alquimista.disminuirHP(1);
        alquimista.aumentarHP(2);
        assertEquals(alquimista.hp, 3);
    }

    @Test
    void testCartaNoPuedeAumentarMasSuHPCuandoEstaEnSuMaximo() {
        Carta alquimista = new Carta(CartasDisponibles.ALQUIMISTA);

        assertEquals(alquimista.hp, 3);
        alquimista.aumentarHP(2);
        assertEquals(alquimista.hp, 3);
    }

    @Test
    void testAlquimistaConCostoDeInvocacion(){
        Carta alquimista = new Carta(CartasDisponibles.ALQUIMISTA);
        HashMap<Energia, Integer> esperado = new HashMap<>();
        
        esperado.put(Energia.Fuego, 1);
        esperado.put(Energia.Planta, 1);
        esperado.put(Energia.Agua, 0);
        
        assertEquals(esperado,alquimista.getCostoDeInvocacion());
    }

    @Test
    void testCostoDeCualquierTipo(){
        Carta antimagia = new Carta(CartasDisponibles.ANTIMAGIA);
        HashMap<Energia, Integer> esperado = new HashMap<>();
        
        esperado.put(Energia.Fuego, null);
        esperado.put(Energia.Planta, null);
        esperado.put(Energia.Agua, null);

        assertEquals(esperado,antimagia.getCostoDeInvocacion());
    }
}
