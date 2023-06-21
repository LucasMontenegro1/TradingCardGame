package fiuba.tdd.tp.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Vector;

import org.springframework.stereotype.Repository;

import fiuba.tdd.tp.model.Excepciones.CartaNoActivable;
import fiuba.tdd.tp.model.Excepciones.CartaNoEncontrada;
import fiuba.tdd.tp.model.Excepciones.EnergiaInsuficiente;
import fiuba.tdd.tp.model.Excepciones.ModoSinPuntosDeVida;
import fiuba.tdd.tp.model.Excepciones.MovimientoInvalido;
import fiuba.tdd.tp.model.Excepciones.PartidaInvalida;
import fiuba.tdd.tp.model.Excepciones.ZonaLlena;
import fiuba.tdd.tp.model.carta.Carta;
import fiuba.tdd.tp.model.carta.Energia;
import fiuba.tdd.tp.model.jugador.Mazo;
import fiuba.tdd.tp.model.jugador.Tablero;
import fiuba.tdd.tp.model.modo.Modo;
import fiuba.tdd.tp.model.partida.Partida;

@Repository
public class PartidasRepository {

	private final HashMap<Integer, Partida> partidas = new HashMap<>();
	private Integer id = 0;

	private HashMap<String, HashSet<Integer>> solicitudesEnviadas = new HashMap<>();
	private HashMap<String, HashSet<Integer>> solicitudesRecibidas = new HashMap<>();

	public PartidasRepository() {

	}

	public void registrarPartida(Modo modoPartida, String unJugador, String otroJugador, Mazo unMazo, Mazo otroMazo)
			throws PartidaInvalida {
		Partida partida = new Partida(modoPartida, unJugador, otroJugador, unMazo, otroMazo);
		partidas.put(this.id, partida);

		this.agregarSolicitudEnviada(unJugador, this.id);
		this.agregarSolicitudRecibida(otroJugador, this.id);

		this.id += 1;
	}

	public void agregarSolicitudEnviada(String unJugador, Integer idPartida) {
		HashSet<Integer> solicitudesEnviadasJugador = this.solicitudesEnviadas.get(unJugador);
		solicitudesEnviadasJugador.add(this.id);

		this.solicitudesEnviadas.put(unJugador, solicitudesEnviadasJugador);
	}

	public void agregarSolicitudRecibida(String unJugador, Integer idPartida) {
		HashSet<Integer> solicitudesRecibidasJugador = this.solicitudesRecibidas.get(unJugador);
		solicitudesRecibidasJugador.add(this.id);

		this.solicitudesRecibidas.put(unJugador, solicitudesRecibidasJugador);
	}

	public Vector<String> obtenerSolicitudesRecibidas(String jugador) {
		Vector<String> solicitudes = new Vector<String>();
		this.solicitudesRecibidas.get(jugador)
				.forEach(solicitud -> solicitudes.add(this.partidas.get(solicitud).jugadorEnTurno));
		return solicitudes;
	}

	public Tablero tableroJugador(Integer partidaId, String jugador) {
		return this.partidas.get(partidaId).tableroJugador(jugador);
	}

	public Integer puntosDeVida(Integer partidaId, String jugador) throws ModoSinPuntosDeVida {
		return this.partidas.get(partidaId).puntosDeVida(jugador);
	}

	public void terminarEtapa(Integer partidaId) throws MovimientoInvalido {
		this.partidas.get(partidaId).terminarEtapa();
	}

	public Carta invocarCarta(Integer partidaId, String jugador, String carta, String zona)
			throws CartaNoEncontrada, EnergiaInsuficiente, MovimientoInvalido, ZonaLlena {

		return this.partidas.get(partidaId).invocarCarta(jugador, carta, zona);
	}

	public void activarCarta(Integer partidaId, Carta carta, Integer indiceMetodo, String jugadorObjetivo,
			ArrayList<Carta> cartasObjetivos, Energia energia) throws CartaNoActivable, MovimientoInvalido {

		this.partidas.get(partidaId).activarCarta(carta, indiceMetodo, jugadorObjetivo, cartasObjetivos, energia);
	}
}
