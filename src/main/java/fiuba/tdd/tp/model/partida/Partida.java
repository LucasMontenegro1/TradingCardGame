package fiuba.tdd.tp.model.partida;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;

import fiuba.tdd.tp.model.Excepciones.CartaNoActivable;
import fiuba.tdd.tp.model.Excepciones.CartaNoEncontrada;
import fiuba.tdd.tp.model.Excepciones.EnergiaInsuficiente;
import fiuba.tdd.tp.model.Excepciones.ModoSinPuntosDeVida;
import fiuba.tdd.tp.model.Excepciones.MovimientoInvalido;
import fiuba.tdd.tp.model.Excepciones.PartidaInvalida;
import fiuba.tdd.tp.model.Excepciones.ZonaLlena;
import fiuba.tdd.tp.model.carta.Carta;
import fiuba.tdd.tp.model.carta.Energia;
import fiuba.tdd.tp.model.carta.Tipo;
import fiuba.tdd.tp.model.carta.Metodos.MetodoCarta;
import fiuba.tdd.tp.model.jugador.Mazo;
import fiuba.tdd.tp.model.jugador.Tablero;
import fiuba.tdd.tp.model.modo.Modo;
import fiuba.tdd.tp.model.modo.Modo1;
import fiuba.tdd.tp.model.turno.Etapa;
import fiuba.tdd.tp.model.turno.EtapaInicial;
import fiuba.tdd.tp.model.turno.Turno;
import fiuba.tdd.tp.model.zona.ZonaArtefacto;
import fiuba.tdd.tp.model.zona.ZonaCombate;
import fiuba.tdd.tp.model.zona.ZonaReserva;

public class Partida {

    final static String ZonaArtefacto = "ZonaArtefacto";
    final static String ZonaCombate = "ZonaCombate";
    final static String ZonaReserva = "ZonaReserva";

    public String jugadorEnTurno;
    public Tablero tablero1;
    public Tablero tablero2;
    private Turno turno;
    private Modo modo;
    public Deque<Ejecucion> pilaDeEjecucion;
    public ArrayList<Carta> cartasUsadasEnTurno;
    public ArrayList<Carta> artefactosInvocados;
    private String ganador;
    public Integer maxZonaCombate;
    public Integer maxZonaReserva;
    public Integer maxZonaArtefactos;

    public Partida(Modo modoPartida, String unJugador, String otroJugador, Mazo unMazo, Mazo otroMazo) throws PartidaInvalida {
        if (!modoPartida.verificarMazoValido(unMazo.cartas) || !modoPartida.verificarMazoValido(otroMazo.cartas)) {
            throw new PartidaInvalida("Los mazos de los jugadores no son compatibles");
        }

        this.modo = modoPartida;
        this.ganador = null;
        this.ganador = null;
        this.pilaDeEjecucion = null;
		this.jugadorEnTurno = unJugador;
        this.tablero1 = new Tablero(unJugador, unMazo, modoPartida);
        this.tablero2 = new Tablero(otroJugador, otroMazo, modoPartida);
        this.turno = new Turno(modoPartida);
        this.maxZonaReserva = modoPartida.getMaxZonaReserva();
        this.maxZonaArtefactos = modoPartida.getMaxZonaArtefactos();
        this.maxZonaCombate = modoPartida.getMaxZonaCombate();
        this.cartasUsadasEnTurno = new ArrayList<Carta>();
        this.artefactosInvocados = new ArrayList<Carta>();
    }

    public Turno turnoEnProceso() {
        return turno;
    }

    public void setMazo(Mazo mazo){
        this.tablero2.setMazo(mazo);
    }

    public void iniciarPartida() throws MovimientoInvalido, PartidaInvalida {
        tablero1.iniciarTablero();
        tablero2.iniciarTablero();
        
        this.modo.iniciarTableros(tablero1, tablero2);

        this.turno.iniciarTurno(this.tablero1);
        this.turno.pasarDeEtapa();
    }

    public Tablero tableroJugador(String jugador) {
        if (this.tablero1.usuario.equals(jugador)) {
            return this.tablero1;
        } else if (this.tablero2.usuario.equals(jugador)) {
            return this.tablero2;
        }
        return null;
    }

    public Tablero tableroEnemigo(String jugador) {
        if (this.tablero1.usuario.equals(jugador)) {
            return this.tablero2;
        } else if (this.tablero2.usuario.equals(jugador)) {
            return this.tablero1;
        }
        return null;
    }

    public Integer puntosDeVida(String jugador) throws ModoSinPuntosDeVida {
        
        Tablero tablero = tableroJugador(jugador);

        return modo.obtenerPuntosDeVida(tablero);
    }
    
    public Tablero tableroEnTurno() {
        return tableroJugador(jugadorEnTurno);
    }

    public Tablero tableroEnEspera() {
        if (tablero1.usuario == jugadorEnTurno) {
            return tablero2;
        }

        return tablero1;
    }

    public HashMap<Carta, ArrayList<MetodoCarta>> cartasUsables(){
		return tableroEnTurno().cartasUsables(turno.etapaActual(),pilaDeEjecucion, cartasUsadasEnTurno);
	}

    public void terminarEtapa() throws MovimientoInvalido {
        this.turno.pasarDeEtapa();
        cartasUsadasEnTurno.clear();
    
        if (this.turno.etapaActual() == null) {
            if (jugadorEnTurno == tablero1.usuario) {
                this.turno = new Turno(this.modo);
                this.jugadorEnTurno = tablero2.usuario;
            } else {
                this.turno = new Turno(this.modo);
                this.jugadorEnTurno = tablero1.usuario;
            }

            this.artefactosInvocados.clear();
        }

        Tablero tablero = tableroEnTurno();
        this.turno.etapaActual().iniciar(tablero);
        if (this.turno.etapaActual() instanceof EtapaInicial) {
            verificarGanador();
        }
    }

