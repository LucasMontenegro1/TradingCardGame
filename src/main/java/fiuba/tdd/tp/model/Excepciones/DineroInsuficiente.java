package fiuba.tdd.tp.model.Excepciones;

public class DineroInsuficiente extends Exception {
    public DineroInsuficiente(String mensaje) {
        super(mensaje);
    }
}