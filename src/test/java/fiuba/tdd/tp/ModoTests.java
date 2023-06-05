package fiuba.tdd.tp;
import fiuba.tdd.tp.modo.Modo;
import fiuba.tdd.tp.modo.Modo1;
import fiuba.tdd.tp.modo.Modo2;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class ModoTests {

    @Test
    void testAgregarCartaModo1() {

        Modo modo1 = new Modo1();
        HashMap<String, Integer> cartas = new HashMap<>();
        cartas.put("Carta1",1);

        assertEquals(modo1.agregarCarta(cartas, "Carta2"),true);
    }

    @Test
    void testAgregarCartaModo2Invalido() {

        Modo modo2 = new Modo2();
        HashMap<String, Integer> cartas = new HashMap<>();
        cartas.put("Carta1",1);

        assertEquals(modo2.agregarCarta(cartas, "Carta2"),false);
    }
}
