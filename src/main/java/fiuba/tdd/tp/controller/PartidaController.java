package fiuba.tdd.tp.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import fiuba.tdd.tp.model.Excepciones.CartaNoActivable;
import fiuba.tdd.tp.model.Excepciones.CartaNoEncontrada;
import fiuba.tdd.tp.model.Excepciones.EnergiaInsuficiente;
import fiuba.tdd.tp.model.Excepciones.ModoSinPuntosDeVida;
import fiuba.tdd.tp.model.Excepciones.MovimientoInvalido;
import fiuba.tdd.tp.model.Excepciones.ZonaLlena;
import fiuba.tdd.tp.model.carta.Carta;
import fiuba.tdd.tp.model.jugador.Tablero;
import fiuba.tdd.tp.model.partida.Partida;
import fiuba.tdd.tp.repository.JugadoresRepository;
import fiuba.tdd.tp.repository.PartidasRepository;
import fiuba.tdd.tp.service.ActivacionDeCarta;
import fiuba.tdd.tp.service.InvocacionDeCarta;
import fiuba.tdd.tp.service.JwtService;
import fiuba.tdd.tp.service.SolicitudDePartida;

@RestController
@RequestMapping("/api/partida")
public class PartidaController {

	public PartidasRepository repositorioPartidas;
	public JugadoresRepository repositorioJugadores;
	private final JwtService jwtService;

	public PartidaController(PartidasRepository repositorioPartidas, JugadoresRepository repositorioJugadores,
			JwtService jwtService) {
		this.repositorioPartidas = repositorioPartidas;
		this.repositorioJugadores = repositorioJugadores;
		this.jwtService = jwtService;
	}

	private String solicitador(String authorizationHeader) {
		String jwt = authorizationHeader.substring(7);
		return jwtService.extractUsername(jwt);
	}

	@ResponseStatus(HttpStatus.OK)
	@PostMapping("/solicitar")
	public void solicitarPartida(@RequestHeader("Authorization") String authorizationHeader,
			@RequestBody SolicitudDePartida solicitudDePartida) {
		try {
			String jugador = solicitador(authorizationHeader);
			this.repositorioPartidas.registrarPartida(solicitudDePartida.modoPartida(), jugador,
					solicitudDePartida.otroJugador(), solicitudDePartida.unMazo(), solicitudDePartida.otroMazo());
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error creando la Partida");
		}
	}

	@GetMapping("/solicitudes")
	public List<String> obtenerSolicitudesRecibidas(@RequestHeader("Authorization") String authorizationHeader) {
		String jugador = solicitador(authorizationHeader);
		return this.repositorioPartidas.obtenerSolicitudesRecibidas(jugador);
	}

	@PostMapping("/aceptarPartida/{contrincante}")
	public void aceptarPartida(@RequestHeader("Authorization") String authorizationHeader,
			@PathVariable String contricante)
			throws MovimientoInvalido {

		String jugador = solicitador(authorizationHeader);
		Partida partida = this.repositorioPartidas.buscarPartida(contricante, jugador);
		partida.iniciarPartida();
	}

	@GetMapping("/tablero")
	public Tablero getTablero(@RequestHeader("Authorization") String authorizationHeader) {

		String jugador = solicitador(authorizationHeader);
		return this.repositorioPartidas.tableroJugador(jugador);
	}

	@GetMapping("/puntos")
	public Integer getPuntosDeVida(@RequestHeader("Authorization") String authorizationHeader)
			throws ModoSinPuntosDeVida {

		String jugador = solicitador(authorizationHeader);
		Partida partida = this.repositorioPartidas.buscarPartidaEnJuego(jugador);
		return partida.tableroJugador(jugador).puntos;
	}

	@PostMapping("/terminarEtapa")
	public void terminarEtapa(@RequestHeader("Authorization") String authorizationHeader) throws MovimientoInvalido {

		String jugador = solicitador(authorizationHeader);
		Partida partida = this.repositorioPartidas.buscarPartidaEnJuego(jugador);
		partida.terminarEtapa();
	}

	@PostMapping("/invocarCarta")
	public Carta invocarCarta(@RequestHeader("Authorization") String authorizationHeader,
			@RequestBody InvocacionDeCarta invocacionDeCarta)
			throws CartaNoEncontrada, EnergiaInsuficiente, MovimientoInvalido, ZonaLlena {

		String jugador = solicitador(authorizationHeader);
		return this.repositorioPartidas.invocarCarta(jugador,
				invocacionDeCarta.carta(), invocacionDeCarta.zona());
	}

	@PostMapping("/activarCarta")
	public void activarCarta(@RequestHeader("Authorization") String authorizationHeader,
			@RequestBody ActivacionDeCarta activacionDeCarta) throws CartaNoActivable, MovimientoInvalido {

		String jugador = solicitador(authorizationHeader);
		this.repositorioPartidas.activarCarta(jugador, activacionDeCarta.carta(),
				activacionDeCarta.indiceMetodo(), activacionDeCarta.jugadorObjetivo(),
				activacionDeCarta.cartasObjetivos(), activacionDeCarta.energia());
	}

}
