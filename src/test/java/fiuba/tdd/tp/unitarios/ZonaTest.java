package fiuba.tdd.tp.unitarios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import fiuba.tdd.tp.model.Excepciones.MovimientoInvalido;
import fiuba.tdd.tp.model.zona.Zona;
import fiuba.tdd.tp.model.zona.ZonaArtefacto;
import fiuba.tdd.tp.model.zona.ZonaCombate;
import fiuba.tdd.tp.model.zona.ZonaDescarte;
import fiuba.tdd.tp.model.zona.ZonaMano;
import fiuba.tdd.tp.model.zona.ZonaReserva;

public class ZonaTest {

    // Zona de Artefactos

    @Test
    public void testZonaDeArtefactoCambiarZona() throws MovimientoInvalido {
        Zona zona = new ZonaArtefacto();
        
        assertThrows(MovimientoInvalido.class, () -> {
            zona.cambiarZona();
        });
    }

    @Test
    public void testZonaDeArtefactoMoverACombate() throws MovimientoInvalido {
        Zona zona = new ZonaArtefacto();

        assertThrows(MovimientoInvalido.class, () -> {
            zona.moverACombate();
        });
    }

    @Test
    public void testZonaDeArtefactoMoverAReserva() throws MovimientoInvalido {
        Zona zona = new ZonaArtefacto();
        assertThrows(MovimientoInvalido.class, () -> {
            zona.moverAReserva();
        });
    }

    @Test
    public void testZonaDeArtefactoInvocar() throws MovimientoInvalido {
        Zona zona = new ZonaArtefacto();
        
        assertThrows(MovimientoInvalido.class, () -> {
            zona.invocar();
        });
    }

    @Test
    public void testZonaDeArtefactoDescartar() {
        Zona zona = new ZonaArtefacto();
        assertEquals(zona.descartar().getClass(), ZonaDescarte.class);
    }

    // Zona de Combate

    @Test
    public void testZonaDeCombateCambiarZona() throws MovimientoInvalido {
        Zona zona = new ZonaCombate();
        assertEquals(zona.cambiarZona().getClass(), ZonaReserva.class);
    }

    @Test
    public void testZonaDeCombateMoverACombate() throws MovimientoInvalido {
        Zona zona = new ZonaCombate();

        assertThrows(MovimientoInvalido.class, () -> {
            zona.moverACombate();
        });
    }

    @Test
    public void testZonaDeCombateMoverAReserva() throws MovimientoInvalido {
        Zona zona = new ZonaCombate();
        
        assertThrows(MovimientoInvalido.class, () -> {
            zona.moverAReserva();
        });
    }

    @Test
    public void testZonaDeCombateInvocar() throws MovimientoInvalido {
        Zona zona = new ZonaCombate();

        assertThrows(MovimientoInvalido.class, () -> {
            zona.invocar();
        });
    }

    @Test
    public void testZonaDeCombateDescartar() {
        Zona zona = new ZonaCombate();
        assertEquals(zona.descartar().getClass(), ZonaDescarte.class);
    }

    // Zona de Descarte

    @Test
    public void testZonaDeDescarteCambiarZona() throws MovimientoInvalido {
        Zona zona = new ZonaDescarte();

        assertThrows(MovimientoInvalido.class, () -> {
            zona.cambiarZona();
        });
    }

    @Test
    public void testZonaDeDescarteMoverACombate() throws MovimientoInvalido {
        Zona zona = new ZonaDescarte();

        assertThrows(MovimientoInvalido.class, () -> {
            zona.moverACombate();
        });
    }

    @Test
    public void testZonaDeDescarteMoverAReserva() throws MovimientoInvalido {
        Zona zona = new ZonaDescarte();

        assertThrows(MovimientoInvalido.class, () -> {
            zona.moverAReserva();
        });
    }

    @Test
    public void testZonaDeDescarteInvocar() throws MovimientoInvalido {
        Zona zona = new ZonaDescarte();

        assertThrows(MovimientoInvalido.class, () -> {
            zona.invocar();
        });
    }

    @Test
    public void testZonaDeDescarteDescartar() {
        Zona zona = new ZonaDescarte();
        assertEquals(zona.descartar().getClass(), ZonaDescarte.class);
    }

    // Zona Mano

    @Test
    public void testZonaManoCambiarZona() throws MovimientoInvalido {
        Zona zona = new ZonaMano();

        assertThrows(MovimientoInvalido.class, () -> {
            zona.cambiarZona();
        });
    }

    @Test
    public void testZonaManoMoverACombate() throws MovimientoInvalido {
        Zona zona = new ZonaMano();
        assertEquals(zona.moverACombate().getClass(), ZonaCombate.class);
    }

    @Test
    public void testZonaManoMoverAReserva() throws MovimientoInvalido {
        Zona zona = new ZonaMano();
        assertEquals(zona.moverAReserva().getClass(), ZonaReserva.class);
    }

    @Test
    public void testZonaManoInvocar() throws MovimientoInvalido {
        Zona zona = new ZonaMano();
        assertEquals(zona.invocar().getClass(), ZonaArtefacto.class);
    }

    @Test
    public void testZonaManoDescartar() {
        Zona zona = new ZonaMano();
        assertEquals(zona.descartar().getClass(), ZonaDescarte.class);
    }

    // Zona de Reserva

    @Test
    public void testZonaDeReservaCambiarZona() throws MovimientoInvalido {
        Zona zona = new ZonaReserva();
        assertEquals(zona.cambiarZona().getClass(), ZonaCombate.class);
    }

    @Test
    public void testZonaDeReservaMoverACombate() throws MovimientoInvalido {
        Zona zona = new ZonaReserva();

        assertThrows(MovimientoInvalido.class, () -> {
            zona.moverACombate();
        });
    }

    @Test
    public void testZonaDeReservaMoverAReserva() throws MovimientoInvalido {
        Zona zona = new ZonaReserva();

        assertThrows(MovimientoInvalido.class, () -> {
            zona.moverAReserva();
        });
    }

    @Test
    public void testZonaDeReservaInvocar() throws MovimientoInvalido {
        Zona zona = new ZonaReserva();
        
        assertThrows(MovimientoInvalido.class, () -> {
            zona.invocar();
        });
    }

    @Test
    public void testZonaDeReservaDescartar() {
        Zona zona = new ZonaReserva();
        assertEquals(zona.descartar().getClass(), ZonaDescarte.class);
    }
}
