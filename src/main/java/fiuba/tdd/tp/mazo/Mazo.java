package fiuba.tdd.tp.mazo;

import java.util.HashMap;

import fiuba.tdd.tp.Excepciones.MazoInvalido;
import fiuba.tdd.tp.modo.Modo;

public class Mazo {
    private HashMap<String, Integer> cartas;
    private Modo modo;

    public Mazo(HashMap<String, Integer> cartas, Modo modo) throws MazoInvalido {
        this.modo = modo;
        if (this.modo.verificarMazoValido(cartas)) {
            System.out.println("No debería entrar acá");
            this.cartas = cartas;
        } else {
            throw new MazoInvalido("No se puede crear el mazo con las cartas seleccionadas");
        }
    }

    /*
    public Integer tomarCarta() {
        if (cartas.size() == 0){
            return null;
        }

        Random random = new Random();
        return this.cartas.get(random.nextInt(this.cartas.size()));
    }

    public void agregarCarta(Carta carta){
        // this.cartas.add(carta);
    }
     */
}
