package fiuba.tdd.tp.acceptance;


import fiuba.tdd.tp.Excepciones.CartaNoEncontrada;
import fiuba.tdd.tp.Excepciones.DineroInsuficiente;
import fiuba.tdd.tp.Excepciones.EnergiaInsuficiente;
import fiuba.tdd.tp.Excepciones.MazoExistente;
import fiuba.tdd.tp.Excepciones.PartidaInvalida;
import fiuba.tdd.tp.carta.Carta;
import fiuba.tdd.tp.carta.CartasDisponibles;
import fiuba.tdd.tp.driver.*;
import fiuba.tdd.tp.etapa.Etapa;
import fiuba.tdd.tp.jugador.Jugador;
import fiuba.tdd.tp.jugador.Mazo;
import fiuba.tdd.tp.modo.Modo;
import fiuba.tdd.tp.modo.Modo1;
import fiuba.tdd.tp.modo.Modo2;
import fiuba.tdd.tp.partida.Partida;
import fiuba.tdd.tp.tablero.Tablero;

import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@SpringBootTest
public class AcceptanceTestRoot<Account, Card> {

    final static String ZonaArtefacto = "ZonaArtefacto";
    final static String ZonaCombate = "ZonaCombate";
    final static String ZonaReserva = "ZonaReserva";

    static Map<DriverCardName, CartasDisponibles> mapCartas = new HashMap<>();
        static {
            mapCartas.put(DriverCardName.WaterEnergy, CartasDisponibles.AGUA);
            mapCartas.put(DriverCardName.FireEnergy, CartasDisponibles.FUEGO);
            mapCartas.put(DriverCardName.PlantEnergy, CartasDisponibles.PLANTA);
            mapCartas.put(DriverCardName.Alchemist, CartasDisponibles.ALQUIMISTA);
            mapCartas.put(DriverCardName.Antimagic, CartasDisponibles.ANTIMAGIA);
            mapCartas.put(DriverCardName.MagicBarrier, CartasDisponibles.BARRERAMAGICA);
            mapCartas.put(DriverCardName.Corrosion, CartasDisponibles.CORROSION);
            mapCartas.put(DriverCardName.Drain, CartasDisponibles.DRENAR);
            mapCartas.put(DriverCardName.MagicSword, CartasDisponibles.ESPADAMAGICA);
            mapCartas.put(DriverCardName.Goblin, CartasDisponibles.GOBLIN);
            mapCartas.put(DriverCardName.Hospital, CartasDisponibles.HOSPITAL);
            mapCartas.put(DriverCardName.BlockReaction, CartasDisponibles.IMPEDIR);
            mapCartas.put(DriverCardName.Inventor, CartasDisponibles.INVENTOR);
            mapCartas.put(DriverCardName.Orc, CartasDisponibles.ORCO);
            mapCartas.put(DriverCardName.Recycle, CartasDisponibles.RECICLAR);
            mapCartas.put(DriverCardName.Resonance, CartasDisponibles.RESONANCIA);
            mapCartas.put(DriverCardName.Saboteur, CartasDisponibles.SABOTEAR);
            mapCartas.put(DriverCardName.Sacrifice, CartasDisponibles.SACRIFICIO);
            mapCartas.put(DriverCardName.MagicDrill, CartasDisponibles.TALADROMAGICO);
            mapCartas.put(DriverCardName.Treason, CartasDisponibles.TRAICION);
        }

        static Map<DriverGameMode, Modo> mapModo = new HashMap<>();
        static {
            mapModo.put(DriverGameMode.HitpointLoss, new Modo1());
            mapModo.put(DriverGameMode.CreatureSlayer, new Modo2());
        }

        static Map<DriverMatchSide, String> mapJugador = new HashMap<>();
        static {
            mapJugador.put(DriverMatchSide.Blue, "blue");
            mapJugador.put(DriverMatchSide.Green, "green");
        }

        static Map<DriverTurnPhase, String> mapFase = new HashMap<>();
        static {
            mapFase.put(DriverTurnPhase.Initial, "EtapaInicial");
            mapFase.put(DriverTurnPhase.Main, "EtapaPrincipal");
            mapFase.put(DriverTurnPhase.Attack, "EtapaDeAtaque");
            mapFase.put(DriverTurnPhase.End, null);
        }

        static Map<DriverActiveZone, String> mapZona = new HashMap<>();
        static {
            mapZona.put(DriverActiveZone.Artifact, ZonaArtefacto);
            mapZona.put(DriverActiveZone.Combat, ZonaCombate);
            mapZona.put(DriverActiveZone.Reserve, ZonaReserva);
        }

