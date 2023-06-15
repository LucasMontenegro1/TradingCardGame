package fiuba.tdd.tp.Excepciones;

public class CartaNoEncontrada extends Exception {
    public CartaNoEncontrada(String mensaje) {
        super(mensaje);
    }
}