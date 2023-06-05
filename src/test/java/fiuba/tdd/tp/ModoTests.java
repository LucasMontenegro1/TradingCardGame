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

    @Test
    void testAgregarCartaRepetidaModo1() {
        Modo modo1 = new Modo1();
        HashMap<String, Integer> cartas = new HashMap<>();
        cartas.put("Carta1",3);

        assertEquals(modo1.agregarCarta(cartas, "Carta1"),false);
    }

    @Test
    void testAgregarCartasMazoLlenoModo1() {
        Modo modo1 = new Modo1();
        HashMap<String, Integer> cartas = new HashMap<>();
        cartas.put("Carta1",60);

        assertEquals(modo1.agregarCarta(cartas, "Carta2"),false);
    }

    @Test
    void testRemoverCartaModo1() {
        Modo modo1 = new Modo1();
        HashMap<String, Integer> cartas = new HashMap<>();
        cartas.put("Carta1",40);

        assertEquals(modo1.removerCarta(cartas, "Carta1"),false);
    }

    @Test
    void testRemoverCartaInexistenteModo1() {
        Modo modo1 = new Modo1();
        HashMap<String, Integer> cartas = new HashMap<>();
        cartas.put("Carta1",50);

        assertEquals(modo1.removerCarta(cartas, "Carta2"),false);
    }

    @Test
    void testRemoverCartaValidoModo1() {
        Modo modo1 = new Modo1();
        HashMap<String, Integer> cartas = new HashMap<>();
        cartas.put("Carta1",50);

        assertEquals(modo1.removerCarta(cartas, "Carta1"),true);
    }

    @Test
    void testVerificarMazoValidoModo1() {
        Modo modo1 = new Modo1();
        HashMap<String, Integer> cartas = new HashMap<>();
        for (int i = 1; i <= 50; i++) {
            String key = "Carta" + i;
            cartas.put(key, 1);
        }
        assertEquals(modo1.verificarMazoValido(cartas),true);
    }

    @Test
    void testVerificarMazoMuyGrandeModo1() {
        Modo modo1 = new Modo1();
        HashMap<String, Integer> cartas = new HashMap<>();
        for (int i = 1; i <= 61; i++) {
            String key = "Carta" + i;
            cartas.put(key, 1);
        }
        assertEquals(modo1.verificarMazoValido(cartas),false);
    }

    @Test
    void testVerificarMazoMuyChicoModo1() {
        Modo modo1 = new Modo1();
        HashMap<String, Integer> cartas = new HashMap<>();

        for (int i = 1; i <= 39; i++) {
            String key = "Carta" + i;
            cartas.put(key, 1);
        }

        assertEquals(modo1.verificarMazoValido(cartas),false);
    }

    @Test
    void testVerificarMazoConCartasRepetidasModo1() {
        Modo modo1 = new Modo1();
        HashMap<String, Integer> cartas = new HashMap<>();
        for (int i = 1; i <= 50; i++) {
            String key = "Carta" + i;
            cartas.put(key, 1);
        }
        cartas.put("CartaRepetida", 4);

        assertEquals(modo1.verificarMazoValido(cartas),false);
    }

    @Test
    void testVerificarMazoValidoModo2() {
        Modo modo2 = new Modo2();
        HashMap<String, Integer> cartas = new HashMap<>();
        for (int i = 1; i <= 60; i++) {
            String key = "Carta" + i;
            cartas.put(key, 1);
        }
        assertEquals(modo2.verificarMazoValido(cartas),true);
    }

    @Test
    void testVerificarMazoMuyGrandeModo2() {
        Modo modo2 = new Modo2();
        HashMap<String, Integer> cartas = new HashMap<>();
        for (int i = 1; i <= 61; i++) {
            String key = "Carta" + i;
            cartas.put(key, 1);
        }
        assertEquals(modo2.verificarMazoValido(cartas),false);
    }

    @Test
    void testVerificarMazoMuyChicoModo2() {
        Modo modo2 = new Modo2();
        HashMap<String, Integer> cartas = new HashMap<>();

        for (int i = 1; i <= 59; i++) {
            String key = "Carta" + i;
            cartas.put(key, 1);
        }

        assertEquals(modo2.verificarMazoValido(cartas),false);
    }

    @Test
    void testVerificarMazoConCartasRepetidasModo2() {
        Modo modo2 = new Modo2();
        HashMap<String, Integer> cartas = new HashMap<>();
        for (int i = 1; i <= 55; i++) {
            String key = "Carta" + i;
            cartas.put(key, 1);
        }
        cartas.put("CartaRepetida", 5);

        assertEquals(modo2.verificarMazoValido(cartas),false);
    }
}
