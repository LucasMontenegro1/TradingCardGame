package fiuba.tdd.tp.mazo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fiuba.tdd.tp.carta.Carta;
import fiuba.tdd.tp.modo.Modo;

public class Mazo {
    public List<Carta> cartas = new ArrayList<Carta>();
    public Modo modo;

    public Carta tomar_carta(){
        Random random = new Random();
        return cartas.get(random.nextInt(cartas.size()));
    }

}
