package fiuba.tdd.tp.aceptación;

import fiuba.tdd.tp.carta.Carta;
import fiuba.tdd.tp.driver.*;
import fiuba.tdd.tp.jugador.Jugador;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static fiuba.tdd.tp.driver.DriverCardName.*;
import static fiuba.tdd.tp.driver.DriverGameMode.*;
import static fiuba.tdd.tp.driver.DriverMatchSide.*;
import static org.junit.jupiter.api.Assertions.*;

public class MatchAcceptanceTests<Account, Card> extends AcceptanceTestRoot<Jugador, Carta> {

    @Test
    void cantStartMatchWithTooFewCards() {
        List<DriverCardName> deck = loopedCardNames(20);
        Jugador blue = accountWithDeck(deck);
        Jugador green = accountWithDeck(deck);
        assertThrows(Throwable.class, () -> {
            testDriver.startMatch(HitpointLoss, blue, "main", green, "main");
        });
    }

    @Test
    void cantStartMatchWithTooManyCards() {
        List<DriverCardName> deck = loopedCardNames(100, Stream.of());
        Jugador blue = accountWithDeck(deck);
        Jugador green = accountWithDeck(deck);
        assertThrows(Throwable.class, () -> {
            testDriver.startMatch(HitpointLoss, blue, "main", green, "main");
        });
    }

    @Test
    void repeatedCardLimit() {
        assertThrows(RuntimeException.class, () -> {
            commonMatch(Stream.iterate(Alchemist, x -> x));
        });
    }

    @Test
    void energiesDontCountAgainstRepeatedCardLimit() {
        assertDoesNotThrow(() -> {
            commonMatch(Stream.iterate(FireEnergy, x -> x));
        });
    }

    @Test
    void cantSummonTooManyForZone() {
        MatchDriver<Carta> match = commonMatch(Stream.of(
                MagicBarrier
        ));

        match.skipToPhase(Blue, DriverTurnPhase.Main);
        Carta energy = match.summon(Blue, FireEnergy, DriverActiveZone.Artifact);

        match.skipToPhase(Blue, DriverTurnPhase.Main);
        match.activateArtifact(energy);
        assertThrows(Throwable.class, () -> {
            match.summon(Blue, MagicBarrier, DriverActiveZone.Reserve);
        });
    }

    @Test
    void cantSummonWithoutEnergy() {
        MatchDriver<Carta> match = commonMatch(Stream.of(
                MagicSword
        ));

        match.skipToPhase(Blue, DriverTurnPhase.Main);
        assertThrows(Throwable.class, () -> {
            match.summon(Blue, MagicSword, DriverActiveZone.Combat);
        });
    }

    @Test
    void cantSummonInWrongZone() {
        MatchDriver<Carta> match = commonMatch(Stream.of(
                Inventor
        ));

        match.skipToPhase(Blue, DriverTurnPhase.Main);
        Carta energy = match.summon(Blue, WaterEnergy, DriverActiveZone.Artifact);

        match.skipToPhase(Blue, DriverTurnPhase.Main);
        match.activateArtifact(energy);
        assertThrows(Throwable.class, () -> {
            match.summon(Blue, Inventor, DriverActiveZone.Artifact);
        });
    }

    @Test
    void cantSummonInWrongPhase() {
        MatchDriver<Carta> match = commonMatch(Stream.of(
                MagicSword
        ));

        match.skipToPhase(Blue, DriverTurnPhase.Main);
        Carta energy = match.summon(Blue, FireEnergy, DriverActiveZone.Artifact);

        match.skipToPhase(Blue, DriverTurnPhase.Main);
        match.activateArtifact(energy);

        match.skipToPhase(Blue, DriverTurnPhase.Attack);
        assertThrows(Throwable.class, () -> {
            match.summon(Blue, MagicSword, DriverActiveZone.Combat);
        });
    }

    @Test
    void cantAttackTwiceWithSameCreature() {
        MatchDriver<Carta> match = commonMatch(Stream.of(
                FireEnergy,
                MagicSword
        ));

        match.skipToPhase(Blue, DriverTurnPhase.Main);
        Carta energy = match.summon(Blue, FireEnergy, DriverActiveZone.Artifact);

        match.skipToPhase(Blue, DriverTurnPhase.Main);
        match.activateArtifact(energy);
        Carta sword = match.summon(Blue, MagicSword, DriverActiveZone.Combat);

        match.skipToPhase(Blue, DriverTurnPhase.Attack);
        match.attackPlayer(sword, 0);
        assertThrows(RuntimeException.class, () ->
                match.attackPlayer(sword, 0)
        );
    }

