package fiuba.tdd.tp.model.Excepciones;

public class CartaNoEncontrada extends Exception {
    public CartaNoEncontrada(String mensaje) {
        super(mensaje);
    }
}