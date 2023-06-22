package fiuba.tdd.tp.service;

import java.util.ArrayList;

public class ActivacionDeCarta {

	private String carta;
	private Integer indiceMetodo;
	private String jugadorObjetivo;
	private ArrayList<String> cartasObjetivos;
	private String energia;

	public ActivacionDeCarta(String carta, Integer indiceMetodo, String jugadorObjetivo,
			ArrayList<String> cartasObjetivos, String energia) {
		this.indiceMetodo = indiceMetodo;
		this.carta = carta;
		this.jugadorObjetivo = jugadorObjetivo;
		this.cartasObjetivos = cartasObjetivos;
		this.energia = energia;
	}

	public String carta() {
		return carta;
	}

	public Integer indiceMetodo() {
		return indiceMetodo;
	}

	public String jugadorObjetivo() {
		return jugadorObjetivo;
	}

	public ArrayList<String> cartasObjetivos() {
		return cartasObjetivos;
	}

	public String energia() {
		return energia;
	}

}