    protected Driver<Jugador, Carta> testDriver = new Driver<Jugador, Carta>() {

        @Override
        public Jugador newAccount() {
            return new Jugador("jugador", "contra");
        }

        @Override
        public int countCards(Jugador account, DriverCardName cardName) {
            HashMap<String, Integer> cartas = account.getCartas();
            String nombre = mapCartas.get(cardName).nombre;
            return Objects.requireNonNullElse(cartas.get(nombre), 0);
        }

        @Override
        public int availableCurrency(Jugador account) {
            return account.getCantdDinero();
        }

        @Override
        public void addCurrency(Jugador account, int amount) {
            account.depositarDinero(amount);
        }

        @Override
        public void buyCards(Jugador account, DriverCardName cardName, int amount) {
            CartasDisponibles carta = mapCartas.get(cardName);

            for(int i = 0; i < amount; i++) {
                try {
                    account.comprarCarta(carta);
                } catch (DineroInsuficiente e) {
                    throw new RuntimeException(e);
                }
            }
        }

        @Override
        public int countDeckCards(Jugador account, String deckName, DriverCardName cardName) {
            HashMap<String, Mazo> mazos = account.getMazos();
            Mazo mazo = mazos.get(deckName);
            
            return mazo.cantCartaEspecifica(mapCartas.get(cardName).nombre);
        }

        @Override
        public void addDeckCards(Jugador account, String deckName, DriverCardName cardName, int amount) {
            if (cardName == null) {
                return;
            } 
            
            String nombreCarta = mapCartas.get(cardName).nombre;
            
            if (!account.getMazos().containsKey(deckName)) {
                HashMap<String, Integer> cartas = new HashMap<>();
                cartas.put(nombreCarta, amount);
                
                Mazo mazo = new Mazo(cartas);

                try {
                    account.agregarMazo(deckName, mazo);
                } catch (MazoExistente | CartaNoEncontrada e) {
                    throw new RuntimeException(e);
                }
            } else {
                Mazo mazo = account.getMazos().get(deckName);
                mazo.agregarCarta(nombreCarta, amount);
            }
        }

        @Override
        public MatchDriver<Carta> startMatch(DriverGameMode mode, Jugador blue, String blueDeck, Jugador green, String greenDeck) {
        
            Mazo mazoJugadorBlue = blue.getMazos().get(blueDeck);
            Mazo mazoJugadorGreen = green.getMazos().get(greenDeck);

            blue.modificarNombre("blue");
            green.modificarNombre("green");

            Partida partida;

            try {
                partida = new Partida(mapModo.get(mode), blue.nombreJugador(), green.nombreJugador(), mazoJugadorBlue, mazoJugadorGreen);             
            } catch (PartidaInvalida e) {
                throw new RuntimeException(e);
            }
            
            matchDriver.nuevaPartida(partida);

            return matchDriver;
        }
    };

    protected MatchDriver<Carta> matchDriver = new MatchDriver<Carta>() {

        private Partida partida;

        @Override
        public void nuevaPartida(Partida unaPartida) {
            this.partida = unaPartida;
        }

        @Override
        public List<DriverCardName> deckOrder(DriverMatchSide player) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'deckOrder'");
        }

        @Override
        public void forceDeckOrder(DriverMatchSide player, List<DriverCardName> cards) {

            ArrayList<String> cartas = new ArrayList<>();

            for (DriverCardName card : cards) {
                cartas.add(mapCartas.get(card).nombre);
            }

            Tablero tableroJugador = partida.tableroJugador(mapJugador.get(player));

            try {
                tableroJugador.reordenarMazo(cartas);
            } catch (CartaNoEncontrada e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public void start() {
            partida.iniciarPartida();
        }

        private boolean mismaEtapa(String nombreEtapa, Etapa etapa) {
            return (nombreEtapa == null && etapa == null) || (etapa.getClass().getSimpleName() == nombreEtapa);
        }

        @Override
        public void skipToPhase(DriverMatchSide player, DriverTurnPhase phase) {

            String jugador = mapJugador.get(player);
            String etapa = mapFase.get(phase);

            while (!partida.jugadorEnTurno(jugador) && !mismaEtapa(etapa, partida.turnoEnProceso().etapaActual())) {
                partida.terminarEtapa();
            }
        }

        @Override
        public Carta summon(DriverMatchSide player, DriverCardName card, DriverActiveZone zone) {

            String carta = mapCartas.get(card).nombre;
            
            Tablero tableroJugador = partida.tableroJugador(mapJugador.get(player));

            String zona = mapZona.get(zone);

            try {
                Carta cartaMovida = tableroJugador.invocarCarta(carta, zona);
                return cartaMovida;
            } catch (CartaNoEncontrada | EnergiaInsuficiente e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public int getCreatureHitpoints(Carta card) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'getCreatureHitpoints'");
        }

        @Override
        public void attackCreature(Carta creature, int index, Carta target) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'attackCreature'");
        }

        @Override
        public void attackPlayer(Carta creature, int index) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'attackPlayer'");
        }

        @Override
        public void activateArtifact(Carta artifact, int index, Optional<DriverMatchSide> targetPlayer,
                List<Carta> targets) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'activateArtifact'");
        }

        @Override
        public void activateAction(DriverMatchSide player, DriverCardName card, int index,
                Optional<DriverMatchSide> targetPlayer, List<Carta> targetCards) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'activateAction'");
        }

        @Override
        public void startReactionWindow() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'startReactionWindow'");
        }

        @Override
        public void endReactionWindow() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'endReactionWindow'");
        }

        @Override
        public void activateReactionFromHand(DriverMatchSide player, DriverCardName card,
                Optional<DriverMatchSide> targetPlayer, List<Carta> targetCards) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'activateReactionFromHand'");
        }

        @Override
        public void activateReactionFromActiveZone(Carta card, Optional<DriverMatchSide> targetPlayer,
                List<Carta> targetCards) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'activateReactionFromActiveZone'");
        }

        @Override
        public int playerHealth(DriverMatchSide player) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'playerHealth'");
        }

        @Override
        public int playerEnergy(DriverMatchSide player, DriverEnergyType energyType) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'playerEnergy'");
        }

        @Override
        public Optional<DriverMatchSide> winner() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'winner'");
        }
    };
}