package fiuba.tdd.tp.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import fiuba.tdd.tp.model.Excepciones.CartaNoActivable;
import fiuba.tdd.tp.model.Excepciones.CartaNoEncontrada;
import fiuba.tdd.tp.model.Excepciones.EnergiaInsuficiente;
import fiuba.tdd.tp.model.Excepciones.MovimientoInvalido;
import fiuba.tdd.tp.model.Excepciones.PartidaInvalida;
import fiuba.tdd.tp.model.Excepciones.ZonaLlena;
import fiuba.tdd.tp.model.carta.Carta;
import fiuba.tdd.tp.model.jugador.Jugador;
import fiuba.tdd.tp.model.jugador.Mazo;
import fiuba.tdd.tp.model.jugador.Tablero;
import fiuba.tdd.tp.model.modo.Modo;
import fiuba.tdd.tp.model.modo.Modo1;
import fiuba.tdd.tp.model.modo.Modo2;
import fiuba.tdd.tp.model.partida.Partida;
import fiuba.tdd.tp.service.PartidaEnEspera;

@Repository
public class PartidasRepository {

	private final JugadoresRepository repositorio;
	private final List<Partida> partidasEnJuego = new ArrayList<>();
	private final HashMap<String, List<PartidaEnEspera>> partidasEnEspera = new HashMap<>();

	private final Integer MODO1 = 1;
	private final Integer MODO2 = 2;

	public PartidasRepository(JugadoresRepository repositorioJugadores) {
		this.repositorio = repositorioJugadores;
	}

	public void registrarPartida(Integer nroModoPartida, String unJugador, String otroJugador, String nombreUnMazo)
			throws PartidaInvalida {

		Optional<Jugador> jugador = repositorio.buscarPorUsername(unJugador);
		Modo modoPartida = null;
		if (nroModoPartida == MODO1) {
			modoPartida = new Modo1();
		} else if (nroModoPartida == MODO2) {
			modoPartida = new Modo2();
		}
		Mazo unMazo = jugador.get().getMazo(nombreUnMazo);
		
		PartidaEnEspera partida = new PartidaEnEspera(modoPartida, unJugador, otroJugador, unMazo);

		if (!partidasEnEspera.containsKey(otroJugador)) {
			ArrayList<PartidaEnEspera> partidas = new ArrayList<>();
			this.partidasEnEspera.put(otroJugador, partidas); 
		}
		partidasEnEspera.get(otroJugador).add(partida);
	}

	public List<String> obtenerSolicitudesRecibidas(String jugador) {
		List<String> solicitantes = new ArrayList<>();
		List<PartidaEnEspera> partidas = this.partidasEnEspera.get(jugador);
		if (partidas != null) {
			for (PartidaEnEspera partida : partidas) {
				solicitantes.add(partida.getJugador());
			}
		}

		return solicitantes;
	}

	public PartidaEnEspera buscarPartida(String jugador, String contrincante) {

		for (PartidaEnEspera partida : this.partidasEnEspera.get(contrincante)) {
			if (partida.getJugador().equals(jugador)) {
				return partida;
			}
		}

		return null;
	}

	public Partida buscarPartidaEnJuego(String jugador) {

		for (Partida partida : partidasEnJuego) {
			if (partida.jugadorEnTurno.equals(jugador) || partida.tableroEnEspera().usuario.equals(jugador)) {
				return partida;
			}
		}

		return null;
	}

	public Tablero tableroJugador(String jugador) {
		Partida partida = buscarPartidaEnJuego(jugador);
		return partida.tableroJugador(jugador);
	}

	public Carta invocarCarta(String unJugador, String unaCarta, String unaZona)
			throws CartaNoEncontrada, EnergiaInsuficiente, MovimientoInvalido, ZonaLlena {
				
		Optional<Jugador> posibleJugador = repositorio.buscarPorUsername(unJugador);
		if (posibleJugador.isPresent()) {
			Partida partida = buscarPartidaEnJuego(unJugador);		
			return partida.invocarCarta(unJugador, unaCarta, unaZona);
		}

		return null;
	}

	public void activarCarta(String unJugador, String unaCarta, Integer indiceMetodo, String unJugadorObjetivo,
			ArrayList<String> unasCartasObjetivos, String energia) throws CartaNoActivable, MovimientoInvalido {
		
		// Partida partida = buscarPartidaEnJuego(unJugador);
		// Tablero tablero = partida.tableroJugador(unJugador);
		
		
		// List<Carta> cartasObjetivos = new ArrayList<>();
		// for (String cartaObjetivo : unasCartasObjetivos) {
		// 	cartasObjetivos.add(tablero.buscarCarta(cartaObjetivo));
		// }
		
		// partida.activarCarta(carta, indiceMetodo, unJugadorObjetivo, cartasObjetivos, energia);
	}

	public Partida ponerPartidaEnJuego(PartidaEnEspera partidaEnEspera, Mazo mazo) throws PartidaInvalida {
		String contrincante = partidaEnEspera.getContrincante();
		this.partidasEnEspera.get(contrincante).remove(partidaEnEspera);
		
		Partida partida = new Partida(partidaEnEspera.getModo(), partidaEnEspera.getJugador(), contrincante, partidaEnEspera.getMazo(), mazo);
		this.partidasEnJuego.add(partida);

		return partida;
	}
}
