package fiuba.tdd.tp.acceptance;


import fiuba.tdd.tp.Excepciones.CartaNoEncontrada;
import fiuba.tdd.tp.Excepciones.DineroInsuficiente;
import fiuba.tdd.tp.Excepciones.MazoExistente;
import fiuba.tdd.tp.Excepciones.PartidaInvalida;
import fiuba.tdd.tp.carta.Carta;
import fiuba.tdd.tp.carta.CartasDisponibles;
import fiuba.tdd.tp.driver.*;
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

    static Map<DriverCardName, CartasDisponibles> mapCartas = new HashMap<>();
        static {
            mapCartas.put(DriverCardName.Antimagic, CartasDisponibles.ANTIMAGIA);
            mapCartas.put(DriverCardName.Alchemist, CartasDisponibles.ALQUIMISTA);
            mapCartas.put(DriverCardName.Corrosion, CartasDisponibles.CORROSION);
            mapCartas.put(DriverCardName.Drain, CartasDisponibles.DRENAR);
            mapCartas.put(DriverCardName.FireEnergy, CartasDisponibles.FUEGO);
            mapCartas.put(DriverCardName.WaterEnergy, CartasDisponibles.AGUA);
            mapCartas.put(DriverCardName.PlantEnergy, CartasDisponibles.PLANTA);
            mapCartas.put(DriverCardName.BlockReaction, CartasDisponibles.IMPEDIR);
            mapCartas.put(DriverCardName.Goblin, CartasDisponibles.GOBLIN);
            mapCartas.put(DriverCardName.Hospital, CartasDisponibles.HOSPITAL);
            mapCartas.put(DriverCardName.Inventor, CartasDisponibles.INVENTOR);
            mapCartas.put(DriverCardName.Saboteur, CartasDisponibles.SABOTEAR);
            mapCartas.put(DriverCardName.Resonance, CartasDisponibles.RESONANCIA);
            mapCartas.put(DriverCardName.Treason, CartasDisponibles.TRAICION);
            mapCartas.put(DriverCardName.Orc, CartasDisponibles.ORCO);
            mapCartas.put(DriverCardName.Recycle, CartasDisponibles.RECICLAR);
            mapCartas.put(DriverCardName.MagicSword, CartasDisponibles.ESPADAMAGICA);
            mapCartas.put(DriverCardName.MagicDrill, CartasDisponibles.TALADROMAGICO);
            mapCartas.put(DriverCardName.MagicBarrier, CartasDisponibles.BARRERAMAGICA);
            mapCartas.put(DriverCardName.Sacrifice, CartasDisponibles.SACRIFICIO);
        }

        static Map<DriverGameMode, Modo> mapModo = new HashMap<>();
        static {
            mapModo.put(DriverGameMode.CreatureSlayer, new Modo1());
            mapModo.put(DriverGameMode.CreatureSlayer, new Modo2());
        }

        static Map<DriverMatchSide, String> mapJugador = new HashMap<>();
        static {
            mapJugador.put(DriverMatchSide.Blue, "blue");
            mapJugador.put(DriverMatchSide.Green, "green");
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
            
            testDriver.addDeckCards(blue, blueDeck, null, 0);
            testDriver.addDeckCards(green, greenDeck, null, 0);

            Mazo mazoJugadorBlue = blue.getMazos().get(blueDeck);
            Mazo mazoJugadorGreen = green.getMazos().get(greenDeck);

            blue.modificarNombre("blue");
            green.modificarNombre("green");

            Partida partida;

            try {
                blue.agregarMazo(blueDeck, mazoJugadorBlue);
                green.agregarMazo(greenDeck, mazoJugadorGreen);
                partida = new Partida(mapModo.get(mode), blue.nombreJugador(), green.nombreJugador(), mazoJugadorBlue, mazoJugadorGreen);             
            } catch (MazoExistente | CartaNoEncontrada | PartidaInvalida e) {
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
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'start'");
        }

        @Override
        public void skipToPhase(DriverMatchSide player, DriverTurnPhase phase) {
            /**
             * End phases and turns until the current player and phase match the
             * arguments. Must end at least one phase
             */

            throw new UnsupportedOperationException("Unimplemented method 'skipToPhase'");
        }

        @Override
        public Carta summon(DriverMatchSide player, DriverCardName card, DriverActiveZone zone) {
            /**
             * Play one card from the player's hand into the given zone
             * @return Reference to the newly placed card
             * @throws RuntimeException if the summon can't complete as indicated
             */
            throw new UnsupportedOperationException("Unimplemented method 'summon'");
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