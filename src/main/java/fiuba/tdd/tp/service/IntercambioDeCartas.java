package fiuba.tdd.tp.service;

public class IntercambioDeCartas  {
    
    private String cartaDispuesta;
    private String cartaDeseada;

    public IntercambioDeCartas (String cartaDispuesta, String cartaDeseada) {
        this.cartaDispuesta = cartaDispuesta;
        this.cartaDeseada = cartaDeseada;
    }

    public String cartaDispuesta() {
        return cartaDispuesta;
    }

    public String cartaDeseada() {
        return cartaDeseada;
    }

}
