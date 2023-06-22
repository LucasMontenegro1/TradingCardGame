package fiuba.tdd.tp.service;


public class InvocacionDeCarta {
    
	private String carta;
	private String zona;

    public InvocacionDeCarta (String carta, String zona) {
        this.carta = carta;
        this.zona = zona;
    }

    public String carta() {
        return carta;
    }

    public String zona() {
        return zona;
    }

}
