package fiuba.tdd.tp.controller;

public class CuentaJugador {
    
    private String usuario;
    private String password;

    public CuentaJugador(String usuario, String password) {
        this.usuario = usuario;
        this.password = password;
    }

    public String usuario() {
        return usuario;
    }

    public String password() {
        return password;
    }
}
