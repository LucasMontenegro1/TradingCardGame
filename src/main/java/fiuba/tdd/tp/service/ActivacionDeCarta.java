package fiuba.tdd.tp.service;

import java.util.ArrayList;

public class ActivacionDeCarta {

	private Integer carta;
	private Integer indiceMetodo;
	private String jugadorObjetivo;
	private ArrayList<Integer> cartasObjetivos;
	private String energia;

	public ActivacionDeCarta(Integer carta, Integer indiceMetodo, String jugadorObjetivo,
			ArrayList<Integer> cartasObjetivos, String energia) {
		this.indiceMetodo = indiceMetodo;
		this.carta = carta;
		this.jugadorObjetivo = jugadorObjetivo;
		this.cartasObjetivos = cartasObjetivos;
		this.energia = energia;
	}

	public Integer carta() {
		return carta;
	}

	public Integer indiceMetodo() {
		return indiceMetodo;
	}

	public String jugadorObjetivo() {
		return jugadorObjetivo;
	}

	public ArrayList<Integer> cartasObjetivos() {
		return cartasObjetivos;
	}

	public String energia() {
		return energia;
	}

}
