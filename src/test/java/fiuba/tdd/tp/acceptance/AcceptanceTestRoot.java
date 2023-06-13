package fiuba.tdd.tp.acceptance;


import fiuba.tdd.tp.Excepciones.CartaNoEncontrada;
import fiuba.tdd.tp.Excepciones.DineroInsuficiente;
import fiuba.tdd.tp.Excepciones.MazoExistente;
import fiuba.tdd.tp.Excepciones.MazoInvalido;
import fiuba.tdd.tp.carta.Carta;
import fiuba.tdd.tp.carta.CartasDisponibles;
import fiuba.tdd.tp.driver.*;
import fiuba.tdd.tp.jugador.Jugador;
import fiuba.tdd.tp.mazo.Mazo;
import fiuba.tdd.tp.modo.Modo;
import fiuba.tdd.tp.modo.Modo1;
import fiuba.tdd.tp.partida.Partida;

import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@SpringBootTest
public class AcceptanceTestRoot<Account, Card> {
    protected Driver<Jugador, Carta> testDriver = new Driver<Jugador, Carta>() {
        static Map<DriverCardName, CartasDisponibles> map = new HashMap<>();
        static {
            map.put(DriverCardName.Antimagic, CartasDisponibles.ANTIMAGIA);
            map.put(DriverCardName.Alchemist, CartasDisponibles.ALQUIMISTA);
            map.put(DriverCardName.Corrosion, CartasDisponibles.CORROSION);
            map.put(DriverCardName.Drain, CartasDisponibles.DRENAR);
            map.put(DriverCardName.FireEnergy, CartasDisponibles.FUEGO);
            map.put(DriverCardName.WaterEnergy, CartasDisponibles.AGUA);
            map.put(DriverCardName.PlantEnergy, CartasDisponibles.PLANTA);
            map.put(DriverCardName.BlockReaction,CartasDisponibles.IMPEDIR);
            map.put(DriverCardName.Goblin,CartasDisponibles.GOBLIN);
            map.put(DriverCardName.Hospital,CartasDisponibles.HOSPITAL);
            map.put(DriverCardName.Inventor,CartasDisponibles.INVENTOR);
            map.put(DriverCardName.Saboteur,CartasDisponibles.SABOTEAR);
            map.put(DriverCardName.Resonance,CartasDisponibles.RESONANCIA);
            map.put(DriverCardName.Treason,CartasDisponibles.TRAICION);
            map.put(DriverCardName.Orc,CartasDisponibles.ORCO);
            map.put(DriverCardName.Recycle,CartasDisponibles.RECICLAR);
            map.put(DriverCardName.MagicSword,CartasDisponibles.ESPADAMAGICA);
            map.put(DriverCardName.MagicDrill,CartasDisponibles.TALADROMAGICO);
            map.put(DriverCardName.MagicBarrier,CartasDisponibles.BARRERAMAGICA);
            map.put(DriverCardName.Sacrifice,CartasDisponibles.SACRIFICIO);
        }


        @Override
        public Jugador newAccount() {
            return new Jugador("jugador", "contra");
        }

        @Override
        public int countCards(Jugador account, DriverCardName cardName) {
            HashMap<String, Integer> cartas = account.getCartas();
            String nombre = map.get(cardName).nombre;
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
            CartasDisponibles carta = map.get(cardName);
            for(int i = 0; i< amount; i++){
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
            HashMap<String, Integer> cartas = mazos.get(deckName).cartas;
            String nombre = map.get(cardName).nombre;
            Integer cantidad = Objects.requireNonNullElse(cartas.get(nombre), 0);

            return cantidad;
        }

        @Override
        public void addDeckCards(Jugador account, String deckName, DriverCardName cardName, int amount) {
            String nombreCarta = map.get(cardName).nombre;
            
            buyCards(account, DriverCardName.FireEnergy, 40);

            if (!account.getMazos().containsKey(deckName)) {
                Mazo mazoModoUno = null;
                HashMap<String, Integer> cartasModoUno = new HashMap<>();
                Modo modoUno = new Modo1();
                cartasModoUno.put(nombreCarta, amount);
                cartasModoUno.put(CartasDisponibles.FUEGO.nombre, 40);
                
                try {
                    mazoModoUno = new Mazo(cartasModoUno, modoUno);
                } catch (MazoInvalido e) {
                    throw new RuntimeException(e);
                }

                try {
                    account.agregarMazo(deckName, mazoModoUno);
                } catch (MazoExistente | CartaNoEncontrada e) {
                    throw new RuntimeException(e);
                }
            } else {
                Mazo mazo = account.getMazos().get(deckName);
                try {
                    for (int i = 0; i < amount; i++) {
                        mazo.agregarCarta(nombreCarta);
                    }
                } catch (MazoInvalido e) {
                    throw new RuntimeException(e);
                }
            }
        }

        @Override
        public MatchDriver<Carta> startMatch(DriverGameMode mode, Jugador blue, String blueDeck, Jugador green, String greenDeck) {

             
            
            return matchDriver;
        }
    };

    protected MatchDriver<Carta> matchDriver = new MatchDriver<Carta>() {

        @Override
        public List<DriverCardName> deckOrder(DriverMatchSide player) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'deckOrder'");
        }

        @Override
        public void forceDeckOrder(DriverMatchSide player, List<DriverCardName> cards) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'forceDeckOrder'");
        }

        @Override
        public void start() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'start'");
        }

        @Override
        public void skipToPhase(DriverMatchSide player, DriverTurnPhase phase) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'skipToPhase'");
        }

        @Override
        public Carta summon(DriverMatchSide player, DriverCardName card, DriverActiveZone zone) {
            // TODO Auto-generated method stub
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