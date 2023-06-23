package fiuba.tdd.tp.controller;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fiuba.tdd.tp.model.Excepciones.CartaNoActivable;
import fiuba.tdd.tp.model.Excepciones.CartaNoEncontrada;
import fiuba.tdd.tp.model.Excepciones.EnergiaInsuficiente;
import fiuba.tdd.tp.model.Excepciones.ModoSinPuntosDeVida;
import fiuba.tdd.tp.model.Excepciones.MovimientoInvalido;
import fiuba.tdd.tp.model.Excepciones.PartidaInvalida;
import fiuba.tdd.tp.model.Excepciones.ZonaLlena;
import fiuba.tdd.tp.model.carta.Carta;
import fiuba.tdd.tp.model.carta.Metodos.MetodoCarta;
import fiuba.tdd.tp.model.jugador.Jugador;
import fiuba.tdd.tp.model.jugador.Mazo;
import fiuba.tdd.tp.model.jugador.Tablero;
import fiuba.tdd.tp.model.partida.Partida;
import fiuba.tdd.tp.repository.JugadoresRepository;
import fiuba.tdd.tp.repository.PartidasRepository;
import fiuba.tdd.tp.service.ActivacionDeCarta;
import fiuba.tdd.tp.service.InvocacionDeCarta;
import fiuba.tdd.tp.service.JwtService;
import fiuba.tdd.tp.service.PartidaEnEspera;
import fiuba.tdd.tp.service.SolicitudDePartida;

