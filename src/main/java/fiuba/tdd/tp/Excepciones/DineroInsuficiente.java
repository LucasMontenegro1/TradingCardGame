package fiuba.tdd.tp.Excepciones;

public class DineroInsuficiente extends Exception {
    public DineroInsuficiente(String mensaje) {
        super(mensaje);
    }
}