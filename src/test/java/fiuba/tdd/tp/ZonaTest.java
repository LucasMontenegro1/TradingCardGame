package fiuba.tdd.tp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import fiuba.tdd.tp.zona.Zona;
import fiuba.tdd.tp.zona.ZonaArtefecto;
import fiuba.tdd.tp.zona.ZonaCombate;
import fiuba.tdd.tp.zona.ZonaDescarte;
import fiuba.tdd.tp.zona.ZonaMano;
import fiuba.tdd.tp.zona.ZonaReserva;

public class ZonaTest {

    // Zona de Artefactos

    @Test
    public void testZonaDeArtefactoCambiarZona() {
        Zona zona = new ZonaArtefecto();
        assertEquals(zona.cambiarZona().getClass(), ZonaArtefecto.class);
    }

    @Test
    public void testZonaDeArtefactoMoverACombate() {
        Zona zona = new ZonaArtefecto();
        assertEquals(zona.moverACombate().getClass(), ZonaArtefecto.class);
    }

    @Test
    public void testZonaDeArtefactoMoverAReserva() {
        Zona zona = new ZonaArtefecto();
        assertEquals(zona.moverAReserva().getClass(), ZonaArtefecto.class);
    }

    @Test
    public void testZonaDeArtefactoInvocar() {
        Zona zona = new ZonaArtefecto();
        assertEquals(zona.invocar().getClass(), ZonaArtefecto.class);
    }

    @Test
    public void testZonaDeArtefactoDescartar() {
        Zona zona = new ZonaArtefecto();
        assertEquals(zona.descartar().getClass(), ZonaDescarte.class);
    }

    // Zona de Combate

    @Test
    public void testZonaDeCombateCambiarZona() {
        Zona zona = new ZonaCombate();
        assertEquals(zona.cambiarZona().getClass(), ZonaReserva.class);
    }

    @Test
    public void testZonaDeCombateMoverACombate() {
        Zona zona = new ZonaCombate();
        assertEquals(zona.moverACombate().getClass(), ZonaCombate.class);
    }

    @Test
    public void testZonaDeCombateMoverAReserva() {
        Zona zona = new ZonaCombate();
        assertEquals(zona.moverAReserva().getClass(), ZonaCombate.class);
    }

    @Test
    public void testZonaDeCombateInvocar() {
        Zona zona = new ZonaCombate();
        assertEquals(zona.invocar().getClass(), ZonaCombate.class);
    }

    @Test
    public void testZonaDeCombateDescartar() {
        Zona zona = new ZonaCombate();
        assertEquals(zona.descartar().getClass(), ZonaDescarte.class);
    }

    // Zona de Descarte

    @Test
    public void testZonaDeDescarteCambiarZona() {
        Zona zona = new ZonaDescarte();
        assertEquals(zona.cambiarZona().getClass(), ZonaDescarte.class);
    }

    @Test
    public void testZonaDeDescarteMoverACombate() {
        Zona zona = new ZonaDescarte();
        assertEquals(zona.moverACombate().getClass(), ZonaDescarte.class);
    }

    @Test
    public void testZonaDeDescarteMoverAReserva() {
        Zona zona = new ZonaDescarte();
        assertEquals(zona.moverAReserva().getClass(), ZonaDescarte.class);
    }

    @Test
    public void testZonaDeDescarteInvocar() {
        Zona zona = new ZonaDescarte();
        assertEquals(zona.invocar().getClass(), ZonaDescarte.class);
    }

    @Test
    public void testZonaDeDescarteDescartar() {
        Zona zona = new ZonaDescarte();
        assertEquals(zona.descartar().getClass(), ZonaDescarte.class);
    }

    // Zona Mano

    @Test
    public void testZonaManoCambiarZona() {
        Zona zona = new ZonaMano();
        assertEquals(zona.cambiarZona().getClass(), ZonaMano.class);
    }

    @Test
    public void testZonaManoMoverACombate() {
        Zona zona = new ZonaMano();
        assertEquals(zona.moverACombate().getClass(), ZonaCombate.class);
    }

    @Test
    public void testZonaManoMoverAReserva() {
        Zona zona = new ZonaMano();
        assertEquals(zona.moverAReserva().getClass(), ZonaReserva.class);
    }

    @Test
    public void testZonaManoInvocar() {
        Zona zona = new ZonaMano();
        assertEquals(zona.invocar().getClass(), ZonaArtefecto.class);
    }

    @Test
    public void testZonaManoDescartar() {
        Zona zona = new ZonaMano();
        assertEquals(zona.descartar().getClass(), ZonaDescarte.class);
    }
}