    @Test
    void canAttackTwiceWithDifferentCreatures() {
        MatchDriver<Carta> match = commonMatch(Stream.of(
                MagicSword,
                MagicDrill,
                FireEnergy,
                FireEnergy
        ));

        match.skipToPhase(Blue, DriverTurnPhase.Main);
        List<Carta> energies = new ArrayList<>();
        energies.add(match.summon(Blue, FireEnergy, DriverActiveZone.Artifact));
        energies.add(match.summon(Blue, FireEnergy, DriverActiveZone.Artifact));

        match.skipToPhase(Blue, DriverTurnPhase.Main);
        energies.forEach(match::activateArtifact);
        List<Carta> creatures = new ArrayList<>();
        creatures.add(match.summon(Blue, MagicSword, DriverActiveZone.Combat));
        creatures.add(match.summon(Blue, MagicDrill, DriverActiveZone.Combat));

        match.skipToPhase(Blue, DriverTurnPhase.Attack);
        assertDoesNotThrow(() -> {
            creatures.forEach(creature -> match.attackPlayer(creature, 0));
        });
    }

    @Test
    void canUseBothAttacks() {
        MatchDriver<Carta> match = commonMatch(Stream.of(
                Alchemist
        ));

        match.skipToPhase(Blue, DriverTurnPhase.Main);
        Carta fireEnergy = match.summon(Blue, FireEnergy, DriverActiveZone.Artifact);
        Carta plantEnergy = match.summon(Blue, PlantEnergy, DriverActiveZone.Artifact);

        match.skipToPhase(Blue, DriverTurnPhase.Main);
        match.activateArtifact(fireEnergy);
        match.activateArtifact(plantEnergy);
        Carta alchemist = match.summon(Blue, Alchemist, DriverActiveZone.Combat);

        match.skipToPhase(Blue, DriverTurnPhase.Attack);
        match.attackPlayer(alchemist, 0);

        match.skipToPhase(Blue, DriverTurnPhase.Attack);
        match.attackPlayer(alchemist, 1);

        assertEquals(1, match.playerEnergy(Blue, DriverEnergyType.Fire));
    }

    @Test
    void canActivateAsBothTypes() {
        MatchDriver<Carta> match = commonMatch(Stream.of(
                Alchemist
        ));

        match.skipToPhase(Blue, DriverTurnPhase.Main);
        Carta fireEnergy = match.summon(Blue, FireEnergy, DriverActiveZone.Artifact);
        Carta plantEnergy = match.summon(Blue, PlantEnergy, DriverActiveZone.Artifact);

        match.skipToPhase(Blue, DriverTurnPhase.Main);
        match.activateArtifact(fireEnergy);
        match.activateArtifact(plantEnergy);
        Carta alchemist = match.summon(Blue, Alchemist, DriverActiveZone.Combat);

        match.skipToPhase(Blue, DriverTurnPhase.Main);
        match.activateArtifact(alchemist);

        match.skipToPhase(Blue, DriverTurnPhase.Attack);
        assertDoesNotThrow(() -> {
            match.attackPlayer(alchemist, 0);
        });
    }

    @Test
    void useAction() {
        MatchDriver<Carta> match = commonMatch(Stream.of(
                MagicSword
        ));

        match.skipToPhase(Blue, DriverTurnPhase.Main);
        Carta energy = match.summon(Blue, FireEnergy, DriverActiveZone.Artifact);

        match.skipToPhase(Blue, DriverTurnPhase.Main);
        match.activateArtifact(energy);

        match.activateAction(Blue, MagicSword, 0, Optional.of(Green), new ArrayList<>());
        assertEquals(17, match.playerHealth(Green));
    }

    @Test
    void useReaction() {
        MatchDriver<Carta> match = commonMatch(Stream.of(
                Saboteur
        ));

        match.skipToPhase(Blue, DriverTurnPhase.Main);
        Carta blueEnergy = match.summon(Blue, WaterEnergy, DriverActiveZone.Artifact);

        match.skipToPhase(Green, DriverTurnPhase.Main);
        Carta greenEnergy = match.summon(Green, WaterEnergy, DriverActiveZone.Artifact);

        match.skipToPhase(Blue, DriverTurnPhase.Main);
        match.activateArtifact(blueEnergy);

        match.skipToPhase(Green, DriverTurnPhase.Main);
        match.withReactionWindow(() -> {
            match.activateArtifact(greenEnergy);
            match.activateReactionFromHand(Blue, Saboteur, Optional.empty(), new ArrayList<>());
        });
        assertEquals(0, match.playerEnergy(Green, DriverEnergyType.Water));
    }