    public boolean jugadorEnTurno(String unJugador) {
        return unJugador.equals(jugadorEnTurno);
    }
    
    private void verificarGanador() {
        this.ganador = modo.calcularGanador(tablero1, tablero2);
    }

    public String ganador() {
        return this.ganador;
    }

    private void invocarPorZona(String zona, Etapa etapaActual, Tablero tablero, Carta cartaEnTablero) throws MovimientoInvalido, ZonaLlena {
        switch (zona) {
            case ZonaArtefacto -> {
                if (tablero.cartasEnZona(ZonaArtefacto.class.getSimpleName()).size() < maxZonaArtefactos) {
                    etapaActual.invocarAZonaDeArtefacto(cartaEnTablero);
                } else {
                    throw new ZonaLlena("No puede agregar más cartas a la zona de artefacto");
                }
            }
            case ZonaCombate -> {
                if (tablero.cartasEnZona(ZonaCombate.class.getSimpleName()).size() < maxZonaCombate) {
                    etapaActual.invocarAZonaDeCombate(cartaEnTablero);
                } else {
                    throw new ZonaLlena("No puede agregar más cartas a la zona de combate");
                }
            }
            case ZonaReserva -> {
                if (tablero.cartasEnZona(ZonaReserva.class.getSimpleName()).size() < maxZonaReserva) {
                    etapaActual.invocarAZonaDeReserva(cartaEnTablero);
                } else {
                    throw new ZonaLlena("No puede agregar más cartas a la zona de reserva");
                }
            }
        }
    }

    public Carta invocarCarta(String jugador, String carta, String zona) throws CartaNoEncontrada, EnergiaInsuficiente, MovimientoInvalido, ZonaLlena {

        if (!jugador.equals(this.jugadorEnTurno)) {
            throw new MovimientoInvalido("No es tu turno");
        }

        Tablero tablero = tableroJugador(jugador);

        Carta cartaEnTablero = tablero.buscarCarta(carta, zona);
        if (cartaEnTablero == null) {
            throw new CartaNoEncontrada("La carta no existe");
        } 

        Etapa etapaActual = turnoEnProceso().etapaActual();

        if (tablero.verificarEnergia(cartaEnTablero)) {
            invocarPorZona(zona, etapaActual, tablero, cartaEnTablero); 
            tablero.disminuirEnergia(Energia.Fuego, cartaEnTablero.costoDeInvocacion.get(Energia.Fuego));
            tablero.disminuirEnergia(Energia.Agua, cartaEnTablero.costoDeInvocacion.get(Energia.Agua));
            tablero.disminuirEnergia(Energia.Planta, cartaEnTablero.costoDeInvocacion.get(Energia.Planta));
        }

        if (cartaEnTablero.esTipo(Tipo.Accion)) {   
            artefactosInvocados.add(cartaEnTablero);
        }
        
        return cartaEnTablero;
    }

    public void activarCarta(Carta carta, Integer indiceMetodo, String jugadorObjetivo, ArrayList<Carta> cartasObjetivos, Energia energia) throws CartaNoActivable, MovimientoInvalido {        
        Etapa etapa = turnoEnProceso().etapaActual();
        Tablero tablero = tableroEnTurno();
        Tablero tableroContrincante = tableroEnEspera();
        HashMap<Carta, ArrayList<MetodoCarta>> cartas = null;
        
        if (tablero.cartas.contains(carta)) {
            cartas = tablero.cartasUsables(etapa, pilaDeEjecucion, cartasUsadasEnTurno); 
        } else if (tableroContrincante.cartas.contains(carta) && carta.esTipo(Tipo.Reaccion)) {
            cartas = tableroContrincante.cartasUsables(etapa, pilaDeEjecucion, cartasUsadasEnTurno); 
            tableroContrincante = tableroEnTurno();
            tablero = tableroEnEspera();
        } else {
            throw new MovimientoInvalido("No podes activar esta carta ahora");
        }

        if (cartas != null && cartas.containsKey(carta) && !(cartasUsadasEnTurno.contains(carta))) {

            MetodoCarta metodo = cartas.get(carta).get(indiceMetodo);
            if (pilaDeEjecucion != null) {
                Ejecucion nuevaEjecucion = new Ejecucion(metodo, tablero, tableroContrincante, pilaDeEjecucion, jugadorObjetivo, cartasObjetivos, carta, energia);
                pilaDeEjecucion.push(nuevaEjecucion);
            } else {
                metodo.ejecutar(tablero, tableroContrincante, pilaDeEjecucion, jugadorObjetivo, cartasObjetivos, carta, energia);
            }

            cartasUsadasEnTurno.add(carta);
        } else {
            throw new CartaNoActivable("Esta carta no está disponible en este momento");
        }

        if (modo instanceof Modo1) {
            this.ganador = modo.calcularGanador(tablero, tableroContrincante);
        }
    }

    public void iniciarPilaDeEjecucion() {
        this.pilaDeEjecucion = new ArrayDeque<>();
    }
    
    public void ejecutarPila() throws MovimientoInvalido {
        
        while (!pilaDeEjecucion.isEmpty()) {
            pilaDeEjecucion.pop().ejecutar();
        }

        pilaDeEjecucion = null;
    }
}