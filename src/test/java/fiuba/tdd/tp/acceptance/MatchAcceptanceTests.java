package fiuba.tdd.tp.acceptance;

import fiuba.tdd.tp.carta.Carta;
import fiuba.tdd.tp.driver.*;
import fiuba.tdd.tp.jugador.Jugador;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static fiuba.tdd.tp.driver.DriverCardName.*;
import static fiuba.tdd.tp.driver.DriverGameMode.*;
import static fiuba.tdd.tp.driver.DriverMatchSide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MatchAcceptanceTests<AccountReference, CardReference> extends AcceptanceTestRoot<Jugador, Carta> {
    
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

    @Disabled
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

    @Disabled
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

    @Disabled
    @Test
    void cantSummonInWrongPhase() {
        MatchDriver<Carta> match = commonMatch(Stream.of(
                Alchemist
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

    @Disabled
    @Test
    void useAction() {
        MatchDriver<Carta> match = commonMatch(Stream.of(
                MagicSword
        ));

        match.skipToPhase(Blue, DriverTurnPhase.Main);
        Carta energy = match.summon(Blue, FireEnergy, DriverActiveZone.Artifact);

        match.skipToPhase(Blue, DriverTurnPhase.Main);
        match.activateArtifact(energy);

        match.activateAction(Blue, MagicSword, 0, Optional.of(Green), List.of());
        assertEquals(17, match.playerHealth(Green));
    }

    @Disabled
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
            match.activateReactionFromHand(Blue, Saboteur, Optional.empty(), List.of());
        });
        assertEquals(0, match.playerEnergy(Green, DriverEnergyType.Water));
    }

    @Disabled
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

    @Disabled
    @Test
    void creatureSlayerVictoryCondition() {
        List<DriverCardName> greenCreatures = List.of(
                Alchemist,
                Inventor,
                Inventor,
                Goblin,
                Saboteur,
                Saboteur,
                MagicSword
        );
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
        List<Carta> greenEnergies = List.of(
                match.summon(Green, FireEnergy, DriverActiveZone.Artifact),
                match.summon(Green, WaterEnergy, DriverActiveZone.Artifact),
                match.summon(Green, PlantEnergy, DriverActiveZone.Artifact)
        );

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
        Jugador Jugador = testDriver.newAccount();
        testDriver.addCurrency(Jugador, Integer.MAX_VALUE);
        for (DriverCardName cardName: cardList) {
            testDriver.buyCards(Jugador, cardName, 1);
            testDriver.addDeckCards(Jugador, "main", cardName, 1);
        }
        return Jugador;
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
