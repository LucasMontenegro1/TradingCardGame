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
}
