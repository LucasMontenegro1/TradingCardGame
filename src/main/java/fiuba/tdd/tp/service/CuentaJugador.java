package fiuba.tdd.tp.service;

public class CuentaJugador  {
    
    private String username;
    private String password;

    public CuentaJugador(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String username() {
        return username;
    }

    public String password() {
        return password;
    }

}