@RestController
@RequestMapping("/api/partidas")
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
	@PostMapping("/solicitar/{modoPartida}")
	public ResponseEntity<String> solicitarPartida(
			@RequestHeader("Authorization") String authorizationHeader,
			@RequestBody SolicitudDePartida solicitudDePartida,
			@PathVariable Integer modoPartida
		) {

		try {
			String jugador = solicitador(authorizationHeader);
			if (!jugador.equals(solicitudDePartida.contrincante())) {
				this.repositorioPartidas.registrarPartida(modoPartida, jugador, solicitudDePartida.contrincante(), solicitudDePartida.mazo());
			} else {
				return ResponseEntity.ok("No podes jugar con vos mismo!");
			}
		} catch (Exception e) {
			return ResponseEntity.ok(e.getMessage());
		}

		return ResponseEntity.ok("Partida solicitada!");
	}

	@GetMapping("/solicitudes")
	public List<String> obtenerSolicitudesRecibidas(@RequestHeader("Authorization") String authorizationHeader) {
		String jugador = solicitador(authorizationHeader);
		return this.repositorioPartidas.obtenerSolicitudesRecibidas(jugador);
	}

	@PostMapping("/aceptarPartida")
	public ResponseEntity<String> aceptarPartida(
			@RequestHeader("Authorization") String authorizationHeader,
			@RequestBody SolicitudDePartida solicitudDePartida
		) throws MovimientoInvalido, PartidaInvalida {

		String nombreJugador = solicitador(authorizationHeader);
		Optional<Jugador> unJugador = this.repositorioJugadores.buscarPorUsername(nombreJugador);
		if (unJugador.isPresent()) {
			Jugador jugador = unJugador.get();
			Mazo mazo = jugador.getMazo(solicitudDePartida.mazo());
			if (mazo == null) {
				return ResponseEntity.ok("Mazo no encontrado");
			}
			PartidaEnEspera partidaEnEspera = this.repositorioPartidas.buscarPartida(solicitudDePartida.contrincante(), nombreJugador);
			try {
				this.repositorioPartidas.ponerPartidaEnJuego(partidaEnEspera, mazo);
			} catch (Exception e) {
				return ResponseEntity.ok(e.getMessage());
			}
		}

		return ResponseEntity.ok("Partida aceptada!");
	}
	
	@PostMapping("/iniciar")
	public ResponseEntity<String> iniciarPartida(@RequestHeader("Authorization") String authorizationHeader) throws MovimientoInvalido, PartidaInvalida {
		String jugador = solicitador(authorizationHeader);
		Partida partida = this.repositorioPartidas.buscarPartidaEnJuego(jugador);
		try {
			partida.iniciarPartida();
		} catch (Exception e){
			return ResponseEntity.ok(e.getMessage());
		}
		return ResponseEntity.ok("Partida iniciada!");
	}

	@GetMapping("/puntos")
	public Integer getPuntosDeVida(@RequestHeader("Authorization") String authorizationHeader)
			throws ModoSinPuntosDeVida {

		String jugador = solicitador(authorizationHeader);
		Partida partida = this.repositorioPartidas.buscarPartidaEnJuego(jugador);
		
		return partida.tableroJugador(jugador).puntos;
	}

	@GetMapping("/ganador")
	public String consultarGanador(@RequestHeader("Authorization") String authorizationHeader) {
		String jugador = solicitador(authorizationHeader);
		Partida partida = this.repositorioPartidas.buscarPartidaEnJuego(jugador);
		return partida.ganador();
	}

	@GetMapping("/turno")
	public String getJugadorEnTurno(@RequestHeader("Authorization") String authorizationHeader) {

		String jugador = solicitador(authorizationHeader);
		Partida partida = this.repositorioPartidas.buscarPartidaEnJuego(jugador);
		return partida.jugadorEnTurno;
	}

	@GetMapping("/energia")
	public String getEnergiasJugador(@RequestHeader("Authorization") String authorizationHeader) {

		String jugador = solicitador(authorizationHeader);
		Tablero tablero = this.repositorioPartidas.tableroJugador(jugador);
		if (tablero != null) {
			return String.format("Agua: %d, Fuego: %d, Planta: %d", tablero.energiaAgua(), tablero.energiaFuego(), tablero.energiaPlanta());
		}
		return "";
	}
	
	@GetMapping("/energiaEnemigo")
	public String getEnergiasEnemigo(@RequestHeader("Authorization") String authorizationHeader) {

		String jugador = solicitador(authorizationHeader);
		Tablero tablero = this.repositorioPartidas.tableroEnemigo(jugador);

		if (tablero != null) {
			return String.format("Agua: %d, Fuego: %d, Planta: %d", tablero.energiaAgua(), tablero.energiaFuego(), tablero.energiaPlanta());
		}
		return "";
	}
	
	private HashMap<String, ArrayList<HashMap<String, Integer>>> serializar(Tablero tablero) {
		HashMap<String, ArrayList<HashMap<String, Integer>>> tableroEnJuego = new HashMap<>();
		tableroEnJuego.put("ZonaMano", new ArrayList<>());
		tableroEnJuego.put("ZonaArtefacto", new ArrayList<>());
		tableroEnJuego.put("ZonaCombate", new ArrayList<>());
		tableroEnJuego.put("ZonaDescarte", new ArrayList<>());
		tableroEnJuego.put("ZonaReserva", new ArrayList<>());

		ArrayList<HashMap<String, Integer>> cartasEnZona;
		Integer i = 0;
		for (Carta carta : tablero.cartas) {
			if (carta.zona != null) {
				cartasEnZona = tableroEnJuego.get(carta.zona.getClass().getSimpleName());
				HashMap<String, Integer> unaCarta = new HashMap<>();
				unaCarta.put(String.format("%d: %s", i, carta.nombre), carta.hp);
				cartasEnZona.add(unaCarta);
			}
			i++;
		}

		return tableroEnJuego;
	}

	@GetMapping("/tablero")
	public HashMap<String, ArrayList<HashMap<String, Integer>>> getTablero(@RequestHeader("Authorization") String authorizationHeader) {
		String jugador = solicitador(authorizationHeader);
		Tablero tablero = this.repositorioPartidas.tableroJugador(jugador);
		return serializar(tablero);
	}

	@GetMapping("/tableroEnemigo")
	public HashMap<String, ArrayList<HashMap<String, Integer>>> getTableroEnemigo(@RequestHeader("Authorization") String authorizationHeader) {
		String jugador = solicitador(authorizationHeader);
		Tablero tablero = this.repositorioPartidas.tableroEnemigo(jugador);
		HashMap<String, ArrayList<HashMap<String, Integer>>> tableroEnemigo = serializar(tablero);
		tableroEnemigo.remove("ZonaMano");
		return tableroEnemigo;
	}

	@PostMapping("/invocarCarta")
	public ResponseEntity<String> invocarCarta(
			@RequestHeader("Authorization") String authorizationHeader,
			@RequestBody InvocacionDeCarta invocacionDeCarta
		) throws CartaNoEncontrada, EnergiaInsuficiente, MovimientoInvalido, ZonaLlena {

		String jugador = solicitador(authorizationHeader);

		try {
			this.repositorioPartidas.invocarCarta(jugador, invocacionDeCarta.carta(), invocacionDeCarta.zona());
			return ResponseEntity.ok("Carta invocada");
		} catch (Exception e) {
			return ResponseEntity.ok(e.getMessage());
		}
		
	}

	@PostMapping("/terminarEtapa")
	public ResponseEntity<String> terminarEtapa(@RequestHeader("Authorization") String authorizationHeader) throws MovimientoInvalido {

		String jugador = solicitador(authorizationHeader);
		Partida partida = this.repositorioPartidas.buscarPartidaEnJuego(jugador);
		if (partida.jugadorEnTurno.equals(jugador)) {
			partida.terminarEtapa();
			return ResponseEntity.ok(partida.turnoEnProceso().etapaActual().getClass().getSimpleName());
		}

		return ResponseEntity.ok("No podes pasar de turno");
	}

	@GetMapping("/etapa")
	public String getEtapa(@RequestHeader("Authorization") String authorizationHeader) {

		String jugador = solicitador(authorizationHeader);
		Partida partida = this.repositorioPartidas.buscarPartidaEnJuego(jugador);
		if (partida.turnoEnProceso().etapaActual() == null) {
			return "EtapaFinal";
		}
		return partida.turnoEnProceso().etapaActual().getClass().getSimpleName();
	}

	private HashMap<String, ArrayList<Integer>> serializarCartasUsables(HashMap<Carta, ArrayList<MetodoCarta>> cartasUsables){
		HashMap<String, ArrayList<Integer>> result = new HashMap<>();
		for (Carta carta : cartasUsables.keySet()){
			ArrayList<Integer> numbers = new ArrayList<>();
			Integer method_len = cartasUsables.get(carta).size();
			for (int i = 0; i < method_len; i++) {numbers.add(i);}
			result.put(carta.nombreCarta(), numbers);
		}
		return result;
	}

	@GetMapping("/cartasUsables")
	public HashMap<String, ArrayList<Integer>> getCartasUsables(@RequestHeader("Authorization") String authorizationHeader) {

		String jugador = solicitador(authorizationHeader);
		Partida partida = this.repositorioPartidas.buscarPartidaEnJuego(jugador);
		HashMap<Carta, ArrayList<MetodoCarta>> cartasUsables = partida.cartasUsables();
		return serializarCartasUsables(cartasUsables);
	}

	@GetMapping("/cartasAtacables")
	public ArrayList<HashMap<String, Integer>> getCartasAtacables(@RequestHeader("Authorization") String authorizationHeader) {

		String jugador = solicitador(authorizationHeader);
		Partida partida = this.repositorioPartidas.buscarPartidaEnJuego(jugador);
		Tablero tableroEnemigo = partida.tableroEnemigo(jugador);
		ArrayList<Carta> cartasAtacables = tableroEnemigo.cartasAtacables();

		ArrayList<HashMap<String, Integer>> cartas = new ArrayList<>();
		if (cartasAtacables != null) {
			for (Carta carta : cartasAtacables) {
				HashMap<String, Integer> unaCarta = new HashMap<>();
				unaCarta.put(carta.nombre, carta.hp);
				cartas.add(unaCarta);
			}
		}

		return cartas;
	}

	@PostMapping("/activarCarta")
	public void activarCarta(
			@RequestHeader("Authorization") String authorizationHeader,
			@RequestBody ActivacionDeCarta activacionDeCarta
		) throws CartaNoActivable, MovimientoInvalido {

		String jugador = solicitador(authorizationHeader);

		this.repositorioPartidas.activarCarta(jugador, activacionDeCarta.carta(),
				activacionDeCarta.indiceMetodo(), activacionDeCarta.jugadorObjetivo(),
				activacionDeCarta.cartasObjetivos(), activacionDeCarta.energia());
	}

	@PostMapping("/iniciarPila")
	public void iniciarPilaDeEjecucion(@RequestHeader("Authorization") String authorizationHeader) {
		String jugador = solicitador(authorizationHeader);
		Partida partida = this.repositorioPartidas.buscarPartidaEnJuego(jugador);
		partida.iniciarPilaDeEjecucion();
	}

	@PostMapping("/ejecutarPila")
	public void ejecutarPila(@RequestHeader("Authorization") String authorizationHeader) throws MovimientoInvalido {
		String jugador = solicitador(authorizationHeader);
		Partida partida = this.repositorioPartidas.buscarPartidaEnJuego(jugador);
		partida.ejecutarPila();
	}

	private void avanzarEtapa(Partida partida, String jugador, String etapa) throws MovimientoInvalido {
        partida.terminarEtapa();
        while (!partida.jugadorEnTurno(jugador) || !(partida.turnoEnProceso().etapaActual().getClass().getSimpleName().equals(etapa))) {
            partida.terminarEtapa();
        }
    }

	@PostMapping("/moverAEtapa/{jugador}/{etapa}")
	public void moverAEtapa(@RequestHeader("Authorization") String authorizationHeader,
		@PathVariable String jugador,
		@PathVariable String etapa
	) throws MovimientoInvalido {
		String unJugador = solicitador(authorizationHeader);
		Partida partida = this.repositorioPartidas.buscarPartidaEnJuego(unJugador);
		avanzarEtapa(partida, jugador, etapa);
	}

	@PostMapping("/reordenarMazo")
	public ResponseEntity<String> reordenarMazo(
		@RequestHeader("Authorization") String authorizationHeader,
		@RequestBody ArrayList<String> listaOrdenada
	) throws CartaNoEncontrada {
		String unJugador = solicitador(authorizationHeader);
		Partida partida = this.repositorioPartidas.buscarPartidaEnJuego(unJugador);

		Tablero tablero = partida.tableroJugador(unJugador);
		try {
			tablero.reordenarMazo(listaOrdenada);
		} catch (Exception e) {
			return ResponseEntity.ok(e.getMessage());
		}
		
		return ResponseEntity.ok("Mazo reordenado");
	}
}