    @Test
    void hitpointLossVictoryCondition() {
        MatchDriver<Carta> match = commonMatch(Stream.of(
                FireEnergy,
                MagicSword
        ));

        match.skipToPhase(Blue, DriverTurnPhase.Main);
        Carta energy = match.summon(Blue, FireEnergy, DriverActiveZone.Artifact);

        match.skipToPhase(Blue, DriverTurnPhase.Main);
        match.activateArtifact(energy);
        Carta sword = match.summon(Blue, MagicSword, DriverActiveZone.Combat);

        for (int i = 0; i < 7; i++) {
            assertEquals(Optional.empty(), match.winner());
            match.skipToPhase(Blue, DriverTurnPhase.Attack);
            match.attackPlayer(sword, 0);
        }
        assertEquals(Optional.of(Blue), match.winner());
    }

    @Test
    void creatureSlayerVictoryCondition() {
        List<DriverCardName> greenCreatures = Stream.of(
                Alchemist,
                Inventor,
                Inventor,
                Goblin,
                Saboteur,
                Saboteur,
                MagicSword
        ).collect(Collectors.toList());
        List<DriverCardName> blueDeck = loopedCardNames(60, Stream.of(
                PlantEnergy,
                Orc
        ));
        List<DriverCardName> greenDeck = loopedCardNames(60, Stream.concat(
                Stream.of(
                        FireEnergy,
                        WaterEnergy,
                        PlantEnergy
                ),
                greenCreatures.stream()
        ));

        Jugador blue = accountWithDeck(blueDeck);
        Jugador green = accountWithDeck(greenDeck);
        MatchDriver<Carta> match = testDriver.startMatch(CreatureSlayer, blue, "main", green, "main");

        match.forceDeckOrder(Blue, blueDeck);
        match.forceDeckOrder(Green, greenDeck);
        match.start();

        match.skipToPhase(Blue, DriverTurnPhase.Main);
        Carta blueEnergy = match.summon(Blue, PlantEnergy, DriverActiveZone.Artifact);

        match.skipToPhase(Green, DriverTurnPhase.Main);
        List<Carta> greenEnergies = new ArrayList<>();
        greenEnergies.add(match.summon(Green, FireEnergy, DriverActiveZone.Artifact));
        greenEnergies.add(match.summon(Green, WaterEnergy, DriverActiveZone.Artifact));
        greenEnergies.add(match.summon(Green, PlantEnergy, DriverActiveZone.Artifact));

        match.skipToPhase(Blue, DriverTurnPhase.Main);
        match.activateArtifact(blueEnergy);
        Carta orc = match.summon(Blue, Orc, DriverActiveZone.Combat);

        for (DriverCardName creature: greenCreatures) {
            assertEquals(Optional.empty(), match.winner());

            match.skipToPhase(Green, DriverTurnPhase.Main);
            greenEnergies.forEach(match::activateArtifact);
            Carta target = match.summon(Green, creature, DriverActiveZone.Combat);

            match.skipToPhase(Blue, DriverTurnPhase.Attack);
            match.attackCreature(orc, 0, target);
            if (creature == Goblin) {
                match.skipToPhase(Blue, DriverTurnPhase.Attack);
                match.attackCreature(orc, 0, target);
            }
        }

        assertEquals(Optional.empty(), match.winner());
        match.skipToPhase(Blue, DriverTurnPhase.Initial);
        assertEquals(Optional.of(Blue), match.winner());
    }

    private Jugador accountWithDeck(List<DriverCardName> cardList) {
        Jugador account = testDriver.newAccount();
        testDriver.addCurrency(account, Integer.MAX_VALUE);
        for (DriverCardName cardName: cardList) {
            testDriver.buyCards(account, cardName, 1);
            testDriver.addDeckCards(account, "main", cardName, 1);
        }
        return account;
    }

    private MatchDriver<Carta> commonMatch(Stream<DriverCardName> prefix) {
        List<DriverCardName> deck = loopedCardNames(40, prefix);
        Jugador blue = accountWithDeck(deck);
        Jugador green = accountWithDeck(deck);
        MatchDriver<Carta> match = testDriver.startMatch(HitpointLoss, blue, "main", green, "main");

        match.forceDeckOrder(Blue, deck);
        match.forceDeckOrder(Green, deck);
        match.start();
        return match;
    }
}
