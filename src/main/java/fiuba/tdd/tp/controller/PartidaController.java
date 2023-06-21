package fiuba.tdd.tp.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Vector;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
import fiuba.tdd.tp.model.carta.Energia;
import fiuba.tdd.tp.model.jugador.Tablero;
import fiuba.tdd.tp.model.partida.Partida;
import fiuba.tdd.tp.repository.PartidasRepository;

@RestController
@RequestMapping("/api/partida")
public class PartidaController {

	public PartidasRepository repositorio;

	@ResponseStatus(HttpStatus.OK)
	@PostMapping
	public void solicitarPartida() {
		try {
			this.repositorio.registrarPartida(null, null, null, null, null);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error creando la Partida");
		}
	}

	@GetMapping("/solicitudes/{jugador}")
	public Vector<String> obtenerSolicitudesRecibidas(@PathVariable("jugador") String jugador) {
		return this.repositorio.obtenerSolicitudesRecibidas(jugador);
	}

	@PostMapping("/aceptarPartida")
	public void aceptarPartida(@RequestBody String jugador) throws MovimientoInvalido {
		//;
	}


	@GetMapping("/tablero/{partida}/{jugador}")
	public Tablero getTablero(@PathVariable("partida") Integer partida, @PathVariable("jugador") String jugador) {
		return this.repositorio.tableroJugador(partida, jugador);
	}

	@GetMapping("/puntosDeVida/{partida}/{jugador}")
	public Integer getPuntosDeVida(@PathVariable("partida") Integer partida, @PathVariable("jugador") String jugador) throws ModoSinPuntosDeVida {
		return this.repositorio.puntosDeVida(partida, jugador);
	}

	@PostMapping("/terminarEtapa/{partida}")
	public void terminarEtapa(@PathVariable("partida") Integer partida) throws MovimientoInvalido {
		this.repositorio.terminarEtapa(partida);
	}

	@PostMapping("/invocarCarta/{partida}")
	public Carta invocarCarta(@PathVariable("partida") Integer partida, @RequestBody String jugador, @RequestBody String carta, @RequestBody String zona)
			throws CartaNoEncontrada, EnergiaInsuficiente, MovimientoInvalido, ZonaLlena {

		return this.repositorio.invocarCarta(partida, jugador, carta, zona);
	}

	@PostMapping("/activarCarta/{partida}")
	public void activarCarta(
			@PathVariable("partida") Integer partida,
			@RequestBody Carta carta,
			@RequestBody Integer indiceMetodo,
			@RequestBody String jugadorObjetivo,
			@RequestBody ArrayList<Carta> cartasObjetivos,
			@RequestBody Energia energia)
			throws CartaNoActivable, MovimientoInvalido {

		this.repositorio.activarCarta(partida, carta, indiceMetodo, jugadorObjetivo, cartasObjetivos, energia);
	}

}
